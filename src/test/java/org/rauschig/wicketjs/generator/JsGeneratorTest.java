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
package org.rauschig.wicketjs.generator;

import static org.hamcrest.core.Is.is;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.wicket.util.template.TextTemplate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
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
import org.rauschig.wicketjs.JsIfNot;
import org.rauschig.wicketjs.JsLiteral;
import org.rauschig.wicketjs.JsNamedFunction;
import org.rauschig.wicketjs.JsReturn;
import org.rauschig.wicketjs.JsStatement;
import org.rauschig.wicketjs.JsStatements;
import org.rauschig.wicketjs.JsTemplate;
import org.rauschig.wicketjs.JsVariableDefinition;

@SuppressWarnings("unchecked")
public class JsGeneratorTest {

    @Rule
    public ErrorCollector errors = new ErrorCollector();

    @Test
    public void generateJsIdentifier_compilesCorrectly() throws Exception {
        generateAndAssert("this", new JsIdentifier("this"));
    }

    @Test
    public void generateJsString_compilesCorrectly() throws Exception {
        generateAndAssert("'string'", new JsLiteral.JsString("string"));
    }

    @Test
    public void generateJsString_withSingleQuotes_compilesCorrectly() throws Exception {
        generateAndAssert("'string\\'s'", new JsLiteral.JsString("string's"));
    }

    @Test
    public void generateJsString_withSingleEscapedQuotes_compilesCorrectly() throws Exception {
        generateAndAssert("'string\\\\'s'", new JsLiteral.JsString("string\\'s"));
    }

    @Test
    public void generateJsString_withSingleQuotesEscapedSlash_compilesCorrectly() throws Exception {
        generateAndAssert("'string\\\\\\'s'", new JsLiteral.JsString("string\\\\'s"));
    }

    @Test
    public void generateJsString_withDoubleQuotes_compilesCorrectly() throws Exception {
        generateAndAssert("'string\"s'", new JsLiteral.JsString("string\"s"));
    }

    @Test
    public void generateJsString_withDoubleEscapedQuotes_compilesCorrectly() throws Exception {
        generateAndAssert("'string\\\"s'", new JsLiteral.JsString("string\\\"s"));
    }

    @Test
    public void generateJsBoolean_compilesCorrectly() throws Exception {
        generateAndAssert("true", new JsLiteral.JsBoolean(true));
        generateAndAssert("false", new JsLiteral.JsBoolean(false));
    }

    @Test
    public void generateJsNumber_Integer_compilesCorrectly() throws Exception {
        generateAndAssert("42", new JsLiteral.JsNumber(new Integer(42)));
        generateAndAssert("-42", new JsLiteral.JsNumber(new Integer(-42)));
    }

    @Test
    public void generateJsNumber_Long_compilesCorrectly() throws Exception {
        generateAndAssert("42", new JsLiteral.JsNumber(new Long(42)));
        generateAndAssert("-42", new JsLiteral.JsNumber(new Long(-42)));
    }

    @Test
    public void generateJsNumber_Float_compilesCorrectly() throws Exception {
        generateAndAssert("4.2", new JsLiteral.JsNumber(new Float(4.2)));
        generateAndAssert("-4.2", new JsLiteral.JsNumber(new Float(-4.2)));
    }

    @Test
    public void generateJsNumber_Double_compilesCorrectly() throws Exception {
        generateAndAssert("4.2", new JsLiteral.JsNumber(new Double(4.2)));
        generateAndAssert("-4.2", new JsLiteral.JsNumber(new Double(-4.2)));
    }

    @Test
    public void generateJsArray_ObjectArray_compilesCorrectly() throws Exception {
        generateAndAssert("[1,-2,3]", new JsLiteral.JsArray(new Integer[] { 1, -2, 3 }));
    }

    @Test
    public void generateJsArray_StringArray_compilesCorrectly() throws Exception {
        generateAndAssert("[\"a\",\"b\",\"c\"]", new JsLiteral.JsArray(new String[] { "a", "b", "c" }));
    }

    @Test
    public void generateJsArray_ArrayList_compilesCorrectly() throws Exception {
        generateAndAssert("[\"a\",\"b\",\"c\"]", new JsLiteral.JsArray(Arrays.asList("a", "b", "c")));
    }

    @Test
    public void generateJsObject_Map_compilesCorrectly() throws Exception {
        Map<Object, Object> map = new LinkedHashMap<>();
        map.put(1, 2);
        map.put("a", "b");

        generateAndAssert("{\"1\":2,\"a\":\"b\"}", new JsLiteral.JsObject(map));
    }

    @Test
    public void generateJsObject_NestedMap_compilesCorrectly() throws Exception {
        Map<Object, Object> nestedMap = new LinkedHashMap<>();
        nestedMap.put(3, 4);
        nestedMap.put("c", "d");

        Map<Object, Object> map = new LinkedHashMap<>();
        map.put(1, 2);
        map.put("a", "b");
        map.put("m", nestedMap);

        generateAndAssert("{\"1\":2,\"a\":\"b\",\"m\":{\"3\":4,\"c\":\"d\"}}", new JsLiteral.JsObject(map));
    }

