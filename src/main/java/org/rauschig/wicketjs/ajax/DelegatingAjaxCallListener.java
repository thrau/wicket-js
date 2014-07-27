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
import org.rauschig.wicketjs.IJavaScript;

/**
 * An IJsAjaxCallListener that delegates method calls to an IAjaxCallAware.
 */
public class DelegatingAjaxCallListener implements IJsAjaxCallListener {

    private static final long serialVersionUID = 1L;

    private IAjaxCallAware delegate;

    public DelegatingAjaxCallListener(IAjaxCallAware delegate) {
        this.delegate = delegate;
    }

    @Override
    public IJavaScript getBeforeHandler(Component component) {
        return delegate.onTrigger();
    }

    @Override
    public IJavaScript getPrecondition(Component component) {
        return delegate.precondition();
    }

    @Override
    public IJavaScript getBeforeSendHandler(Component component) {
        return delegate.onBefore();
    }

    @Override
    public IJavaScript getAfterHandler(Component component) {
        return delegate.onAfter();
    }

    @Override
    public IJavaScript getSuccessHandler(Component component) {
        return delegate.onSuccess();
    }

    @Override
    public IJavaScript getFailureHandler(Component component) {
        return delegate.onFail();
    }

    @Override
    public IJavaScript getCompleteHandler(Component component) {
        return delegate.onComplete();
    }

}
