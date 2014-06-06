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

package org.grayrabbit.jqs.core.logger;

import org.grayrabbit.jqs.core.logger.slf4j.Slf4jLoggerAdapter;
import org.grayrabbit.jqs.core.logger.support.JqsInfoLogger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 日志输出者工厂
 * <p/>
 * Author:sys53
 * DATE 14-6-3 下午5:22
 * version $Id:LoggerFactory.java,v 0.1.Exp $
 */
public class LoggerFactory {

    private static volatile LoggerAdapter                   LOGGER_ADAPTER;
    private static ConcurrentHashMap<String, JqsInfoLogger> LOGGERS = new ConcurrentHashMap<String, JqsInfoLogger>();

    static {//暂时只考虑slf4j
        LOGGER_ADAPTER = new Slf4jLoggerAdapter();
    }

    /**
     * 获取日志输出器
     *
     * @param clazz
     *            分类键
     * @return 日志输出器, 后验条件: 不返回null.
     */
    public static Logger getLogger(Class<?> clazz) {
        JqsInfoLogger logger = LOGGERS.get(clazz.getName());
        if (logger == null) {
            LOGGERS
                .putIfAbsent(clazz.getName(), new JqsInfoLogger(LOGGER_ADAPTER.getLogger(clazz)));
            logger = LOGGERS.get(clazz.getName());
        }
        return logger;
    }

    /**
     * 获取日志输出器
     * @param key 分类键
     * @return 日志输出器, 后验条件: 不返回null.
     */
    public static Logger getLogger(String key) {
        JqsInfoLogger logger = LOGGERS.get(key);
        if (logger == null) {
            LOGGERS.putIfAbsent(key, new JqsInfoLogger(LOGGER_ADAPTER.getLogger(key)));
            logger = LOGGERS.get(key);
        }
        return logger;
    }
}
