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

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.JavaScriptContentHeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.mock.MockWebResponse;
import org.apache.wicket.util.tester.DummyHomePage;
import org.junit.Test;
import org.rauschig.wicketjs.AbstractWicketTest;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.ajax.JsAjaxEventBehavior;

public class WicketJsUtilsTest extends AbstractWicketTest {

    @Test
    public void appendPrepend_rendersJsInCorrectOrder() throws Exception {
        WebPage page = new DummyHomePage();
        page.add(new JsAjaxEventBehavior("test") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                WicketJsUtils.append(target, new JsCall("alert", "pwnd-2"));
                WicketJsUtils.prepend(target, new JsCall("alert", "pwnd-1"));
                WicketJsUtils.append(target, new JsCall("alert", "pwnd-3"));
            }
        });

        getTester().executeAjaxEvent(page, "test");

        String response = getTester().getLastResponseAsString();

        assertThat(response, containsString("alert('pwnd-1')"));
        assertThat(response, containsString("alert('pwnd-2')"));
        assertThat(response, containsString("alert('pwnd-3')"));

        int a = response.indexOf("alert('pwnd-1')");
        int b = response.indexOf("alert('pwnd-2')");
        int c = response.indexOf("alert('pwnd-3')");

        assertTrue("Javascript was not appended in the correct order", b > a);
        assertTrue("Javascript was not appended in the correct order", c > b);
    }

    @Test
    public void asOnDomReadyHeaderItem_rendersItemCorrectly() throws Exception {
        OnDomReadyHeaderItem item = WicketJsUtils.asOnDomReadyHeaderItem(new JsCall("alert", "pwnd"));
        assertEquals("alert('pwnd')", item.getJavaScript());
    }

    @Test
    public void asHeaderItem_withoutCondition_rendersItemCorrectly() throws Exception {
        JavaScriptHeaderItem item = WicketJsUtils.asHeaderItem(new JsCall("alert", "pwnd"), "js-is-useful");

        assertTrue(item instanceof JavaScriptContentHeaderItem);
        assertNull(item.getCondition());

        MockWebResponse response = new MockWebResponse();
        item.render(response);

        String document = response.getTextResponse().toString();
        assertThat(document, startsWith("<script"));
        assertThat(document, containsString("type=\"text/javascript\""));
        assertThat(document, containsString("id=\"js-is-useful\""));
        assertThat(document, containsString("alert('pwnd')"));
    }

    @Test
    public void asHeaderItem_withCondition_rendersItemCorrectly() throws Exception {
        JavaScriptHeaderItem item = WicketJsUtils.asHeaderItem(new JsCall("alert", "pwnd"), "js-is-useful", "IE");

        assertTrue(item instanceof JavaScriptContentHeaderItem);
        assertNotNull(item.getCondition());

        MockWebResponse response = new MockWebResponse();
        item.render(response);

        String document = response.getTextResponse().toString();
        assertThat(document, startsWith("<!--[if IE]>"));
        assertThat(document, containsString("<script"));
        assertThat(document, containsString("type=\"text/javascript\""));
        assertThat(document, containsString("id=\"js-is-useful\""));
        assertThat(document, containsString("alert('pwnd')"));
    }
}
