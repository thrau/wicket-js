package org.rauschig.wicketjs.compiler;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.JsIdentifier;

/**
 * JsExpressionJoinerTest
 */
public class JsExpressionJoinerTest {

    @Test
    public void multipleExpressions_compilesCorrectly() throws Exception {
        List<IJsExpression> expressions = new ArrayList<>();

        expressions.add(new JsIdentifier("$(this)"));
        expressions.add(new JsCall("parent"));
        expressions.add(new JsCall("find", "#id"));
        expressions.add(new JsCall("toggleClass", "foo", "bar"));

        String result = new JsExpressionJoiner(expressions, ".").compile();

        assertEquals("$(this).parent().find('#id').toggleClass('foo','bar')", result);
    }

    @Test
    public void singleExpression_compilesCorrectly() throws Exception {
        List<IJsExpression> expressions = new ArrayList<>();

        expressions.add(new JsCall("foo"));

        String result = new JsExpressionJoiner(expressions, ".").compile();

        assertEquals("foo()", result);
    }

    @Test
    public void emptyList_compilesToEmptyString() throws Exception {
        List<IJsExpression> expressions = new ArrayList<>();

        String result = new JsExpressionJoiner(expressions, ".").compile();

        assertEquals("", result);
    }

    @Test
    public void nullList_compilesToEmptyString() throws Exception {
        String result = new JsExpressionJoiner(null, ".").compile();

        assertEquals("", result);
    }

}
