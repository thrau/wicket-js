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
package org.rauschig.wicketjs.jquery;

import static org.junit.Assert.assertEquals;
import static org.rauschig.wicketjs.jquery.JQuery.$;

import org.apache.wicket.Component;
import org.junit.Test;
import org.mockito.Mockito;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.JsExpression;
import org.rauschig.wicketjs.JsIdentifier;
import org.rauschig.wicketjs.markup.IComponentMarkupIdProvider;

/**
 * JQueryTest
 */
public class JQueryTest {

    @Test
    public void noSelector_compilesCorrectly() throws Exception {
        compileAndAssert("$", $());
    }

    @Test
    public void singleSelector_compilesCorrectly() throws Exception {
        compileAndAssert("$('#id')", $("#id"));
    }

    @Test
    public void selectorWithContext_compilesCorrectly() throws Exception {
        compileAndAssert("$('child','#id')", $("child", "#id"));
        compileAndAssert("$('child',this)", $("child", new JsExpression("this")));
    }

    @Test
    public void componentSelector_compilesCorrectly() throws Exception {
        Component c = Mockito.mock(Component.class);
        Mockito.when(c.getMarkupId()).thenReturn("id");

        compileAndAssert("$('#id')", $(c));
    }

    @Test
    public void selectorWithComponentContext_compilesCorrectly() throws Exception {
        Component c = Mockito.mock(Component.class);
        Mockito.when(c.getMarkupId()).thenReturn("id");

        compileAndAssert("$('div','#id')", $("div", c));
    }

    @Test
    public void markupIdProviderSelector_compilesCorrectly() throws Exception {
        IComponentMarkupIdProvider c = Mockito.mock(IComponentMarkupIdProvider.class);
        Mockito.when(c.getComponentMarkupId()).thenReturn("id");

        compileAndAssert("$('#id')", $(c));
    }

    @Test
    public void selectorWithMarkupIdProviderContext_compilesCorrectly() throws Exception {
        IComponentMarkupIdProvider c = Mockito.mock(IComponentMarkupIdProvider.class);
        Mockito.when(c.getComponentMarkupId()).thenReturn("id");

        compileAndAssert("$('div','#id')", $("div", c));
    }

    @Test
    public void identifierSelector_compilesCorrectly() throws Exception {
        compileAndAssert("$(this)", $(new JsIdentifier("this")));
    }

    @Test
    public void nestedSelectors_compilesCorrectly() throws Exception {
        compileAndAssert("$($(this))", $($(new JsIdentifier("this"))));
    }

    @Test
    public void multipleChains_compilesCorrectly() throws Exception {
        compileAndAssert("$('i').foo.bar.ed()", $("i")._("foo")._("bar")._(new JsCall("ed")));
    }

    @Test
    public void find_compilesCorrectly() throws Exception {
        compileAndAssert("$('table').find('tr')", $("table").find("tr"));
    }

    @Test
    public void parent_compilesCorrectly() throws Exception {
        compileAndAssert("$('table').parent()", $("table").parent());
        compileAndAssert("$('table').parent('div')", $("table").parent("div"));
    }

    @Test
    public void on_compilesCorrectly() throws Exception {
        String expected = "$('tag').on('click',function(eventObject){console.log(this);})";
        compileAndAssert(expected, $("tag").on("click", "console.log(this)"));
    }

    @Test
    public void on_identifier_compilesCorrectly() throws Exception {
        String expected = "$('tag').on('click',callback)";
        compileAndAssert(expected, $("tag").on("click", new JsIdentifier("callback")));
    }

    @Test
    public void on_withSelector_compilesCorrectly() throws Exception {
        String expected = "$('tag').on('click','child',function(eventObject){console.log(this);})";
        compileAndAssert(expected, $("tag").on("click", "child", "console.log(this)"));
    }

    @Test
    public void bind_compilesCorrectly() throws Exception {
        String expected = "$('tag').bind('click',function(eventObject){console.log(this);})";
        compileAndAssert(expected, $("tag").bind("click", "console.log(this)"));
    }

    @Test
    public void bind_identifier_compilesCorrectly() throws Exception {
        String expected = "$('tag').bind('click',callback)";
        compileAndAssert(expected, $("tag").bind("click", new JsIdentifier("callback")));
    }

    @Test
    public void click_compilesCorrectly() throws Exception {
        String expected = "$('tag').bind('click',function(eventObject){console.log(this);})";
        compileAndAssert(expected, $("tag").click("console.log(this)"));
    }

    @Test
    public void toggleClass_compilesCorrectly() throws Exception {
        String expected = "$('tag').toggleClass('foo')";
        compileAndAssert(expected, $("tag").toggleClass("foo"));
    }

    @Test
    public void toggleClass_multipleClasses_compilesCorrectly() throws Exception {
        String expected = "$('tag').toggleClass('foo bar ed')";
        compileAndAssert(expected, $("tag").toggleClass("foo", "bar", "ed"));
    }

    private void compileAndAssert(String expected, JQuery jQuery) {
        assertEquals(expected, jQuery.js());
    }

}
