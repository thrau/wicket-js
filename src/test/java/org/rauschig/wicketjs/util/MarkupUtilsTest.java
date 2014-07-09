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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.parser.XmlTag;
import org.junit.Before;
import org.junit.Test;

public class MarkupUtilsTest {

    private ComponentTag tag;

    @Before
    public void setUp() throws Exception {
        tag = new ComponentTag("foo", XmlTag.TagType.OPEN);
    }

    @Test
    public void taqEquals_singleMatchingTag_returnsTrue() throws Exception {
        assertTrue(MarkupUtils.tagEquals(tag, "foo"));
    }

    @Test
    public void taqEquals_multipleMatchingTag_returnsTrue() throws Exception {
        assertTrue(MarkupUtils.tagEquals(tag, "ed", "foo", "baz"));
    }

    @Test
    public void taqEquals_singleNonMatchingTag_returnsFalse() throws Exception {
        assertFalse(MarkupUtils.tagEquals(tag, "bar"));
    }

    @Test
    public void taqEquals_multipleNonMatchingTag_returnsFalse() throws Exception {
        assertFalse(MarkupUtils.tagEquals(tag, "ed", "bar", "baz"));
    }

}
