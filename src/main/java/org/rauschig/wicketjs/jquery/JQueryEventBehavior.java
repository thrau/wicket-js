package org.rauschig.wicketjs.jquery;

import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.behavior.JsBehavior;

/**
 * JQueryEventBehavior
 */
public abstract class JQueryEventBehavior extends JsBehavior {

    private String event;
    private String selector;

    public JQueryEventBehavior(String event) {
        this(event, null);
    }

    public JQueryEventBehavior(String event, String selector) {
        this.event = event;
        this.selector = selector;
    }

    @Override
    protected IJsExpression domReadyJs() {
        return $().bind(event, callback());
    }

    private JQuery $() {
        JQuery $ = JQuery.$(getComponent());

        if (selector != null) {
            $.find(selector);
        }

        return $;
    }

    protected abstract IJsExpression callback();
}
