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

import java.util.List;

import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.JsCall;

/**
 * JsCallBehavior is a shorthand for
 * 
 * <pre>
 * add(new JsBehavior() {
 *     private static final long serialVersionUID = 1L;
 * 
 *     &#064;Override
 *     protected IJavaScript domReadyJs() {
 *         return new JsCall(&quot;fn&quot;, arg);
 *     }
 * });
 * </pre>
 * 
 * which is equivalent to
 * 
 * <pre>
 * new JsCallBehavior(&quot;fn&quot;, arg);
 * </pre>
 */
public class JsCallBehavior extends JsBehavior {

    private static final long serialVersionUID = 1L;

    private JsCall call;

    public JsCallBehavior(CharSequence functionName) {
        this(new JsCall(functionName));
    }

    public JsCallBehavior(CharSequence functionName, Object... args) {
        this(new JsCall(functionName, args));
    }

    public JsCallBehavior(IJsExpression function) {
        this(new JsCall(function));
    }

    public JsCallBehavior(IJsExpression function, List<IJsExpression> arguments) {
        this(new JsCall(function, arguments));
    }

    public JsCallBehavior(JsCall call) {
        setCall(call);
    }

    @Override
    protected IJavaScript domReadyJs() {
        return getCall();
    }

    public JsCall getCall() {
        return call;
    }

    public void setCall(JsCall call) {
        this.call = call;
    }
}
