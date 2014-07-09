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

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.wicket.util.template.TextTemplate;
import org.junit.Test;
import org.mockito.Mockito;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.JsAssignment;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.JsCallChain;
import org.rauschig.wicketjs.JsExpression;
import org.rauschig.wicketjs.JsExpressionStatement;
import org.rauschig.wicketjs.JsFunction;
import org.rauschig.wicketjs.JsIdentifier;
import org.rauschig.wicketjs.JsIf;
import org.rauschig.wicketjs.JsLiteral;
import org.rauschig.wicketjs.JsNamedFunction;
import org.rauschig.wicketjs.JsReturn;
import org.rauschig.wicketjs.JsStatement;
import org.rauschig.wicketjs.JsStatements;
import org.rauschig.wicketjs.JsTemplate;
import org.rauschig.wicketjs.JsVariableDefinition;

@SuppressWarnings("unchecked")
public class JsCompilerTest {
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
    public void compileJsArray_ObjectArray_compilesCorrectly() throws Exception {
        compileAndAssert("[1,-2,3]", new JsLiteral.JsArray(new Integer[] { 1, -2, 3 }));
    }

    @Test
    public void compileJsArray_StringArray_compilesCorrectly() throws Exception {
        compileAndAssert("[\"a\",\"b\",\"c\"]", new JsLiteral.JsArray(new String[] { "a", "b", "c" }));
    }

    @Test
    public void compileJsArray_ArrayList_compilesCorrectly() throws Exception {
        compileAndAssert("[\"a\",\"b\",\"c\"]", new JsLiteral.JsArray(Arrays.asList("a", "b", "c")));
    }

    @Test
    public void compileJsObject_Map_compilesCorrectly() throws Exception {
        Map<Object, Object> map = new LinkedHashMap<>();
        map.put(1, 2);
        map.put("a", "b");

        compileAndAssert("{\"1\":2,\"a\":\"b\"}", new JsLiteral.JsObject(map));
    }

    @Test
    public void compileJsObject_NestedMap_compilesCorrectly() throws Exception {
        Map<Object, Object> nestedMap = new LinkedHashMap<>();
        nestedMap.put(3, 4);
        nestedMap.put("c", "d");

        Map<Object, Object> map = new LinkedHashMap<>();
        map.put(1, 2);
        map.put("a", "b");
        map.put("m", nestedMap);

        compileAndAssert("{\"1\":2,\"a\":\"b\",\"m\":{\"3\":4,\"c\":\"d\"}}", new JsLiteral.JsObject(map));
    }

    @Test
    public void compileJsNull_compilesCorrectly() throws Exception {
        compileAndAssert("null", JsLiteral.NULL);
    }

    @Test
    public void compileJsAssignment_expressionAssignment_compilesCorrectly() throws Exception {
        compileAndAssert("foo = bar()", new JsAssignment(new JsIdentifier("foo"), new JsCall("bar")));
    }

