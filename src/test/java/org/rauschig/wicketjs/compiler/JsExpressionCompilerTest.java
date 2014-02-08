package org.rauschig.wicketjs.compiler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.JsExpression;
import org.rauschig.wicketjs.JsExpressionList;
import org.rauschig.wicketjs.JsFunction;
import org.rauschig.wicketjs.JsIdentifier;
import org.rauschig.wicketjs.JsLiteral;
import org.rauschig.wicketjs.JsNamedFunction;
import org.rauschig.wicketjs.compiler.JsExpressionCompiler;

/**
 * JsExpressionCompilerTest
 */
public class JsExpressionCompilerTest {
    @Test
    public void compileJsIdentifier_compilesCorrectly() throws Exception {
        compileAndAssert("this", new JsIdentifier("this"));
    }

    @Test
    public void compileJsString_compilesCorrectly() throws Exception {
        compileAndAssert("'string'", new JsLiteral.JsString("string"));
    }

    @Test
    public void compileJsBoolean_compilesCorrectly() throws Exception {
        compileAndAssert("true", new JsLiteral.JsBoolean(true));
        compileAndAssert("false", new JsLiteral.JsBoolean(false));
    }

    @Test
    public void compileJsNumber_Integer_compilesCorrectly() throws Exception {
        compileAndAssert("42", new JsLiteral.JsNumber(new Integer(42)));
        compileAndAssert("-42", new JsLiteral.JsNumber(new Integer(-42)));
    }

    @Test
    public void compileJsNumber_Long_compilesCorrectly() throws Exception {
        compileAndAssert("42", new JsLiteral.JsNumber(new Long(42)));
        compileAndAssert("-42", new JsLiteral.JsNumber(new Long(-42)));
    }

    @Test
    public void compileJsNumber_Float_compilesCorrectly() throws Exception {
        compileAndAssert("4.2", new JsLiteral.JsNumber(new Float(4.2)));
        compileAndAssert("-4.2", new JsLiteral.JsNumber(new Float(-4.2)));
    }

    @Test
    public void compileJsNumber_Double_compilesCorrectly() throws Exception {
        compileAndAssert("4.2", new JsLiteral.JsNumber(new Double(4.2)));
        compileAndAssert("-4.2", new JsLiteral.JsNumber(new Double(-4.2)));
    }

    @Test
    public void compileJsCall_withNoArgument_compilesCorrectly() throws Exception {
        compileAndAssert("call()", new JsCall("call"));
    }

    @Test
    public void compileJsCall_withSingleArgument_compilesCorrectly() throws Exception {
        compileAndAssert("call('arg')", new JsCall("call", "arg"));
    }

    @Test
    public void compileJsCall_withMultipleArguments_compilesCorrectly() throws Exception {
        JsCall call = new JsCall("call", "arg", 1, true, new JsIdentifier("this"));
        compileAndAssert("call('arg',1,true,this)", call);
    }

    @Test
    public void compileJsCall_withMultipleChainedArguments_compilesCorrectly() throws Exception {
        JsCall call = new JsCall("call", "arg", 1);

        call.arg(true);
        call.arg(new JsIdentifier("this"));

        compileAndAssert("call('arg',1,true,this)", call);
    }

    @Test
    public void compileJsExpression_compilesCorrectly() throws Exception {
        compileAndAssert("console.log('i like cheese');", new JsExpression("console.log('i like cheese');"));
    }

    @Test
    public void compileJsFunction_withNoParameters_compilesCorrectly() throws Exception {
        String expected = "function(){console.log(this)}";
        compileAndAssert(expected, new JsFunction("console.log(this)"));
    }

    @Test
    public void compileJsFunction_withSingleParameter_compilesCorrectly() throws Exception {
        String expected = "function(i){console.log(this)}";
        compileAndAssert(expected, new JsFunction("console.log(this)", "i"));
    }

    @Test
    public void compileJsFunction_withMultipleParameters_compilesCorrectly() throws Exception {
        String expected = "function(i,item,more){console.log(this)}";
        compileAndAssert(expected, new JsFunction("console.log(this)", "i", "item", "more"));
    }

    @Test
    public void compileJsNamedFunction_withNoParameters_compilesCorrectly() throws Exception {
        String expected = "function log(){console.log(this)}";
        compileAndAssert(expected, new JsNamedFunction("log", "console.log(this)"));
    }

    @Test
    public void compileJsNamedFunction_withSingleParameter_compilesCorrectly() throws Exception {
        String expected = "function log(i){console.log(this)}";
        compileAndAssert(expected, new JsNamedFunction("log", "console.log(this)", "i"));
    }

    @Test
    public void compileJsNamedFunction_withMultipleParameters_compilesCorrectly() throws Exception {
        String expected = "function log(i,item,more){console.log(this)}";
        compileAndAssert(expected, new JsNamedFunction("log", "console.log(this)", "i", "item", "more"));
    }

    @Test
    public void compileJsNamedFunction_withMultipleChainedParameters_compilesCorrectly() throws Exception {
        String expected = "function log(i,item,more){console.log(this)}";
        JsNamedFunction function = new JsNamedFunction("log", "console.log(this)", "i");

        function.param("item");
        function.param(new JsIdentifier("more"));

        compileAndAssert(expected, function);
    }

    @Test
    public void compileJsExpressionList_withSingleExpression_compilesCorrectly() throws Exception {
        String expected = "foo=bar;";
        IJsExpression expr = new JsExpressionList(new JsExpression("foo=bar"));

        compileAndAssert(expected, expr);
    }

    @Test
    public void compileJsExpressionList_withMultipleExpression_compilesCorrectly() throws Exception {
        String expected = "foo=bar;foo();call();";
        JsExpression expr = new JsExpression("foo=bar");

        JsExpressionList list = expr._(new JsCall("foo"));
        list._(new JsCall("call"));

        compileAndAssert(expected, list);
    }

    protected static void compileAndAssert(String expected, IJsExpression expression) {
        assertEquals(expected, new JsExpressionCompiler(expression).compile());
    }
}
