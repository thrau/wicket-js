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
     * Adds javascript that will be evaluated on the client side after components are replaced

     * @param target the target to append the javascript to
     * @param js the javascript to render
     *
     * @see AjaxRequestTarget#appendJavaScript(CharSequence)
     */
    public static void append(AjaxRequestTarget target, IJavaScript js) {
        target.appendJavaScript(toString(js));
    }

    /**
     * Adds javascript that will be evaluated on the client side before components are replaced
     *
     * @param target the target to prepend the javascript to
     * @param js the javascript to render
     * @see AjaxRequestTarget#prependJavaScript(CharSequence)
     */
    public static void prepend(AjaxRequestTarget target, IJavaScript js) {
        target.appendJavaScript(toString(js));
    }

    /**
     * Creates a {@link org.apache.wicket.markup.head.JavaScriptContentHeaderItem} for the given content.
     *
     * @param js javascript content to be rendered.
     * @param id unique id for the javascript element. This can be null, however in that case the ajax header
     *        contribution can't detect duplicate script fragments.
     * @return A newly created {@link JavaScriptHeaderItem} for the given content.
     * @see JavaScriptHeaderItem#forScript(CharSequence, String)
     */
    public static JavaScriptHeaderItem asHeaderItem(IJavaScript js, String id) {
        return JavaScriptHeaderItem.forScript(toString(js), id);
    }

    /**
     * Creates a {@link org.apache.wicket.markup.head.JavaScriptContentHeaderItem} for the given content.
     *
     * @param js javascript content to be rendered.
     * @param id unique id for the javascript element. This can be null, however in that case the ajax header
     *        contribution can't detect duplicate script fragments.
     * @param condition the condition to use for Internet Explorer conditional comments. E.g. "IE 7".
     * @return A newly created {@link JavaScriptHeaderItem} for the given content.
     * @see JavaScriptHeaderItem#forScript(CharSequence, String, String)
     */
    public static JavaScriptHeaderItem asHeaderItem(IJavaScript js, String id, String condition) {
        return JavaScriptHeaderItem.forScript(toString(js), id, condition);
    }

    /**
     * Creates a {@link OnDomReadyHeaderItem} for the script.
     * 
     * @param js The script to execute on the DOM ready event.
     * @return A newly created {@link OnDomReadyHeaderItem}.
     * @see OnDomReadyHeaderItem#forScript(CharSequence)
     */
    public static OnDomReadyHeaderItem asOnDomReadyHeaderItem(IJavaScript js) {
        return OnDomReadyHeaderItem.forScript(toString(js));
    }

    private static String toString(IJavaScript js) {
        return new JsGenerator(js).generate();
    }
}