    @Test
    public void compileJsAssignment_literalAssignment_compilesCorrectly() throws Exception {
        compileAndAssert("foo = 'bar'", new JsAssignment("foo", "bar"));
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
    public void compileJsCall_withNullArgument_compilesCorrectly() throws Exception {
        compileAndAssert("call(null)", new JsCall("call", (Object) null));
        compileAndAssert("call('foo',null,'bar')", new JsCall("call", "foo", null, "bar"));
    }

    @Test
    public void compileJsCall_withMultipleChainedArguments_compilesCorrectly() throws Exception {
        JsCall call = new JsCall("call", "arg", 1);

        call.arg(true);
        call.arg(new JsIdentifier("this"));

        compileAndAssert("call('arg',1,true,this)", call);
    }

    @Test
    public void compileJsCallChain_withSingleCall_compilesCorrectly() throws Exception {
        JsCallChain call = new JsCallChain(new JsCall("call"));

        compileAndAssert("call()", call);
    }

    @Test
    public void compileJsCallChain_withSingleIdentifier_compilesCorrectly() throws Exception {
        JsCallChain call = new JsCallChain(new JsIdentifier("this"));

        compileAndAssert("this", call);
    }

    @Test
    public void compileJsCallChain_withChainedCalls_compilesCorrectly() throws Exception {
        JsCallChain call = new JsCallChain();

        call.chain(new JsCall("call0"));
        call.call("call1", "arg", 1, true);
        call.call("call2");
        call.call("call3", new JsIdentifier("this"));

        compileAndAssert("call0().call1('arg',1,true).call2().call3(this)", call);
    }

    @Test
    public void compileJsCallChain_withChainedIdentifiers_compilesCorrectly() throws Exception {
        JsCallChain call = new JsCallChain();

        call.chain("this");
        call.chain(new JsIdentifier("that"));
        call.chain(new JsIdentifier("and"), new JsIdentifier("thenextthing"));
        call._("field");

        compileAndAssert("this.that.and.thenextthing.field", call);
    }

    @Test
    public void compileJsCallChain_withMixedChains_compilesCorrectly() throws Exception {
        JsCallChain call = new JsCallChain();

        call.chain("document");
        call.chain("window");
        call.call("onload", new JsFunction(new JsCallChain()._("console")._("log", new JsIdentifier("this"))));

        compileAndAssert("document.window.onload(function(){console.log(this);})", call);
    }

    @Test
    public void compileJsExpression_compilesCorrectly() throws Exception {
        compileAndAssert("console.log('i like cheese');", new JsExpression("console.log('i like cheese');"));
    }

    @Test
    public void compileJsFunction_withNoParameters_compilesCorrectly() throws Exception {
        String expected = "function(){console.log(this);}";
        compileAndAssert(expected, new JsFunction("console.log(this)"));
    }

    @Test
    public void compileJsFunction_withSingleParameter_compilesCorrectly() throws Exception {
        String expected = "function(i){console.log(this);}";
        compileAndAssert(expected, new JsFunction("console.log(this)", "i"));
    }

    @Test
    public void compileJsFunction_withMultipleParameters_compilesCorrectly() throws Exception {
        String expected = "function(i,item,more){console.log(this);}";
        compileAndAssert(expected, new JsFunction("console.log(this)", "i", "item", "more"));
    }

    @Test
    public void compileJsNamedFunction_withNoParameters_compilesCorrectly() throws Exception {
        String expected = "function log(){console.log(this);}";
        compileAndAssert(expected, new JsNamedFunction("log", "console.log(this)"));
    }

    @Test
    public void compileJsNamedFunction_withSingleParameter_compilesCorrectly() throws Exception {
        String expected = "function log(i){console.log(this);}";
        compileAndAssert(expected, new JsNamedFunction("log", "console.log(this)", "i"));
    }

    @Test
    public void compileJsNamedFunction_withMultipleParameters_compilesCorrectly() throws Exception {
        String expected = "function log(i,item,more){console.log(this);}";
        compileAndAssert(expected, new JsNamedFunction("log", "console.log(this)", "i", "item", "more"));
    }

    @Test
    public void compileJsNamedFunction_withMultipleChainedParameters_compilesCorrectly() throws Exception {
        String expected = "function log(i,item,more){console.log(this);}";
        JsNamedFunction function = new JsNamedFunction("log", "console.log(this)", "i");

        function.param("item");
        function.param(new JsIdentifier("more"));

        compileAndAssert(expected, function);
    }

    @Test
    public void compileJsTemplate_returnsTemplateContent() throws Exception {
        TextTemplate template = Mockito.mock(TextTemplate.class);
        Mockito.when(template.asString(Mockito.anyMap())).thenReturn("var life = undefined");
        JsTemplate jsTemplate = new JsTemplate(template);

        compileAndAssert("var life = undefined", jsTemplate);
    }

    @Test
    public void compileJsStatement_compilesCorrectly() throws Exception {
        compileAndAssert("call();", new JsStatement("call()"));
    }

    @Test
    public void compileJsStatementExpression_compilesCorrectly() throws Exception {
        compileAndAssert("call();", new JsExpressionStatement(new JsCall("call")));
    }

    @Test
    public void compileJsStatements_withSingleStatement_compilesCorrectly() throws Exception {
        compileAndAssert("call();", new JsStatements(new JsStatement("call()")));
    }

    @Test
    public void compileJsStatements_withMultipleStatements_compilesCorrectly() throws Exception {
        JsStatements statements;

        statements =
            new JsCall("call").terminate()._("if(true){}")._(new JsCall("console.log", new JsIdentifier("this")))
                    ._(new JsStatement("break"));

        compileAndAssert("call();if(true){};console.log(this);break;", statements);
    }

    @Test
    public void terminatedJsExpression_compilesCorrectly() throws Exception {
        compileAndAssert("call();", new JsCall("call").terminate());
    }

    @Test
    public void compileJsIf_withoutElseBlock_compilesCorrectly() throws Exception {
        JsIf statement = new JsIf("someCondition()", new JsCall("someAction").terminate());

        compileAndAssert("if(someCondition()){someAction();}", statement);
    }

    @Test
    public void compileJsIf_withElseBlock_compilesCorrectly() throws Exception {
        JsIf statement =
            new JsIf(new JsCall("someCondition"), new JsCall("someAction").terminate(), new JsStatements(new JsCall(
                    "someAlternative"), new JsStatement("return")));

        compileAndAssert("if(someCondition()){someAction();}else{someAlternative();return;}", statement);
    }

    @Test
    public void compileJsReturn_withoutExpression_compilesCorrectly() throws Exception {
        compileAndAssert("return;", new JsReturn());
    }

    @Test
    public void compileJsReturn_withExpression_compilesCorrectly() throws Exception {
        compileAndAssert("return foo.bar();", new JsReturn("foo.bar()"));
    }

    @Test
    public void compileJsReturn_withValue_compilesCorrectly() throws Exception {
        compileAndAssert("return true;", new JsReturn(true));
        compileAndAssert("return 42;", new JsReturn(42));
    }

    @Test
    public void compileJsReturn_withNullValue_compilesCorrectly() throws Exception {
        compileAndAssert("return null;", new JsReturn((Object) null));
    }

    @Test
    public void compileJsVariableDefinition_simpleDefinition_compilesCorrectly() throws Exception {
        JsVariableDefinition statement = new JsVariableDefinition("foo");

        compileAndAssert("var foo;", statement);
    }

    @Test
    public void compileJsVariableDefinition_withLiteralAssignment_compilesCorrectly() throws Exception {
        compileAndAssert("var foo = 'bar';", new JsVariableDefinition("foo", "bar"));
        compileAndAssert("var foo = 'bar';", new JsVariableDefinition("foo", new JsLiteral.JsString("bar")));
    }

    @Test
    public void compileJsVariableDefinition_withNullAssignment_compilesCorrectly() throws Exception {
        compileAndAssert("var foo = null;", new JsVariableDefinition("foo", null));
    }

    @Test
    public void compileJsVariableDefinition_withExpressionAssignment_compilesCorrectly() throws Exception {
        compileAndAssert("var foo = this;", new JsVariableDefinition("foo", JsExpression.THIS));
    }

    protected static void compileAndAssert(String expected, IJavaScript expression) {
        assertEquals(expected, new JsCompiler(expression).compile());
    }
}
