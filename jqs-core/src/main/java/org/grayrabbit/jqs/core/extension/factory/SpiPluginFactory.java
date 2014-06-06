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

package org.grayrabbit.jqs.core.extension.factory;

import org.grayrabbit.jqs.core.annotation.Plugin;
import org.grayrabbit.jqs.core.extension.PluginFactory;
import org.grayrabbit.jqs.core.extension.PluginLoader;

/**
 * 基于Plugin注释的插件工厂实现类
 * <p>
 *     插件扩展机制，使用@Plugin接口标注接口是否为可扩展接口
 * </p>
 * Author:sys53
 * DATE 14-6-3 上午11:29
 * version $Id:SpiPluginFactory.java,v 0.1.Exp $
 */
public class SpiPluginFactory implements PluginFactory {
    @Override
    public <T> T getPlugin(Class<T> type, String name) {
        if (type.isInterface() && type.isAnnotationPresent(Plugin.class)) {
            PluginLoader<T> loader = PluginLoader.getPluginLoader(type);
            if (loader.getSupportedPlugins().size() > 0) {
                return loader.getAdaptivePlugin();
            }
        }
        return null;
    }
}
