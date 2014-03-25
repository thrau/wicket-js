/**
 *    Copyright 2014 Thomas Rausch
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.rauschig.wicketjs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JsIdentifierTest
 */
public class JsIdentifierTest {

    @Test(expected = IllegalArgumentException.class)
    public void construct_withEmptyString_throwsException() throws Exception {
        new JsIdentifier("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void construct_withNullString_throwsException() throws Exception {
        new JsIdentifier(null);
    }

    @Test
    public void equals_sameObject_returnsTrue() throws Exception {
        JsIdentifier i = new JsIdentifier("this");
        assertTrue(i.equals(i));
    }

    @Test
    public void equals_sameIdentifierString_returnsTrue() throws Exception {
        JsIdentifier i = new JsIdentifier("this");
        assertTrue(i.equals("this"));
    }

    @Test
    public void equals_sameIdentifier_returnsTrue() throws Exception {
        JsIdentifier a = new JsIdentifier("this");
        JsIdentifier b = new JsIdentifier("this");

        assertTrue(a.equals(b));
    }

    @Test
    public void equals_differentIdentifier_returnsFalse() throws Exception {
        JsIdentifier a = new JsIdentifier("this");
        JsIdentifier b = new JsIdentifier("that");
        assertFalse(a.equals(b));
    }

    @Test
    public void equals_null_returnsFalse() throws Exception {
        JsIdentifier i = new JsIdentifier("this");
        assertFalse(i.equals(null));
    }
}