    @Test
    public void generateJsNull_compilesCorrectly() throws Exception {
        generateAndAssert("null", JsLiteral.NULL);
    }

    @Test
    public void generateJsAssignment_expressionAssignment_compilesCorrectly() throws Exception {
        generateAndAssert("foo = bar()", new JsAssignment(new JsIdentifier("foo"), new JsCall("bar")));
    }

    @Test
    public void generateJsAssignment_literalAssignment_compilesCorrectly() throws Exception {
        generateAndAssert("foo = 'bar'", new JsAssignment("foo", "bar"));
    }

    @Test
    public void generateJsCall_withNoArgument_compilesCorrectly() throws Exception {
        generateAndAssert("call()", new JsCall("call"));
    }

    @Test
    public void generateJsCall_withSingleArgument_compilesCorrectly() throws Exception {
        generateAndAssert("call('arg')", new JsCall("call", "arg"));
    }

    @Test
    public void generateJsCall_withMultipleArguments_compilesCorrectly() throws Exception {
        JsCall call = new JsCall("call", "arg", 1, true, new JsIdentifier("this"));
        generateAndAssert("call('arg',1,true,this)", call);
    }

    @Test
    public void generateJsCall_withNullArgument_compilesCorrectly() throws Exception {
        generateAndAssert("call(null)", new JsCall("call", (Object) null));
        generateAndAssert("call('foo',null,'bar')", new JsCall("call", "foo", null, "bar"));
    }

    @Test
    public void generateJsCall_withMultipleChainedArguments_compilesCorrectly() throws Exception {
        JsCall call = new JsCall("call", "arg", 1);

        call.arg(true);
        call.arg(new JsIdentifier("this"));

        generateAndAssert("call('arg',1,true,this)", call);
    }

    @Test
    public void generateJsCallChain_withSingleCall_compilesCorrectly() throws Exception {
        JsCallChain call = new JsCallChain(new JsCall("call"));

        generateAndAssert("call()", call);
    }

    @Test
    public void generateJsCallChain_withSingleIdentifier_compilesCorrectly() throws Exception {
        JsCallChain call = new JsCallChain(new JsIdentifier("this"));

        generateAndAssert("this", call);
    }

    @Test
    public void generateJsCallChain_withChainedCalls_compilesCorrectly() throws Exception {
        JsCallChain call = new JsCallChain();

        call.chain(new JsCall("call0"));
        call.call("call1", "arg", 1, true);
        call.call("call2");
        call.call("call3", new JsIdentifier("this"));

        generateAndAssert("call0().call1('arg',1,true).call2().call3(this)", call);
    }

    @Test
    public void generateJsCallChain_withChainedIdentifiers_compilesCorrectly() throws Exception {
        JsCallChain call = new JsCallChain();

        call.chain("this");
        call.chain(new JsIdentifier("that"));
        call.chain(new JsIdentifier("and"), new JsIdentifier("thenextthing"));
        call._("field");

        generateAndAssert("this.that.and.thenextthing.field", call);
    }

    @Test
    public void generateJsCallChain_withMixedChains_compilesCorrectly() throws Exception {
        JsCallChain call = new JsCallChain();

        call.chain("document");
        call.chain("window");
        call.call("onload", new JsFunction(new JsCallChain()._("console")._("log", new JsIdentifier("this"))));

        generateAndAssert("document.window.onload(function(){console.log(this);})", call);
    }

    @Test
    public void generateJsExpression_compilesCorrectly() throws Exception {
        generateAndAssert("console.log('i like cheese');", new JsExpression("console.log('i like cheese');"));
    }

    @Test
    public void generateJsFunction_withNoParameters_compilesCorrectly() throws Exception {
        String expected = "function(){console.log(this);}";
        generateAndAssert(expected, new JsFunction("console.log(this)"));
    }

    @Test
    public void generateJsFunction_withSingleParameter_compilesCorrectly() throws Exception {
        String expected = "function(i){console.log(this);}";
        generateAndAssert(expected, new JsFunction("console.log(this)", "i"));
    }

    @Test
    public void generateJsFunction_withMultipleParameters_compilesCorrectly() throws Exception {
        String expected = "function(i,item,more){console.log(this);}";
        generateAndAssert(expected, new JsFunction("console.log(this)", "i", "item", "more"));
    }

    @Test
    public void generateJsNamedFunction_withNoParameters_compilesCorrectly() throws Exception {
        String expected = "function log(){console.log(this);}";
        generateAndAssert(expected, new JsNamedFunction("log", "console.log(this)"));
    }

    @Test
    public void generateJsNamedFunction_withSingleParameter_compilesCorrectly() throws Exception {
        String expected = "function log(i){console.log(this);}";
        generateAndAssert(expected, new JsNamedFunction("log", "console.log(this)", "i"));
    }

    @Test
    public void generateJsNamedFunction_withMultipleParameters_compilesCorrectly() throws Exception {
        String expected = "function log(i,item,more){console.log(this);}";
        generateAndAssert(expected, new JsNamedFunction("log", "console.log(this)", "i", "item", "more"));
    }

