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

import org.apache.wicket.Component;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.JsCallChain;
import org.rauschig.wicketjs.JsFunction;
import org.rauschig.wicketjs.JsIdentifier;
import org.rauschig.wicketjs.JsLiteral;
import org.rauschig.wicketjs.markup.IComponentMarkupIdProvider;
import org.rauschig.wicketjs.util.Strings;

/**
 * A (not complete) JQuery object. Allows you to conveniently build chained JQuery expressions in a JavaScript type
 * syntax.
 * <p/>
 * 
 * Example usage: (Import the {@link #$(String)} function using
 * {@code import static org.rauschig.wicketjs.jquery.JQuery.$})
 * 
 * <pre>
 * $(&quot;table&quot;).find(&quot;tr&quot;).click(&quot;console.log(this)&quot;);
 * </pre>
 * 
 * Will compile to
 * 
 * <pre>
 *     $('table').find('tr').click(function(eventObject) {
 *         console.log(this)
 *     });
 * </pre>
 * 
 * @see <a href="http://api.jquery.com/">http://api.jquery.com/</a>
 */
public class JQuery extends JsCallChain {

    private static final long serialVersionUID = -3713464209858405030L;

    public static final JsIdentifier eventObject = new JsIdentifier("eventObject");

    public JQuery() {
        super(new JsIdentifier("$"));
    }

    /**
     * Create a new JQuery expression using the given Component's markup id as selector.
     * <p/>
     * E.g. if the Component returns the markup id "table0" the initial JQuery selector will be $('#table0').
     * 
     * @param component the component to use in the selector
     */
    public JQuery(Component component) {
        this(getSelector(component));
    }

    /**
     * Create a new JQuery expression using the markup id provided by the given MarkupIdProvider.
     * <p/>
     * This allows convenient calls within anonymous {@code JsBehavior} definitions. E.g.
     * 
     * <pre>
     * Component c;
     * 
     * c.add(new JQueryEventBehavior(&quot;click&quot;) {
     *     &#064;Override
     *     protected IJavaScript callback() {
     *         return $(this).toggleClass(&quot;clicked&quot;);
     *     }
     * });
     * </pre>
     * 
     * In this case, the markup id of component {@code c} will be used.
     * 
     * @param markupIdProvider
     */
    public JQuery(IComponentMarkupIdProvider markupIdProvider) {
        this(getSelector(markupIdProvider));
    }

    /**
     * Create a new JQuery expression using the given selector.
     * <p/>
     * Note that the selector will be treated as a JavaScript string literal when compiled, s.t. <code>$("this")</code>
     * will compile to <code>$('this')</code>.
     * <p/>
     * If you need a reference to {@code this}, then use {@link #JQuery(org.rauschig.wicketjs.IJsExpression)} and
     * {@link org.rauschig.wicketjs.JsExpression#THIS}.
     * 
     * @param selector the JQuery selector
     */
    public JQuery(String selector) {
        this(new JsLiteral.JsString(selector));
    }

    /**
     * Create a new JQuery expression using the given expression as selector.
     * 
     * @param selector the JQuery selector
     */
    public JQuery(IJsExpression selector) {
        super(new JsCall("$", selector));
    }

    /**
     * Create a new JQuery expression using the given expression as selector and context.
     * <p/>
     * Note that the selector will be treated as a JavaScript string literal when compiled, s.t. <code>$("this")</code>
     * will compile to <code>$('this')</code>.
     * <p/>
     * If you need a reference to {@code this}, then use {@link #JQuery(String, org.rauschig.wicketjs.IJsExpression)}
     * and {@link org.rauschig.wicketjs.JsExpression#THIS}.
     * 
     * @param selector the JQuery selector
     * @param context the selector context
     */
    public JQuery(String selector, Component context) {
        this(selector, getSelector(context));
    }

    /**
     * Create a new JQuery expression using the given expression as selector and context.
     * <p/>
     * Note that the selector will be treated as a JavaScript string literal when compiled, s.t. <code>$("this")</code>
     * will compile to <code>$('this')</code>.
     * <p/>
     * If you need a reference to {@code this}, then use {@link #JQuery(String, org.rauschig.wicketjs.IJsExpression)}
     * and {@link org.rauschig.wicketjs.JsExpression#THIS}.
     *
     * @param selector the JQuery selector
     * @param context the selector context
     */
    public JQuery(String selector, IComponentMarkupIdProvider context) {
        this(selector, getSelector(context));
    }

