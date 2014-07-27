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

import org.apache.wicket.util.io.IClusterable;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.JsIdentifier;

/**
 * Interface for components that provide callbacks that can be used by an .
 */
public interface IAjaxCallAware extends IClusterable {

    public static final JsIdentifier attrs = new JsIdentifier("attrs");
    public static final JsIdentifier jqXHR = new JsIdentifier("jqXHR");
    public static final JsIdentifier data = new JsIdentifier("data");
    public static final JsIdentifier settings = new JsIdentifier("settings");
    public static final JsIdentifier textStatus = new JsIdentifier("textStatus");

    /**
     * The JavaScript that will be executed before the Ajax call, as early as possible. Even before the preconditions.
     * The script will be executed in a function that receives the following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * </ol>
     * 
     * @return the JavaScript that will be executed before the Ajax call.
     */
    IJavaScript onTrigger();

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
    IJavaScript onBefore();

    /**
     * The JavaScript that will be executed after successful return of the Ajax call. The script will be executed in a
     * function that receives the following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * <li>jqXHR - the jQuery XMLHttpRequest object</li>
     * <li>data - the Ajax response. Its type depends on
     * {@link org.apache.wicket.ajax.attributes.AjaxRequestAttributes#dataType}</li>
     * <li>textStatus - the status as text</li>
     * </ol>
     * 
     * @return the JavaScript that will be executed after a successful return of the Ajax call.
     */
    IJavaScript onSuccess();

    /**
     * The JavaScript that will be executed after unsuccessful return of the Ajax call. The script will be executed in a
     * function that receives the following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * </ol>
     * 
     * @return the JavaScript that will be executed after a unsuccessful return of the Ajax call.
     */
    IJavaScript onFail();

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
    IJavaScript precondition();

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
    IJavaScript onAfter();

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
    IJavaScript onComplete();

}
