package org.rauschig.wicketjs.jquery;

import static org.junit.Assert.assertEquals;
import static org.rauschig.wicketjs.jquery.JQuery.$;

import org.apache.wicket.Component;
import org.junit.Test;
import org.mockito.Mockito;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.JsIdentifier;

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
    public void componentSelector_compilesCorrectly() throws Exception {
        Component c = Mockito.mock(Component.class);
        Mockito.when(c.getMarkupId()).thenReturn("id");

        compileAndAssert("$('#id')", $(c));
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
    public void bind_compilesCorrectly() throws Exception {
        String expected = "$('tag').bind('click',function(eventObject){console.log(this)})";
        compileAndAssert(expected, $("tag").bind("click", "console.log(this)"));
    }

    @Test
    public void bind_identifier_compilesCorrectly() throws Exception {
        String expected = "$('tag').bind('click',callback)";
        compileAndAssert(expected, $("tag").bind("click", new JsIdentifier("callback")));
    }

    @Test
    public void click_compilesCorrectly() throws Exception {
        String expected = "$('tag').bind('click',function(eventObject){console.log(this)})";
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
