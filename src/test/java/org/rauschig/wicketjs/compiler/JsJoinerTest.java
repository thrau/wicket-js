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
package org.rauschig.wicketjs.compiler;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.JsIdentifier;

/**
 * JsJoinerTest
 */
public class JsJoinerTest {

    @Test
    public void multipleExpressions_compilesCorrectly() throws Exception {
        List<IJsExpression> expressions = new ArrayList<>();

        expressions.add(new JsIdentifier("$(this)"));
        expressions.add(new JsCall("parent"));
        expressions.add(new JsCall("find", "#id"));
        expressions.add(new JsCall("toggleClass", "foo", "bar"));

        String result = new JsJoiner<>(expressions, ".").compile();

        assertEquals("$(this).parent().find('#id').toggleClass('foo','bar')", result);
    }

    @Test
    public void singleExpression_compilesCorrectly() throws Exception {
        List<IJsExpression> expressions = new ArrayList<>();

        expressions.add(new JsCall("foo"));

        String result = new JsJoiner<>(expressions, ".").compile();

        assertEquals("foo()", result);
    }

    @Test
    public void emptyList_compilesToEmptyString() throws Exception {
        List<IJsExpression> expressions = new ArrayList<>();

        String result = new JsJoiner<>(expressions, ".").compile();

        assertEquals("", result);
    }

    @Test
    public void nullList_compilesToEmptyString() throws Exception {
        String result = new JsJoiner<>(null, ".").compile();

        assertEquals("", result);
    }

}
