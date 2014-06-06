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

package org.grayrabbit.jqs.cluster;

import org.grayrabbit.jqs.core.Url;
import org.grayrabbit.jqs.rpc.Invocation;
import org.grayrabbit.jqs.rpc.Invoker;
import org.grayrabbit.jqs.rpc.RpcException;

import java.util.List;

/**
 * Write class comments here
 * <p/>
 * Author:sys53
 * DATE 14-5-30 下午4:49
 * version $Id:Router.java,v 0.1.Exp $
 */
public interface Router {
    <T> void route(List<Invoker<T>> invokers, Url url, Invocation invocation) throws RpcException;
}
