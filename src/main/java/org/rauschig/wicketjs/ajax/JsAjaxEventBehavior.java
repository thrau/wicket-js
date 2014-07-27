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
package org.rauschig.wicketjs.ajax;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.markup.IComponentMarkupIdProvider;

/**
 * This extends AjaxEventBehavior to conveniently add AJAX event lifecycle handlers as IJavaScript.
 * 
 * @see org.apache.wicket.ajax.AjaxEventBehavior
 * @see org.apache.wicket.ajax.attributes.IAjaxCallListener
 */
public abstract class JsAjaxEventBehavior extends AjaxEventBehavior implements IComponentMarkupIdProvider,
        IAjaxCallAware {

    private static final long serialVersionUID = 3233171540872448222L;

    /**
     * Construct.
     * 
     * @param event the event that this behavior will be triggered by
     */
    public JsAjaxEventBehavior(String event) {
        super(event);
    }

    /**
     * Returns the markup id of the component this Behavior is bound to.
     * 
     * @return a markup id
     */
    public String id() {
        return getComponentMarkupId();
    }

    @Override
    public String getComponentMarkupId() {
        return getComponent().getMarkupId();
    }

    @Override
    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);

        attributes.getAjaxCallListeners().add(new AjaxCallListener(new DelegatingAjaxCallListener(this)));
    }

    @Override
    public IJavaScript onTrigger() {
        return null;
    }

    @Override
    public IJavaScript onBefore() {
        return null;
    }

    @Override
    public IJavaScript onSuccess() {
        return null;
    }

    @Override
    public IJavaScript onFail() {
        return null;
    }

    @Override
    public IJavaScript precondition() {
        return null;
    }

    @Override
    public IJavaScript onAfter() {
        return null;
    }

    @Override
    public IJavaScript onComplete() {
        return null;
    }
}
