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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.JsIdentifier;
import org.rauschig.wicketjs.JsLiteral;

/**
 * JsUtilsTest
 */
public class JsUtilsTest {
    @Test
    public void asIdentifierList() throws Exception {
    }

    @Test
    public void asArgumentList_withPrimitives_returnsLiterals() throws Exception {
        List<IJsExpression> list = JsUtils.asArgumentList("foo", 42, false);

        assertEquals(JsLiteral.JsString.class, list.get(0).getClass());
        assertEquals(JsLiteral.JsNumber.class, list.get(1).getClass());
        assertEquals(JsLiteral.JsBoolean.FALSE, list.get(2));
    }

    @Test
    public void asArgumentList_withIJsExpressions_returnsExpressions() throws Exception {
        JsIdentifier foo = new JsIdentifier("foo");
        JsIdentifier bar = new JsIdentifier("bar");

        List<IJsExpression> list = JsUtils.asArgumentList(foo, bar);

        assertEquals(foo, list.get(0));
        assertEquals(bar, list.get(1));
    }

    @Test
    public void asArgumentList_withIJsStatements_returnsStatements() throws Exception {
        JsIdentifier foo = new JsIdentifier("foo");
        JsIdentifier bar = new JsIdentifier("bar");

        List<IJsExpression> list = JsUtils.asArgumentList(foo, bar);

        assertEquals(foo, list.get(0));
        assertEquals(bar, list.get(1));
    }

    @Test
    public void asArgument_primitive_returnsLiteral() throws Exception {
        IJsExpression arg = JsUtils.asArgument("string");

        assertEquals(JsLiteral.JsString.class, arg.getClass());
        assertEquals("string", ((JsLiteral) arg).getValue());
    }

    @Test
    public void asArgument_identifier_returnsIdentifier() throws Exception {
        JsIdentifier identifier = new JsIdentifier("foo");
        IJsExpression arg = JsUtils.asArgument(identifier);

        assertEquals(identifier, arg);
    }

    @Test
    public void asArgument_literal_returnsThatLiteral() throws Exception {
        JsLiteral<?> literal = new JsLiteral.JsString("foo");

        IJsExpression arg = JsUtils.asArgument(literal);

        assertEquals(literal, arg);
    }

    @Test
    public void asLiteral_literal_returnsThatLiteral() throws Exception {
        JsLiteral<?> value = new JsLiteral.JsString("foo");
        JsLiteral<?> literal = JsUtils.asLiteral(value);

        assertEquals(value, literal);
    }

    @Test
    public void asLiteral_string_returnsCorrectLiteral() throws Exception {
        JsLiteral<?> literal = JsUtils.asLiteral("Foo");

        assertEquals(JsLiteral.JsString.class, literal.getClass());
        assertEquals("Foo", literal.getValue());
    }

    @Test
    public void asLiteral_number_returnsCorrectLiteral() throws Exception {
        JsLiteral<?> literal = JsUtils.asLiteral(42);

        assertEquals(JsLiteral.JsNumber.class, literal.getClass());
        assertEquals(42, literal.getValue());
    }

    @Test
    public void asLiteral_boolean_returnsCorrectLiteral() throws Exception {
        JsLiteral<?> literal = JsUtils.asLiteral(false);

        assertEquals(JsLiteral.JsBoolean.FALSE, literal);
    }

    @Test
    public void asLiteral_array_returnsCorrectLiteral() throws Exception {
        JsLiteral<?> literal = JsUtils.asLiteral(new Object[] { "a" });

        assertEquals(JsLiteral.JsArray.class, literal.getClass());
        assertEquals("a", ((JsLiteral.JsArray) literal).getValue()[0]);
    }

    @Test
    public void asLiteral_list_returnsCorrectLiteral() throws Exception {
        JsLiteral<?> literal = JsUtils.asLiteral(Arrays.asList("a"));

        assertEquals(JsLiteral.JsArray.class, literal.getClass());
        assertEquals("a", ((JsLiteral.JsArray) literal).getValue()[0]);
    }

    @Test
    public void asLiteral_map_returnsCorrectLiteral() throws Exception {
        Map<Object, Object> map = new HashMap<>();

        JsLiteral<?> literal = JsUtils.asLiteral(map);

        assertEquals(JsLiteral.JsObject.class, literal.getClass());
    }

}
