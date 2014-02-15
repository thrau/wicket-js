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
package org.rauschig.wicketjs.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * StringsTest
 */
public class StringsTest {
    @Test
    public void join_withNull_returnsEmptyString() throws Exception {
        assertEquals(Strings.EMPTY_STRING, Strings.join(null, ","));
    }

    @Test
    public void join_withEmptyArray_returnsEmptyString() throws Exception {
        assertEquals(Strings.EMPTY_STRING, Strings.join(new Object[0], ","));
    }

    @Test
    public void join_withEmptyDelimiter_returnsCorrectString() throws Exception {
        assertEquals("123", Strings.join(new Integer[] { 1, 2, 3 }, ""));
    }

    @Test
    public void join_returnsCorrectString() throws Exception {
        assertEquals("1--2--3", Strings.join(new Integer[] { 1, 2, 3 }, "--"));
    }
}
