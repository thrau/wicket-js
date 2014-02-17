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

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.JsIdentifier;
import org.rauschig.wicketjs.markup.IComponentMarkupIdProvider;

/**
 * This extends AjaxEventBehavior to conveniently add AJAX event lifecycle handlers as IJavaScript.
 * 
 * @see org.apache.wicket.ajax.AjaxEventBehavior
 * @see org.apache.wicket.ajax.attributes.IAjaxCallListener
 */
public abstract class JsAjaxEventBehavior extends AjaxEventBehavior implements IComponentMarkupIdProvider {

    private static final long serialVersionUID = 3233171540872448222L;

    public static final JsIdentifier attrs = new JsIdentifier("attrs");
    public static final JsIdentifier jqXHR = new JsIdentifier("jqXHR");
    public static final JsIdentifier data = new JsIdentifier("data");
    public static final JsIdentifier settings = new JsIdentifier("settings");
    public static final JsIdentifier textStatus = new JsIdentifier("textStatus");

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

        attributes.getAjaxCallListeners().add(new AjaxCallListener(ajaxCallListener));
    }

    /**
     * The JavaScript that will be executed before the Ajax call, as early as possible. Even before the preconditions.
     * The script will be executed in a function that receives the following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * </ol>
     * 
     * @return the JavaScript that will be executed before the Ajax call.
     */
    protected IJavaScript onTrigger() {
        return null;
    }

    /**
     * The JavaScript that will be executed right before the execution of the the Ajax call, only if all preconditions
     * pass. The script will be executed in a function that receives the following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * <li>jqXHR - the jQuery XMLHttpRequest object</li>
     * <li>settings - the settings used for the jQuery.ajax() call</li>
     * </ol>
     * 
     * @return the JavaScript that will be executed before the Ajax call.
     */
    protected IJavaScript onBefore() {
        return null;
    }

    /**
     * The JavaScript that will be executed after successful return of the Ajax call. The script will be executed in a
     * function that receives the following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * <li>jqXHR - the jQuery XMLHttpRequest object</li>
     * <li>data - the Ajax response. Its type depends on {@link AjaxRequestAttributes#dataType}</li>
     * <li>textStatus - the status as text</li>
     * </ol>
     * 
     * @return the JavaScript that will be executed after a successful return of the Ajax call.
     */
    protected IJavaScript onSuccess() {
        return null;
    }

    /**
     * The JavaScript that will be executed after unsuccessful return of the Ajax call. The script will be executed in a
     * function that receives the following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * </ol>
     * 
     * @return the JavaScript that will be executed after a unsuccessful return of the Ajax call.
     */
    protected IJavaScript onFail() {
        return null;
    }

    /**
     * A JavaScript function that is invoked before the request is being executed. If it returns {@code false} then the
     * execution of the Ajax call will be cancelled. The script will be executed in a function that receives the
     * following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * </ol>
     * 
     * @return the JavaScript that should be used to decide whether the Ajax call should be made at all.
     */
    protected IJavaScript precondition() {
        return null;
    }

    /**
     * The JavaScript that will be executed after the Ajax call. The script will be executed in a function that receives
     * the following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * </ol>
     * <strong>Note</strong>: if the Ajax call is synchronous (see
     * {@link org.apache.wicket.ajax.attributes.AjaxRequestAttributes#setAsynchronous(boolean)}) then this JavaScript
     * will be executed after the {@code getCompleteHandler(org.apache.wicket.Component)}, otherwise it is executed
     * right after the execution of the Ajax request.
     * 
     * @return the JavaScript that will be executed after the start of the Ajax call but before its response is
     *         returned.
     */
    protected IJavaScript onAfter() {
        return null;
    }

    /**
     * The JavaScript that will be executed after both successful and unsuccessful return of the Ajax call. The script
     * will be executed in a function that receives the following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * <li>jqXHR - the jQuery XMLHttpRequest object</li>
     * <li>textStatus - the status as text</li>
     * </ol>
     * 
     * @return the JavaScript that will be executed after both successful and unsuccessful return of the Ajax call.
     */
    protected IJavaScript onComplete() {
        return null;
    }

    private IJsAjaxCallListener ajaxCallListener = new IJsAjaxCallListener() {

        private static final long serialVersionUID = -8155317270090002655L;

        @Override
        public IJavaScript getBeforeHandler(Component component) {
            return onTrigger();
        }

        @Override
        public IJavaScript getPrecondition(Component component) {
            return precondition();
        }

        @Override
        public IJavaScript getBeforeSendHandler(Component component) {
            return onBefore();
        }

        @Override
        public IJavaScript getAfterHandler(Component component) {
            return onAfter();
        }

        @Override
        public IJavaScript getSuccessHandler(Component component) {
            return onSuccess();
        }

        @Override
        public IJavaScript getFailureHandler(Component component) {
            return onFail();
        }

        @Override
        public IJavaScript getCompleteHandler(Component component) {
            return onComplete();
        }
    };
}
