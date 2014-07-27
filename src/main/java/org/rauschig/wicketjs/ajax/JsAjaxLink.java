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
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.attributes.IAjaxCallListener;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.rauschig.wicketjs.IJavaScript;

/**
 * Custom implementation of AjaxLink that is also IAjaxCallAware, allowing to directly return JavaScript callbacks that
 * are executed during the ajax call cycle.
 */
public abstract class JsAjaxLink<T> extends AjaxLink<T> implements IAjaxCallAware {

    private static final long serialVersionUID = 1L;

    public JsAjaxLink(String id) {
        super(id);
    }

    public JsAjaxLink(String id, IModel<T> model) {
        super(id, model);
    }

    @Override
    protected AjaxEventBehavior newAjaxEventBehavior(String event) {
        return new DelegatingAjaxEventBehavior(event);
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

    /**
     * Delegates all IJsAjaxCallListener method calls to this JsAjaxLink instance.
     */
    protected class DelegatingAjaxEventBehavior extends AjaxEventBehavior {
        private static final long serialVersionUID = 1L;

        public DelegatingAjaxEventBehavior(String event) {
            super(event);
        }

        @Override
        protected void onEvent(AjaxRequestTarget target) {
            onClick(target);
        }

        @Override
        protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
            super.updateAjaxAttributes(attributes);

            IAjaxCallListener callListener = new AjaxCallListener(new DelegatingAjaxCallListener(JsAjaxLink.this));
            attributes.getAjaxCallListeners().add(callListener);

            JsAjaxLink.this.updateAjaxAttributes(attributes);
        }

        @Override
        protected void onComponentTag(ComponentTag tag) {
            // add the onclick handler only if link is enabled
            if (isLinkEnabled()) {
                super.onComponentTag(tag);
            }
        }

    }
}
