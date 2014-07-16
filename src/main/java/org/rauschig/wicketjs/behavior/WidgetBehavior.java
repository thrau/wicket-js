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
package org.rauschig.wicketjs.behavior;

import static org.rauschig.wicketjs.jquery.JQuery.$;

import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.util.options.IOptions;
import org.rauschig.wicketjs.util.options.Options;

/**
 * A JsBehavior that calls a function with an object argument on a component. Usually jQuery widgets are initialized
 * like this
 * 
 * <pre>
 *     $('#component').myWidget({
 *         "property1": "value1",
 *         "property2": 2,
 *     });
 * </pre>
 * 
 * This is what {@code WidgetBehavior} allows you to define programmatically as a {@code Behavior} which will then be
 * called as DOM-ready script.
 */
public class WidgetBehavior extends JsBehavior {

    private static final long serialVersionUID = 1L;

    private String widget;
    private String selector;

    private IOptions options;

    /**
     * Creates a new WidgetBehavior for the given widget name.
     * 
     * @param widget the name of the widget.
     */
    public WidgetBehavior(String widget) {
        this(widget, new Options());
    }

    /**
     * Creates a new WidgetBehavior for the given widget name on elements that satisfy the given sub-selector.
     * 
     * @param widget the name of the widget.
     * @param selector the additional sub-selector that will be used to apply the widget
     */
    public WidgetBehavior(String widget, String selector) {
        this(widget, selector, new Options());
    }

    /**
     * Creates a new WidgetBehavior for the given widget name.
     * 
     * @param widget the name of the widget.
     * @param options the options object that is passed to the widget method when called
     */
    public WidgetBehavior(String widget, IOptions options) {
        this(widget, null, options);
    }

    /**
     * Creates a new WidgetBehavior for the given widget name on elements that satisfy the given sub-selector.
     * 
     * @param widget the name of the widget.
     * @param selector the additional sub-selector that will be used to apply the widget
     * @param options the options object that is passed to the widget method when called
     */
    public WidgetBehavior(String widget, String selector, IOptions options) {
        this.widget = widget;
        this.selector = selector;
        this.options = options;

        onInitialize(options);
    }

    /**
     * Returns the widget method name.
     * 
     * @return the widget method
     */
    public String getWidget() {
        return widget;
    }

    /**
     * Returns the optional sub-selector.
     * 
     * @return a jQuery selector
     */
    public String getSelector() {
        return selector;
    }

    /**
     * Returns the Options for this widget.
     * 
     * @return an Options instance
     */
    public IOptions getOptions() {
        return options;
    }

    /**
     * Sets the Options for this widget.
     * 
     * @param options an Options instance
     * @return this for chaining
     */
    public WidgetBehavior setOptions(IOptions options) {
        this.options = options;
        return this;
    }

    @Override
    protected IJavaScript domReadyJs() {
        Object[] params = getCallArguments();

        String sel = getSelector();
        if (sel == null || sel.isEmpty()) {
            return $(this).call(getWidget(), params);
        } else {
            return $(sel, this).call(getWidget(), params);
        }
    }

    /**
     * The arguments passed when calling the widget method.
     * 
     * @return arguments that the widget method is called with
     */
    protected Object[] getCallArguments() {
        IOptions options = getOptions();

        if (options != null) {
            onBeforeRender(options);
            return new Object[] { options.asObject() };
        } else {
            return new Object[0];
        }
    }

    /**
     * Called after construction.
     * 
     * @param options the options being constructed with
     */
    protected void onInitialize(IOptions options) {
        // hook
    }

    /**
     * Called before the options are passed to the widget method call. In the Wicket lifecycle this is onRenderHead.
     * 
     * @param options the options being passed
     */
    protected void onBeforeRender(IOptions options) {
        // hook
    }

}
