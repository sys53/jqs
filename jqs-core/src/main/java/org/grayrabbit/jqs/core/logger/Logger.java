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

/**
 * 统一日志接口
 * <p/>
 * Author:sys53
 * DATE 14-6-3 下午5:22
 * version $Id:Logger.java,v 0.1.Exp $
 */
public interface Logger {
    /**
     * 输出跟踪信息
     *
     * @param format 格式化信息内容
     * @param args 信息参数
     * @since 1.0
     */
    void trace(String format, Object... args);

    /**
     * 输出跟踪信息
     *
     * @param e 异常信息
     * @since 1.0
     */
    void trace(Throwable e);

    /**
     * 输出跟踪信息
     * @param format 格式化信息内容
     * @param e 异常信息
     * @param args 信息参数
     * @since 1.0
     */
    void trace(String format, Throwable e, Object... args);

    /**
     * 输出调试信息
     *
     * @param format 格式化信息内容
     * @param args 信息参数
     * @since 1.0
     */
    void debug(String format, Object... args);

    /**
     * 输出调试信息
     *
     * @param e 异常信息
     * @since 1.0
     */
    void debug(Throwable e);

    /**
     * 输出调试信息
     * @param format 格式化信息内容
     * @param e 异常信息
     * @param args 信息参数
     * @since 1.0
     */
    void debug(String format, Throwable e, Object... args);

    /**
     * 输出普通信息
     *
     * @param format 格式化信息内容
     * @param args 信息参数
     * @since 1.0
     */
    void info(String format, Object... args);

    /**
     * 输出普通信息
     *
     * @param e 异常信息
     * @since 1.0
     */
    void info(Throwable e);

    /**
     * 输出普通信息
     *
     * @param format 格式化信息内容
     * @param e 异常信息
     * @param args 信息参数
     * @since 1.0
     */
    void info(String format, Throwable e, Object... args);

    /**
     * 输出警告信息
     *
     * @param format 格式化信息内容
     * @param args 信息参数
     * @since 1.0
     */
    void warn(String format, Object... args);

    /**
     * 输出警告信息
     *
     * @param e 异常信息
     * @since 1.0
     */
    void warn(Throwable e);

    /**
     * 输出警告信息
     *
     * @param format 格式化信息内容
     * @param e 异常信息
     * @param args 信息参数
     * @since 1.0
     */
    void warn(String format, Throwable e, Object... args);

    /**
     * 输出错误信息
     *
     * @param format 格式化信息内容
     * @param args 信息参数
     * @since 1.0
     */
    void error(String format, Object... args);

    /**
     * 输出错误信息
     *
     * @param e 异常信息
     * @since 1.0
     */
    void error(Throwable e);

    /**
     * 输出错误信息
     *
     * @param format 格式化信息内容
     * @param e 异常信息
     * @param args 信息参数
     * @since 1.0
     */
    void error(String format, Throwable e, Object... args);

    /**
     * 跟踪信息是否开启
     *
     * @return 是否开启
     * @since 1.0
     */
    boolean isTraceEnabled();

    /**
     * 调试信息是否开启
     *
     * @return 是否开启
     * @since 1.0
     */
    boolean isDebugEnabled();

    /**
     * 普通信息是否开启
     *
     * @return 是否开启
     * @since 1.0
     */
    boolean isInfoEnabled();

    /**
     * 警告信息是否开启
     *
     * @return 是否开启
     * @since 1.0
     */
    boolean isWarnEnabled();

    /**
     * 错误信息是否开启
     *
     * @return 是否开启
     * @since 1.0
     */
    boolean isErrorEnabled();
}
