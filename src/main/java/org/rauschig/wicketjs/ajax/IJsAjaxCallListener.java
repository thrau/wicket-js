package org.rauschig.wicketjs.ajax;

import org.apache.wicket.Component;
import org.apache.wicket.util.io.IClusterable;
import org.rauschig.wicketjs.IJavaScript;

/**
 * Interface used to listen at the most important points when Wicket performs an Ajax callback.
 * 
 * <p>
 * Each method can return JavaScript that will be used as a body of a function that is executed at the appropriate time.
 * If the method returns {@code null} or an empty string then it is ignored and no function will be executed for this
 * listener. Each JavaScript function receives arguments in the exact order as specified in the method's javadoc.
 * </p>
 * 
 * <p>
 * Ajax call listeners are potential contributors to the page header by implementing
 * {@link org.apache.wicket.markup.html.IComponentAwareHeaderContributor}. E.g. the JavaScript used by the listener may
 * depend on some JavaScript library, by implementing
 * {@link org.apache.wicket.markup.html.IComponentAwareHeaderContributor} interface they can assure it will be loaded.
 * </p>
 * 
 */
public interface IJsAjaxCallListener extends IClusterable {
    /**
     * The JavaScript that will be executed before the Ajax call, as early as possible. Even before the preconditions.
     * The script will be executed in a function that receives the following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * </ol>
     * 
     * @param component the Component with the Ajax behavior
     * @return the JavaScript that will be executed before the Ajax call.
     */
    IJavaScript getBeforeHandler(Component component);

    /**
     * A JavaScript function that is invoked before the request is being executed. If it returns {@code false} then the
     * execution of the Ajax call will be cancelled. The script will be executed in a function that receives the
     * following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * </ol>
     * 
     * @param component the Component with the Ajax behavior
     * @return the JavaScript that should be used to decide whether the Ajax call should be made at all.
     */
    IJavaScript getPrecondition(Component component);

    /**
     * The JavaScript that will be executed right before the execution of the the Ajax call, only if all preconditions
     * pass. The script will be executed in a function that receives the following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * <li>jqXHR - the jQuery XMLHttpRequest object</li>
     * <li>settings - the settings used for the jQuery.ajax() call</li>
     * </ol>
     * 
     * @param component the Component with the Ajax behavior
     * @return the JavaScript that will be executed before the Ajax call.
     */
    IJavaScript getBeforeSendHandler(Component component);

    /**
     * The JavaScript that will be executed after the Ajax call. The script will be executed in a function that receives
     * the following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * </ol>
     * <strong>Note</strong>: if the Ajax call is synchronous (see
     * {@link org.apache.wicket.ajax.attributes.AjaxRequestAttributes#setAsynchronous(boolean)}) then this JavaScript
     * will be executed after the {@linkplain #getCompleteHandler(org.apache.wicket.Component) complete handler},
     * otherwise it is executed right after the execution of the Ajax request.
     * 
     * @param component the Component with the Ajax behavior
     * @return the JavaScript that will be executed after the start of the Ajax call but before its response is
     *         returned.
     */
    IJavaScript getAfterHandler(Component component);

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
     * @param component the Component with the Ajax behavior
     * @return the JavaScript that will be executed after a successful return of the Ajax call.
     */
    IJavaScript getSuccessHandler(Component component);

    /**
     * The JavaScript that will be executed after unsuccessful return of the Ajax call. The script will be executed in a
     * function that receives the following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * </ol>
     * 
     * @param component the Component with the Ajax behavior
     * @return the JavaScript that will be executed after a unsuccessful return of the Ajax call.
     */
    IJavaScript getFailureHandler(Component component);

    /**
     * The JavaScript that will be executed after both successful and unsuccessful return of the Ajax call. The script
     * will be executed in a function that receives the following parameters:
     * <ol>
     * <li>attrs - the AjaxRequestAttributes as JSON</li>
     * <li>jqXHR - the jQuery XMLHttpRequest object</li>
     * <li>textStatus - the status as text</li>
     * </ol>
     * 
     * @param component the Component with the Ajax behavior
     * @return the JavaScript that will be executed after both successful and unsuccessful return of the Ajax call.
     */
    IJavaScript getCompleteHandler(Component component);
}
