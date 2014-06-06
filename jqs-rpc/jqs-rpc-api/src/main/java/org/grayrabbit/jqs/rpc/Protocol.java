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

package org.grayrabbit.jqs.rpc;

import org.grayrabbit.jqs.core.Url;

/**
 * Write class comments here
 * <p/>
 * Author:sys53
 * DATE 14-5-30 上午9:38
 * version $Id:Protocol.java,v 0.1.Exp $
 */
public interface Protocol {

    int getDefaultPort();

    <T> Provider<T> export(Invoker<T> invoker) throws RpcException;

    void destroy();

    <T> Invoker<T> pick(Class<T> type, Url url) throws RpcException;

}
