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

/**
 * Write class comments here
 * <p/>
 * Author:sys53
 * DATE 14-6-3 上午10:23
 * version $Id:Merger.java,v 0.1.Exp $
 */
public interface Merger<T> {
    T merge(T... items);
}