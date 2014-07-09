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

import org.apache.wicket.ajax.AbstractAjaxResponse;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.generator.JsGenerator;

/**
 * A set of utils that integrates IJavaScript tokens where Wicket expects CharSequences.
 */
public final class WicketJsUtils {

    private WicketJsUtils() {

    }

    /**
     * @see AjaxRequestTarget#appendJavaScript(CharSequence)
     */
    public static void append(AjaxRequestTarget target, IJavaScript js) {
        target.appendJavaScript(toString(js));
    }

    /**
     * @see AjaxRequestTarget#prependJavaScript(CharSequence)
     */
    public static void prepend(AjaxRequestTarget target, IJavaScript js) {
        target.appendJavaScript(toString(js));
    }

    /**
     * @see AbstractAjaxResponse#appendJavaScript(CharSequence)
     */
    public static void append(AbstractAjaxResponse target, IJavaScript js) {
        target.appendJavaScript(toString(js));
    }

    /**
     * @see AbstractAjaxResponse#appendJavaScript(CharSequence)
     */
    public static void prepend(AbstractAjaxResponse target, IJavaScript js) {
        target.appendJavaScript(toString(js));
    }

    /**
     * @see  JavaScriptHeaderItem#forScript(CharSequence, String) 
     */
    public static JavaScriptHeaderItem asHeaderItem(IJavaScript js, String id) {
        return JavaScriptHeaderItem.forScript(toString(js), id);
    }

    /**
     * @see  JavaScriptHeaderItem#forScript(CharSequence, String, String)
     */
    public static JavaScriptHeaderItem asHeaderItem(IJavaScript js, String id, String condition) {
        return JavaScriptHeaderItem.forScript(toString(js), id, condition);
    }

    /**
     * @see OnDomReadyHeaderItem#forScript(CharSequence)
     */
    public static OnDomReadyHeaderItem asOnDomReadyHeaderItem(IJavaScript js) {
        return OnDomReadyHeaderItem.forScript(toString(js));
    }

    private static String toString(IJavaScript js) {
        return new JsGenerator(js).generate();
    }
}
