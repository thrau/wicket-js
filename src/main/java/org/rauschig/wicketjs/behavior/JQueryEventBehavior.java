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

import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.jquery.JQuery;

/**
 * JQueryEventBehavior
 */
public abstract class JQueryEventBehavior extends JsBehavior {

    private static final long serialVersionUID = 3948469775808421408L;

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
