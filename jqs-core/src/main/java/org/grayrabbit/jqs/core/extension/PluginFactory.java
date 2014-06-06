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

import org.grayrabbit.jqs.core.annotation.Plugin;

/**
 * 插件工厂接口
 * <p/>
 * Author:sys53
 * DATE 14-5-28 下午11:57
 * version $Id:PluginFactory.java,v 0.1.Exp $
 */
@Plugin
public interface PluginFactory {
    <T> T getPlugin(Class<T> type, String name);
}
