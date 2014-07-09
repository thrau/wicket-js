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
import org.apache.wicket.ajax.attributes.IAjaxCallListener;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IComponentAwareHeaderContributor;
import org.apache.wicket.util.lang.Args;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.generator.JsGenerator;

/**
 * Wrapper for an IJsAjaxCallListener that compiles returned IJavaScript objects to CharSequence to make it usable as
 * IAjaxCallListener.
 */
public class AjaxCallListener implements IAjaxCallListener, IComponentAwareHeaderContributor {

    private static final long serialVersionUID = -2802085481583552325L;

    private final IJsAjaxCallListener wrapped;

    public AjaxCallListener(IJsAjaxCallListener wrapped) {
        this.wrapped = Args.notNull(wrapped, "wrapped IJsAjaxCallListener");
    }

    @Override
    public CharSequence getBeforeHandler(Component component) {
        return generate(wrapped.getBeforeHandler(component));
    }

    @Override
    public CharSequence getPrecondition(Component component) {
        return generate(wrapped.getPrecondition(component));
    }

    @Override
    public CharSequence getBeforeSendHandler(Component component) {
        return generate(wrapped.getBeforeSendHandler(component));
    }

    @Override
    public CharSequence getAfterHandler(Component component) {
        return generate(wrapped.getAfterHandler(component));
    }

    @Override
    public CharSequence getSuccessHandler(Component component) {
        return generate(wrapped.getSuccessHandler(component));
    }

    @Override
    public CharSequence getFailureHandler(Component component) {
        return generate(wrapped.getFailureHandler(component));
    }

    @Override
    public CharSequence getCompleteHandler(Component component) {
        return generate(wrapped.getCompleteHandler(component));
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {

    }

    protected CharSequence generate(IJavaScript script) {
        return (script == null) ? null : new JsGenerator(script).generate();
    }
}
