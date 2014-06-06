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

import org.grayrabbit.jqs.core.Node;
import org.grayrabbit.jqs.core.compiler.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdaptiveClassCreatorTest {

    @Test
    public void testCreateAdaptivePluginCode(){
        AdaptiveClassCreator creator = new AdaptiveClassCreator(Node.class,"node");
        Assert.assertTrue(creator.createAdaptivePluginCode().length()>0);
    }
    @Test(expected = IllegalStateException.class)
    public void testCreateAdaptivePluginCodeThrowtException() {
        AdaptiveClassCreator creator = new AdaptiveClassCreator(org.grayrabbit.jqs.core.compiler.Compiler.class,"node");
        Assert.assertTrue(creator.createAdaptivePluginCode().length()>0);
    }
}