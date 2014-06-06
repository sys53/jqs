/*
 * Copyright (c) 2013-2014. Grayrabbit.net Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.grayrabbit.jqs.core.extension;

import org.grayrabbit.jqs.core.logger.Logger;
import org.grayrabbit.jqs.core.logger.LoggerFactory;
import org.grayrabbit.jqs.core.Node;
import org.grayrabbit.jqs.core.Url;
import org.grayrabbit.jqs.core.annotation.Adaptive;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * 自适应插件的动态代码生成器
 * <p/>
 * Author:sys53
 * DATE 14-6-3 下午5:14
 * version $Id:AdaptiveClassCreator.java,v 0.1.Exp $
 */
public final class AdaptiveClassCreator {
    private final static Logger LOGGER = LoggerFactory.getLogger(AdaptiveClassCreator.class);

    private Class<?>            clazz;

    private String              cachedDefaultName;

    AdaptiveClassCreator(Class<?> clazz, String defaultName) {
        this.clazz = clazz;
        this.cachedDefaultName = defaultName;
    }

    String createAdaptivePluginCode() {
        StringBuilder codeBuidler = new StringBuilder();
        Method[] methods = clazz.getMethods();
        boolean hasAdaptiveAnnotation = false;
        for (Method m : methods) {
            if (m.isAnnotationPresent(Adaptive.class)) {
                hasAdaptiveAnnotation = true;
                break;
            }
        }
        // 完全没有Adaptive方法，则不需要生成Adaptive类
        if (!hasAdaptiveAnnotation)
            throw new IllegalStateException("No adaptive method on plugin " + clazz.getName()
                                            + ", refuse to create the adaptive class!");

        codeBuidler.append("package " + clazz.getPackage().getName() + ";");
        codeBuidler.append("\nimport " + PluginLoader.class.getName() + ";");
        codeBuidler.append("\npublic class " + clazz.getSimpleName() + "$Jqs$Adpative"
                           + " implements " + clazz.getCanonicalName() + " {");

        for (Method method : methods) {
            Class<?> rt = method.getReturnType();
            Class<?>[] pts = method.getParameterTypes();
            Class<?>[] ets = method.getExceptionTypes();

            Adaptive adaptiveAnnotation = method.getAnnotation(Adaptive.class);
            StringBuilder code = new StringBuilder(512);
            if (adaptiveAnnotation == null) {
                code.append("throw new UnsupportedOperationException(\"method ")
                    .append(method.toString()).append(" of interface ").append(clazz.getName())
                    .append(" is not adaptive method!\");");
            } else {
                int urlTypeIndex = -1;
                for (int i = 0; i < pts.length; ++i) {
                    if (pts[i].equals(Url.class)) {
                        urlTypeIndex = i;
                        break;
                    }
                }
                // 有类型为URL的参数
                if (urlTypeIndex != -1) {
                    // Null Point check
                    String s = String
                        .format(
                            "\nif (arg%d == null) throw new IllegalArgumentException(\"url == null\");",
                            urlTypeIndex);
                    code.append(s);

                    s = String.format("\n%s url = arg%d;", Url.class.getName(), urlTypeIndex);
                    code.append(s);
                }
                // 参数没有URL类型
                else {
                    String attribMethod = null;

                    // 找到参数的URL属性
                    LBL_PTS: for (int i = 0; i < pts.length; ++i) {
                        Method[] ms = pts[i].getMethods();
                        for (Method m : ms) {
                            String name = m.getName();
                            if ((name.startsWith("get") || name.length() > 3)
                                && Modifier.isPublic(m.getModifiers())
                                && !Modifier.isStatic(m.getModifiers())
                                && m.getParameterTypes().length == 0
                                && m.getReturnType() == Url.class) {
                                urlTypeIndex = i;
                                attribMethod = name;
                                break LBL_PTS;
                            }
                        }
                    }
                    if (attribMethod == null) {
                        throw new IllegalStateException(
                            "fail to create adative class for interface "
                                    + clazz.getName()
                                    + ": not found url parameter or url attribute in parameters of method "
                                    + method.getName());
                    }

                    // Null point check
                    String s = String
                        .format(
                            "\nif (arg%d == null) throw new IllegalArgumentException(\"%s argument == null\");",
                            urlTypeIndex, pts[urlTypeIndex].getName());
                    code.append(s);
                    s = String
                        .format(
                            "\nif (arg%d.%s() == null) throw new IllegalArgumentException(\"%s argument %s() == null\");",
                            urlTypeIndex, attribMethod, pts[urlTypeIndex].getName(), attribMethod);
                    code.append(s);

                    s = String.format("%s url = arg%d.%s();", Url.class.getName(), urlTypeIndex,
                        attribMethod);
                    code.append(s);
                }

                String[] value = adaptiveAnnotation.value();
                // 没有设置Key，则使用“扩展点接口名的点分隔 作为Key
                if (value.length == 0) {
                    char[] charArray = clazz.getSimpleName().toCharArray();
                    StringBuilder sb = new StringBuilder(128);
                    for (int i = 0; i < charArray.length; i++) {
                        if (Character.isUpperCase(charArray[i])) {
                            if (i != 0) {
                                sb.append(".");
                            }
                            sb.append(Character.toLowerCase(charArray[i]));
                        } else {
                            sb.append(charArray[i]);
                        }
                    }
                    value = new String[] { sb.toString() };
                }

                boolean hasInvocation = false;
                for (int i = 0; i < pts.length; ++i) {
                    if (pts[i].getName().equals("org.grayrabbit.jqs.rpc.Invocation")) {
                        // Null Point check
                        String s = String
                            .format(
                                "\nif (arg%d == null) throw new IllegalArgumentException(\"invocation == null\");",
                                i);
                        code.append(s);
                        s = String.format("\nString methodName = arg%d.getMethodName();", i);
                        code.append(s);
                        hasInvocation = true;
                        break;
                    }
                }

                String defaultExtName = cachedDefaultName;
                String getNameCode = null;
                for (int i = value.length - 1; i >= 0; --i) {
                    if (i == value.length - 1) {
                        if (null != defaultExtName) {
                            if (!"protocol".equals(value[i]))
                                if (hasInvocation)
                                    getNameCode = String.format(
                                        "url.getMethodParameter(methodName, \"%s\", \"%s\")",
                                        value[i], defaultExtName);
                                else
                                    getNameCode = String.format("url.getParameter(\"%s\", \"%s\")",
                                        value[i], defaultExtName);
                            else
                                getNameCode = String.format(
                                    "( url.getProtocol() == null ? \"%s\" : url.getProtocol() )",
                                    defaultExtName);
                        } else {
                            if (!"protocol".equals(value[i]))
                                if (hasInvocation)
                                    getNameCode = String.format(
                                        "url.getMethodParameter(methodName, \"%s\", \"%s\")",
                                        value[i], defaultExtName);
                                else
                                    getNameCode = String.format("url.getParameter(\"%s\")",
                                        value[i]);
                            else
                                getNameCode = "url.getProtocol()";
                        }
                    } else {
                        if (!"protocol".equals(value[i]))
                            if (hasInvocation)
                                getNameCode = String.format(
                                    "url.getMethodParameter(methodName, \"%s\", \"%s\")", value[i],
                                    defaultExtName);
                            else
                                getNameCode = String.format("url.getParameter(\"%s\", %s)",
                                    value[i], getNameCode);
                        else
                            getNameCode = String
                                .format("url.getProtocol() == null ? (%s) : url.getProtocol()",
                                    getNameCode);
                    }
                }
                code.append("\nString pluginName = ").append(getNameCode).append(";");
                // check pluginName == null?
                String s = String
                    .format(
                        "\nif(pluginName == null) "
                                + "throw new IllegalStateException(\"Fail to get plugin(%s) name from url(\" + url.toString() + \") use keys(%s)\");",
                        clazz.getName(), Arrays.toString(value));
                code.append(s);

                s = String.format(
                    "\n%s plugin = (%<s)%s.getPluginLoader(%s.class).getPlugin(pluginName);",
                    clazz.getName(), PluginLoader.class.getSimpleName(), clazz.getName());
                code.append(s);

                // return statement
                if (!rt.equals(void.class)) {
                    code.append("\nreturn ");
                }

                s = String.format("plugin.%s(", method.getName());
                code.append(s);
                for (int i = 0; i < pts.length; i++) {
                    if (i != 0)
                        code.append(", ");
                    code.append("arg").append(i);
                }
                code.append(");");
            }

            codeBuidler.append("\npublic " + rt.getCanonicalName() + " " + method.getName() + "(");
            for (int i = 0; i < pts.length; i++) {
                if (i > 0) {
                    codeBuidler.append(", ");
                }
                codeBuidler.append(pts[i].getCanonicalName());
                codeBuidler.append(" ");
                codeBuidler.append("arg" + i);
            }
            codeBuidler.append(")");
            if (ets.length > 0) {
                codeBuidler.append(" throws ");
                for (int i = 0; i < ets.length; i++) {
                    if (i > 0) {
                        codeBuidler.append(", ");
                    }
                    codeBuidler.append(pts[i].getCanonicalName());
                }
            }
            codeBuidler.append(" {");
            codeBuidler.append(code.toString());
            codeBuidler.append("\n}");
        }
        codeBuidler.append("\n}");
        LOGGER.debug(codeBuidler.toString());
        return codeBuidler.toString();
    }

    private static class Tester {
        public static void main(String[] args) {
            AdaptiveClassCreator creator = new AdaptiveClassCreator(Node.class, "node");
            System.out.println(creator.createAdaptivePluginCode());
        }
    }
}
