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

import java.io.Serializable;

/**
 * Slf4jLogger日志接口实现
 * <p/>
 * Author:sys53
 * DATE 14-6-4 上午10:21
 * version $Id:Slf4jLogger.java,v 0.1.Exp $
 */
public class Slf4jLogger implements Logger, Serializable {

    private final org.slf4j.Logger logger;

    public Slf4jLogger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    /**
     * 输出跟踪信息
     *
     * @param format 格式化信息内容
     * @param args   信息参数
     */
    @Override
    public void trace(String format, Object... args) {
        logger.trace(format, args);
    }

    /**
     * 输出跟踪信息
     *
     * @param e 异常信息
     */
    @Override
    public void trace(Throwable e) {
        logger.trace(e.getMessage(), e);
    }

    /**
     * 输出跟踪信息
     *
     * @param format 格式化信息内容
     * @param e      异常信息
     * @param args   信息参数
     */
    @Override
    public void trace(String format, Throwable e, Object... args) {
        logger.trace(String.format(format, args), e);
    }

    /**
     * 输出调试信息
     *
     * @param format 格式化信息内容
     * @param args   信息参数
     */
    @Override
    public void debug(String format, Object... args) {
        logger.debug(format, args);
    }

    /**
     * 输出调试信息
     *
     * @param e 异常信息
     */
    @Override
    public void debug(Throwable e) {
        logger.debug(e.getMessage(), e);
    }

    /**
     * 输出调试信息
     *
     * @param format 格式化信息内容
     * @param e      异常信息
     * @param args   信息参数
     */
    @Override
    public void debug(String format, Throwable e, Object... args) {
        logger.debug(String.format(format, args), e);
    }

    /**
     * 输出普通信息
     *
     * @param format 格式化信息内容
     * @param args   信息参数
     */
    @Override
    public void info(String format, Object... args) {
        logger.info(format, args);
    }

    /**
     * 输出普通信息
     *
     * @param e 异常信息
     */
    @Override
    public void info(Throwable e) {
        logger.info(e.getMessage(), e);
    }

    /**
     * 输出普通信息
     *
     * @param format 格式化信息内容
     * @param e      异常信息
     * @param args   信息参数
     */
    @Override
    public void info(String format, Throwable e, Object... args) {
        logger.info(String.format(format, args), e);
    }

    /**
     * 输出警告信息
     *
     * @param format 格式化信息内容
     * @param args   信息参数
     */
    @Override
    public void warn(String format, Object... args) {
        logger.warn(format, args);
    }

    /**
     * 输出警告信息
     *
     * @param e 异常信息
     */
    @Override
    public void warn(Throwable e) {
        logger.warn(e.getMessage(), e);
    }

    /**
     * 输出警告信息
     *
     * @param format 格式化信息内容
     * @param e      异常信息
     * @param args   信息参数
     */
    @Override
    public void warn(String format, Throwable e, Object... args) {
        logger.warn(String.format(format, args), e);
    }

    /**
     * 输出错误信息
     *
     * @param format 格式化信息内容
     * @param args   信息参数
     */
    @Override
    public void error(String format, Object... args) {
        logger.error(format, args);
    }

    /**
     * 输出错误信息
     *
     * @param e 异常信息
     */
    @Override
    public void error(Throwable e) {
        logger.error(e.getMessage(), e);
    }

    /**
     * 输出错误信息
     *
     * @param format 格式化信息内容
     * @param e      异常信息
     * @param args   信息参数
     */
    @Override
    public void error(String format, Throwable e, Object... args) {
        logger.error(String.format(format, args, e));
    }

    /**
     * 跟踪信息是否开启
     *
     * @return 是否开启
     */
    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    /**
     * 调试信息是否开启
     *
     * @return 是否开启
     */
    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * 普通信息是否开启
     *
     * @return 是否开启
     */
    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * 警告信息是否开启
     *
     * @return 是否开启
     */
    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    /**
     * 错误信息是否开启
     *
     * @return 是否开启
     */
    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }
}
