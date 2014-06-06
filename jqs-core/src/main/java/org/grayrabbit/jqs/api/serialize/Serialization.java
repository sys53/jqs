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

package org.grayrabbit.jqs.api.serialize;

import org.grayrabbit.jqs.core.Url;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 序列化接口
 * <p/>
 * User: sys53
 * Date: 14-5-28 上午9:41
 * version $Id: Serialization.java, v 0.1 Exp $
 */
public interface Serialization extends ObjectInput, ObjectOutput {
    /**
     * create serializer
     * @param url
     * @param output
     * @return serializer
     * @throws IOException
     */
    ObjectOutput serialize(Url url, OutputStream output) throws IOException;

    /**
     * create deserializer
     * @param url
     * @param input
     * @return deserializer
     * @throws IOException
     */
    ObjectInput deserialize(Url url, InputStream input) throws IOException;
}
