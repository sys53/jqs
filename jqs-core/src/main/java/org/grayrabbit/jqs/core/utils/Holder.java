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

package org.grayrabbit.jqs.core.utils;

/**
 * 用于线性安全（同步）帮助类
 * <p/>
 * Author:sys53
 * DATE 14-6-3 下午4:30
 * version $Id:Holder.java,v 0.1.Exp $
 */
public final class Holder<T> {
    private volatile T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
