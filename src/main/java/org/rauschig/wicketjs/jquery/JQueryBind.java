package org.rauschig.wicketjs.jquery;

import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.JsFunction;
import org.rauschig.wicketjs.JsIdentifier;

/**
 * JQueryBind
 */
public class JQueryBind extends JsCall {

    public static final JsIdentifier EVENT_OBJECT = new JsIdentifier("eventObject");

    public JQueryBind(String event, String callbackBody) {
        this(event, new JsFunction(callbackBody));
    }

    public JQueryBind(String event, IJsExpression callbackBody) {
        this(event, new JsFunction(callbackBody));
    }

    public JQueryBind(String event, JsIdentifier callback) {
        super("bind", event, callback);
    }

    public JQueryBind(String event, JsFunction callback) {
        super("bind", event, callback);

        if (!callback.getParameters().contains(EVENT_OBJECT)) {
            callback.addParameter(EVENT_OBJECT);
        }
    }

}
