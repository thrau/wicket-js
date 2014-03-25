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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.rauschig.wicketjs.JsLiteral.JsArray;
import static org.rauschig.wicketjs.JsLiteral.JsBoolean;
import static org.rauschig.wicketjs.JsLiteral.JsNumber;
import static org.rauschig.wicketjs.JsLiteral.JsString;
import static org.rauschig.wicketjs.JsLiteral.of;

import org.junit.Test;

public class JsLiteralTest {

    @Test
    public void of_boolean_returnsBooleanConstant() throws Exception {
        assertSame(JsBoolean.FALSE, of(false));
        assertSame(JsBoolean.TRUE, of(true));
    }

    @Test
    public void equals_number_behavesCorrectly() throws Exception {
        JsNumber one = new JsNumber(1);

        assertTrue(one.equals(one));

        assertTrue(one.equals(of(1)));
        assertFalse(one.equals(new JsNumber(2)));
        assertFalse(one.equals(of(1.1)));

        assertTrue(one.equals(1));
        assertFalse(one.equals("two"));
        assertFalse(one.equals(null));
    }

    @Test
    public void equals_string_behavesCorrectly() throws Exception {
        JsString foo = new JsString("foo");

        assertTrue(foo.equals(foo));

        assertTrue(foo.equals(of("foo")));
        assertFalse(foo.equals(new JsString("bar")));
        assertFalse(foo.equals(new JsString(null)));

        assertTrue(foo.equals("foo"));
        assertFalse(foo.equals(null));
    }

    @Test
    public void equals_array_behavesCorrectly() throws Exception {
        String[] arr1 = { "foo", "bar" };
        String[] arr2 = { "baz", "bar" };

        JsArray jsArray = new JsArray(arr1);

        assertTrue(jsArray.equals(jsArray));

        assertTrue(jsArray.equals(of(arr1)));
        assertFalse(jsArray.equals(new JsArray(arr2)));

        assertTrue(jsArray.equals(arr1));
        assertFalse(jsArray.equals(arr2));
        assertFalse(jsArray.equals(null));
    }
}