    /**
     * Create a new JQuery expression using the given selector.
     * <p/>
     * Note that both the selector and the context will be treated as a JavaScript string literal when compiled, s.t.
     * <code>$("tr", "this")</code> will compile to <code>$('tr','this')</code>.
     * <p/>
     * If you need a reference to {@code this}, then use {@link #JQuery(String, org.rauschig.wicketjs.IJsExpression)}
     * and {@link org.rauschig.wicketjs.JsExpression#THIS}.
     * 
     * @param selector the JQuery selector
     * @param context the selector context
     */
    public JQuery(String selector, String context) {
        this(selector, JsLiteral.of(context));
    }

    /**
     * Create a new JQuery expression using the given expression as selector and context.
     * 
     * @param selector the JQuery selector
     * @param context the selector context
     */
    public JQuery(String selector, IJsExpression context) {
        this(JsLiteral.of(selector), context);
    }

    /**
     * Create a new JQuery expression using the given expression as selector and context.
     * 
     * @param selector the JQuery selector
     * @param context the selector context
     */
    public JQuery(IJsExpression selector, IJsExpression context) {
        super(new JsCall("$", selector, context));
    }

    /* factory methods */

    public static JQuery jQuery() {
        return new JQuery();
    }

    public static JQuery jQuery(Component component) {
        return new JQuery(component);
    }

    private static JQuery jQuery(IComponentMarkupIdProvider markupIdProvider) {
        return new JQuery(markupIdProvider);
    }

    public static JQuery jQuery(String selector) {
        return new JQuery(selector);
    }

    public static JQuery jQuery(IJsExpression selector) {
        return new JQuery(selector);
    }

    public static JQuery jQuery(String selector, Component context) {
        return new JQuery(selector, context);
    }

    public static JQuery jQuery(String selector, IComponentMarkupIdProvider context) {
        return new JQuery(selector, context);
    }

    public static JQuery jQuery(String selector, String context) {
        return new JQuery(selector, context);
    }

    public static JQuery jQuery(String selector, IJsExpression context) {
        return new JQuery(selector, context);
    }

    public static JQuery jQuery(IJsExpression selector, IJsExpression context) {
        return new JQuery(selector, context);
    }

    public static JQuery $() {
        return jQuery();
    }

    public static JQuery $(Component component) {
        return jQuery(component);
    }

    public static JQuery $(IComponentMarkupIdProvider markupIdProvider) {
        return jQuery(markupIdProvider);
    }

    public static JQuery $(String selector) {
        return jQuery(selector);
    }

    public static JQuery $(IJsExpression selector) {
        return jQuery(selector);
    }

    public static JQuery $(String selector, Component context) {
        return jQuery(selector, context);
    }

    public static JQuery $(String selector, IComponentMarkupIdProvider context) {
        return jQuery(selector, context);
    }

    public static JQuery $(String selector, String context) {
        return jQuery(selector, context);
    }

    public static JQuery $(String selector, IJsExpression context) {
        return jQuery(selector, context);
    }

    public static JQuery $(IJsExpression selector, IJsExpression context) {
        return jQuery(selector, context);
    }

    /* chaining */

    @Override
    public JQuery _(IJsExpression expression) {
        return (JQuery) super._(expression);
    }

    @Override
    public JQuery _(String identifier) {
        return (JQuery) super._(identifier);
    }

    @Override
    public JQuery _(String functionName, Object... arguments) {
        return (JQuery) super._(functionName, arguments);
    }

    @Override
    public JQuery call(String functionName) {
        return (JQuery) super.call(functionName);
    }

    @Override
    public JQuery call(String functionName, Object... arguments) {
        return (JQuery) super.call(functionName, arguments);
    }

    @Override
    public JQuery chain(IJsExpression expression) {
        return (JQuery) super.chain(expression);
    }

    @Override
    public JQuery chain(IJsExpression... expressions) {
        return (JQuery) super.chain(expressions);
    }

    @Override
    public JQuery chain(String identifier) {
        return (JQuery) super.chain(identifier);
    }

    /* events */

    public JQuery on(String events, CharSequence callbackBody) {
        return on(events, new JsFunction(callbackBody));
    }

    public JQuery on(String events, IJavaScript handlerBody) {
        return on(events, new JsFunction(handlerBody));
    }

    public JQuery on(String events, JsFunction handler) {
        if (handler.getParameters().isEmpty()) {
            handler.getParameters().add(eventObject);
        }

        return call("on", events, handler);
    }

    public JQuery on(String events, JsIdentifier handler) {
        return call("on", events, handler);
    }

    public JQuery on(String events, String selector, CharSequence handlerBody) {
        return on(events, selector, new JsFunction(handlerBody));
    }

    public JQuery on(String events, String selector, IJavaScript handlerBody) {
        return on(events, selector, new JsFunction(handlerBody));
    }