    @Test
    public void generateJsNamedFunction_withMultipleChainedParameters_compilesCorrectly() throws Exception {
        String expected = "function log(i,item,more){console.log(this);}";
        JsNamedFunction function = new JsNamedFunction("log", "console.log(this)", "i");

        function.param("item");
        function.param(new JsIdentifier("more"));

        generateAndAssert(expected, function);
    }

    @Test
    public void generateJsTemplate_returnsTemplateContent() throws Exception {
        TextTemplate template = Mockito.mock(TextTemplate.class);
        Mockito.when(template.asString(Mockito.anyMap())).thenReturn("var life = undefined");
        JsTemplate jsTemplate = new JsTemplate(template);

        generateAndAssert("var life = undefined", jsTemplate);
    }

    @Test
    public void generateJsStatement_compilesCorrectly() throws Exception {
        generateAndAssert("call();", new JsStatement("call()"));
    }

    @Test
    public void generateJsStatementExpression_compilesCorrectly() throws Exception {
        generateAndAssert("call();", new JsExpressionStatement(new JsCall("call")));
    }

    @Test
    public void generateJsStatements_withSingleStatement_compilesCorrectly() throws Exception {
        generateAndAssert("call();", new JsStatements(new JsStatement("call()")));
    }

    @Test
    public void generateJsStatements_withMultipleStatements_compilesCorrectly() throws Exception {
        JsStatements statements;

        statements =
            new JsCall("call").terminate()._("if(true){}")._(new JsCall("console.log", new JsIdentifier("this")))
                    ._(new JsStatement("break"));

        generateAndAssert("call();if(true){};console.log(this);break;", statements);
    }

    @Test
    public void terminatedJsExpression_compilesCorrectly() throws Exception {
        generateAndAssert("call();", new JsCall("call").terminate());
    }

    @Test
    public void generateJsIf_withoutElseBlock_compilesCorrectly() throws Exception {
        JsIf statement = new JsIf("someCondition()", new JsCall("someAction").terminate());

        generateAndAssert("if(someCondition()){someAction();}", statement);
    }

    @Test
    public void generateJsIf_negated_withoutElseBlock_compilesCorrectly() throws Exception {
        JsIf statement = new JsIf("someCondition()", new JsCall("someAction").terminate()).not();

        generateAndAssert("if(!(someCondition())){someAction();}", statement);
    }

    @Test
    public void generateJsIf_withElseBlock_compilesCorrectly() throws Exception {
        JsIf statement =
            new JsIf(new JsCall("someCondition"), new JsCall("someAction").terminate(), new JsStatements(new JsCall(
                    "someAlternative"), new JsStatement("return")));

        generateAndAssert("if(someCondition()){someAction();}else{someAlternative();return;}", statement);
    }

    @Test
    public void generateJsIfNot_withElseBlock_compilesCorrectly() throws Exception {
        JsIf statement =
            new JsIfNot(new JsCall("someCondition"), new JsCall("someAction").terminate(), new JsStatements(new JsCall(
                    "someAlternative"), new JsStatement("return")));

        generateAndAssert("if(!(someCondition())){someAction();}else{someAlternative();return;}", statement);
    }

    @Test
    public void generateJsReturn_withoutExpression_compilesCorrectly() throws Exception {
        generateAndAssert("return;", new JsReturn());
    }

    @Test
    public void generateJsReturn_withExpression_compilesCorrectly() throws Exception {
        generateAndAssert("return foo.bar();", new JsReturn("foo.bar()"));
    }

    @Test
    public void generateJsReturn_withValue_compilesCorrectly() throws Exception {
        generateAndAssert("return true;", new JsReturn(true));
        generateAndAssert("return 42;", new JsReturn(42));
    }

    @Test
    public void generateJsReturn_withNullValue_compilesCorrectly() throws Exception {
        generateAndAssert("return null;", new JsReturn((Object) null));
    }

    @Test
    public void generateJsVariableDefinition_simpleDefinition_compilesCorrectly() throws Exception {
        JsVariableDefinition statement = new JsVariableDefinition("foo");

        generateAndAssert("var foo;", statement);
    }

    @Test
    public void generateJsVariableDefinition_withLiteralAssignment_compilesCorrectly() throws Exception {
        generateAndAssert("var foo = 'bar';", new JsVariableDefinition("foo", "bar"));
        generateAndAssert("var foo = 'bar';", new JsVariableDefinition("foo", new JsLiteral.JsString("bar")));
    }

    @Test
    public void generateJsVariableDefinition_withNullAssignment_compilesCorrectly() throws Exception {
        generateAndAssert("var foo = null;", new JsVariableDefinition("foo", null));
    }

    @Test
    public void generateJsVariableDefinition_withExpressionAssignment_compilesCorrectly() throws Exception {
        generateAndAssert("var foo = this;", new JsVariableDefinition("foo", JsExpression.THIS));
    }

    protected void generateAndAssert(String expected, IJavaScript expression) {
        errors.checkThat(new JsGenerator(expression).generate(), is(expected));
    }
}
