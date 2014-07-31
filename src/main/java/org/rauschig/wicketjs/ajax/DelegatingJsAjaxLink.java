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

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;
import org.rauschig.wicketjs.IJavaScript;

/**
 * Convenience implementation delegates all IAjaxCallAware calls to a given IAjaxCallAware delegate.
 * 
 * @param <T> Model object type
 */
public abstract class DelegatingJsAjaxLink<T> extends JsAjaxLink<T> {

    private static final long serialVersionUID = 1L;

    private final IAjaxCallAware delegate;

    public DelegatingJsAjaxLink(String id, IAjaxCallAware delegate) {
        this(id, null, delegate);
    }

    public DelegatingJsAjaxLink(String id, IModel<T> model, IAjaxCallAware delegate) {
        super(id, model);
        this.delegate = Args.notNull(delegate, "delegate");
    }

    @Override
    public IJavaScript onTrigger() {
        return delegate.onTrigger();
    }

    @Override
    public IJavaScript onBefore() {
        return delegate.onBefore();
    }

    @Override
    public IJavaScript onSuccess() {
        return delegate.onSuccess();
    }

    @Override
    public IJavaScript onFail() {
        return delegate.onFail();
    }

    @Override
    public IJavaScript precondition() {
        return delegate.precondition();
    }

    @Override
    public IJavaScript onAfter() {
        return delegate.onAfter();
    }

    @Override
    public IJavaScript onComplete() {
        return delegate.onComplete();
    }
}