    public JQuery on(String events, String selector, JsFunction handler) {
        if (handler.getParameters().isEmpty()) {
            handler.getParameters().add(eventObject);
        }

        return call("on", events, selector, handler);
    }

    public JQuery on(String events, String selector, JsIdentifier handler) {
        return call("on", events, selector, handler);
    }

    public JQuery bind(String event, JsIdentifier callback) {
        return call("bind", event, callback);
    }

    public JQuery bind(String event, String callbackBody) {
        return bind(event, new JsFunction(callbackBody));
    }

    public JQuery bind(String event, IJavaScript callbackBody) {
        return bind(event, new JsFunction(callbackBody));
    }

    public JQuery bind(String event, JsFunction callback) {
        if (!callback.getParameters().contains(eventObject)) {
            callback.addParameter(eventObject);
        }

        return call("bind", event, callback);
    }

    public JQuery click(String callbackBody) {
        return bind("click", callbackBody);
    }

    public JQuery click(IJavaScript callbackBody) {
        return bind("click", callbackBody);
    }

    public JQuery click(JsIdentifier callback) {
        return bind("click", callback);
    }

    public JQuery click(JsFunction callback) {
        return bind("click", callback);
    }

    public JQuery trigger(String event) {
        return call("trigger", event);
    }

    /* traversing */

    public JQuery each(JsFunction callback) {
        return call("each", callback);
    }

    public JQuery each(String callbackBody) {
        return each(new JsFunction(callbackBody, "index", "element"));
    }

    public JQuery each(IJavaScript callbackBody) {
        return each(new JsFunction(callbackBody, "index", "element"));
    }

    public JQuery find(String selector) {
        return call("find", selector);
    }

    public JQuery first() {
        return call("first");
    }

    public JQuery has(String selector) {
        return call("has", selector);
    }

    public IJsExpression is(String selector) {
        return call("is", selector);
    }

    public JQuery last() {
        return call("last");
    }

    public JQuery not(String selector) {
        return call("not", selector);
    }

    public JQuery parent() {
        return call("parent");
    }

    public JQuery parent(String selector) {
        return call("parent", selector);
    }

    /* attributes */

    public JQuery addClass(String cssClass) {
        return call("addClass", cssClass);
    }

    public JQuery addClass(String... cssClass) {
        return call("addClass", Strings.join(cssClass, " "));
    }

    public IJsExpression attr(String attributeName) {
        return call("attr", attributeName);
    }

    public IJsExpression hasClass(String className) {
        return call("hasClass", className);
    }

    public IJsExpression html() {
        return call("html");
    }

    public JQuery html(CharSequence html) {
        return call("html", html.toString());
    }

    public JQuery html(JsFunction function) {
        if (function.getParameters().isEmpty()) {
            function.param("index").param("oldHtml");
        }

        return call("html", function);
    }

    public JQuery removeClass(String cssClass) {
        return call("removeClass", cssClass);
    }

    public JQuery removeClass(String... cssClass) {
        return call("removeClass", Strings.join(cssClass, " "));
    }

    public JQuery toggleClass(String cssClass) {
        return call("toggleClass", cssClass);
    }

    public JQuery toggleClass(String... cssClass) {
        return call("toggleClass", Strings.join(cssClass, " "));
    }

    public IJsExpression val() {
        return call("val");
    }

    public JQuery val(Number value) {
        return call("val", value);
    }

    public JQuery val(String value) {
        return call("val", value);
    }

    public JQuery val(IJsExpression value) {
        return call("val", value);
    }

    public JQuery val(JsFunction function) {
        if (function.getParameters().isEmpty()) {
            function.param("index").param("value");
        }

        return call("val", function);
    }

    /* effects */

    public JQuery fadeIn() {
        return call("fadeIn");
    }

    public JQuery fadeOut() {
        return call("fadeOut");
    }

    public JQuery fadeToggle() {
        return call("fadeToggle");
    }

    public JQuery hide() {
        return call("hide");
    }

    public JQuery show() {
        return call("show");
    }

    public JQuery toggle() {
        return call("toggle");
    }

    public JQuery slideUp() {
        return call("slideUp");
    }

    public JQuery slideDown() {
        return call("slideDown");
    }

    public JQuery slideToggle() {
        return call("slideToggle");
    }

    public static String getSelector(Component component) {
        return "#" + component.setOutputMarkupId(true).getMarkupId();
    }

    public static String getSelector(IComponentMarkupIdProvider markupIdProvider) {
        return "#" + markupIdProvider.getComponentMarkupId();
    }

}
