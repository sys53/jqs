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

import org.grayrabbit.jqs.core.compiler.Compiler;
import org.grayrabbit.jqs.core.utils.Holder;
import java.util.Collections;
import java.util.Set;

/**
 * 插件加载器
 * <p/>
 * Author:sys53
 * DATE 14-5-28 下午11:54
 * version $Id:PluginLoader.java,v 0.1.Exp $
 */
public class PluginLoader<T> {

    /** 扩展插件类型 */
    private Class<T>  type;
    /** 自适应实例插件 */
    private Holder<T> cachedAdaptiveInstance = new Holder<T>();

    /** 默认名称 */
    private String    cachedDefaultName;

    public static <T> PluginLoader<T> getPluginLoader(Class<T> type) {
        return null;
    }

    public static ClassLoader findClassLoader() {
        return PluginLoader.class.getClassLoader();
    }

    public T getPlugin(String name) {
        return null;
    }

    public boolean hasPlugin(String name) {
        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[" + type.getName() + "]";
    }

    public Set<String> getSupportedPlugins() {
        return Collections.emptySet();
    }

    /**
     * 获取扩展插件实例
     * @return
     */
    public T getAdaptivePlugin() {
        T instance = cachedAdaptiveInstance.get();
        if (instance == null) {
            synchronized (cachedAdaptiveInstance) {
                instance = cachedAdaptiveInstance.get();
                if (instance == null) {//double check
                    try {
                        instance = createAdaptivePlugin();
                        cachedAdaptiveInstance.set(instance);
                    } catch (Throwable t) {
                        throw new IllegalStateException("fail to create adaptive plugin instance:"
                                                        + t.toString(), t);
                    }
                }
            }
        }
        return instance;
    }

    /**
     * 实例化并初始化自适应插件
     * @return
     */
    private T createAdaptivePlugin() {
        try {
            return injectPlugin(getAdaptivePluginClass().newInstance());
        } catch (Exception e) {
            throw new IllegalStateException("Can not create adaptive plugin " + type + ",cause:"
                                            + e.getMessage(), e);
        }
    }

    private T injectPlugin(Object o) {
        return null;
    }

    /**
     * 获取动态自适应字节码编译成自适应插件
     * @return
     */
    private Class<?> getAdaptivePluginClass() {
        String code = createAdaptivePluginCode();
        ClassLoader classLoader = findClassLoader();
        Compiler compiler = PluginLoader.getPluginLoader(Compiler.class).getAdaptivePlugin();
        return compiler.compile(code, classLoader);
    }

    /**
     * 生成自适应插件动态代码
     * @return
     */
    private String createAdaptivePluginCode() {
        AdaptiveClassCreator creator = new AdaptiveClassCreator(type, cachedDefaultName);
        return creator.createAdaptivePluginCode();
    }
}
