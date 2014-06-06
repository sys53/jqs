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

package org.grayrabbit.jqs.core.logger.slf4j;

import org.grayrabbit.jqs.core.logger.Logger;
import org.grayrabbit.jqs.core.logger.LoggerAdapter;

/**
 * slf4j日志适配器
 * <p/>
 * Author:sys53
 * DATE 14-6-4 上午11:23
 * version $Id:Slf4jLoggerAdapter.java,v 0.1.Exp $
 */
public class Slf4jLoggerAdapter implements LoggerAdapter {
    /**
     * 获取日志输出者
     *
     * @param clazz
     * @return
     */
    @Override
    public Logger getLogger(Class<?> clazz) {
        return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(clazz));
    }

    /**
     * 获取日志输出者
     *
     * @param key
     * @return
     */
    @Override
    public Logger getLogger(String key) {
        return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(key));
    }
}
