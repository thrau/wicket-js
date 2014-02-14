package org.rauschig.wicketjs.ajax;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.IAjaxCallListener;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IComponentAwareHeaderContributor;
import org.apache.wicket.util.lang.Args;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.compiler.JsCompiler;

/**
 * Wrapper for an IJsAjaxCallListener that compiles returned IJavaScript objects to CharSequence to make it usable as
 * IAjaxCallListener.
 */
public class AjaxCallListener implements IAjaxCallListener, IComponentAwareHeaderContributor {

    private final IJsAjaxCallListener wrapped;

    public AjaxCallListener(IJsAjaxCallListener wrapped) {
        this.wrapped = Args.notNull(wrapped, "wrapped IJsAjaxCallListener");
    }

    @Override
    public CharSequence getBeforeHandler(Component component) {
        return compile(wrapped.getBeforeHandler(component));
    }

    @Override
    public CharSequence getPrecondition(Component component) {
        return compile(wrapped.getPrecondition(component));
    }

    @Override
    public CharSequence getBeforeSendHandler(Component component) {
        return compile(wrapped.getBeforeSendHandler(component));
    }

    @Override
    public CharSequence getAfterHandler(Component component) {
        return compile(wrapped.getAfterHandler(component));
    }

    @Override
    public CharSequence getSuccessHandler(Component component) {
        return compile(wrapped.getSuccessHandler(component));
    }

    @Override
    public CharSequence getFailureHandler(Component component) {
        return compile(wrapped.getFailureHandler(component));
    }

    @Override
    public CharSequence getCompleteHandler(Component component) {
        return compile(wrapped.getCompleteHandler(component));
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {

    }

    protected CharSequence compile(IJavaScript script) {
        return (script == null) ? null : new JsCompiler(script).compile();
    }
}
