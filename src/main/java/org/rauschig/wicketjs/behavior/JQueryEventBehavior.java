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

import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.jquery.JQuery;

/**
 * JsBehavior that uses JQuery's {@code bind} mechanism to bind an event handler callback to one or more DOM events.
 * When this behavior is added to a Wicket Component, the selector can be used to filter child elements.
 *
 * Example:
 * 
 * <pre>
 * import static org.rauschig.wicketjs.jquery.JQuery.$;
 * import static org.rauschig.wicketjs.JsExpression.THIS;
 * 
 * ...
 * 
 * DataTable table;
 * 
 * table.add(new JQueryEventBehavior(&quot;mouseenter mouseleave&quot;, &quot;tr&quot;) {
 *     &#064;Override
 *     protected IJavaScript callback() {
 *         return $(THIS).toggleClass(&quot;entered&quot;);
 *     }
 * });
 * </pre>
 * 
 * If the DataTable has the markup id <code>'table0'</code>, the JQuery will compile to
 * 
 * <pre>
 *     $('#table0').on('mouseenter mouseleave', 'tr', function(eventObject){
 *         $(this).toggleClass('entered');
 *     });
 * </pre>
 */
public abstract class JQueryEventBehavior extends JsBehavior {

    private static final long serialVersionUID = 3948469775808421408L;

    private String event;
    private String selector;

    /**
     * Creates a new JQueryEventBehavior for the given DOM event (e.g. {@code "click"} or
     * {@code "mouseenter mouseleave"}).
     * 
     * @param event One or more space-separated event types
     */
    public JQueryEventBehavior(String event) {
        this(event, null);
    }

    /**
     * Creates a new JQueryEventBehavior for the given DOM event (e.g. {@code "click"} or
     * {@code "mouseenter mouseleave"}), and binds it to the elements found by the given selector.
     * 
     * @param event One or more space-separated event types
     * @param selector a selector string to filter the descendants of the selected elements that trigger the event
     */
    public JQueryEventBehavior(String event, String selector) {
        this.event = event;
        this.selector = selector;
    }

    public String getEvent() {
        return event;
    }

    public String getSelector() {
        return selector;
    }

    @Override
    protected final IJavaScript domReadyJs() {
        JQuery $this = JQuery.$(getComponent());
        String selector = getSelector();

        if (selector != null) {
            return $this.on(event, selector, callback());
        } else {
            return $this.on(event, callback());
        }

    }

    /**
     * The body of the callback for the bound event.
     * 
     * @return JavaScript instructions
     */
    protected abstract IJavaScript callback();
}
