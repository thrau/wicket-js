package org.rauschig.wicketjs.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestHandler;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

/**
 * AbstractJsBehavior.
 */
public abstract class AbstractJsBehavior extends AbstractAjaxBehavior {

    /**
     * Returns the markup id of the component this Behavior is bound to.
     * 
     * @return a markup id
     */
    public String id() {
        return getComponent().getMarkupId();
    }

    @Override
    protected void onBind() {
        getComponent().setOutputMarkupId(true);
    }

    @Override
    public void onEvent(Component component, IEvent<?> event) {
        super.onEvent(component, event);

        if (event.getPayload() instanceof AjaxRequestHandler) {
            onAjaxEvent((AjaxRequestHandler) event.getPayload());
        }
    }

    @Override
    public void onRequest() {
        // we usually don't need this
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        if (component.isEnabledInHierarchy()) {
            onRenderHead(response);
            renderDomReadyItem(response);
        }
    }

    /**
     * Executed on events that have an AjaxRequestHandler as payload.
     * <p/>
     * This method is also called if other components issue Ajax events that do not directly concern the component this
     * behavior instance is bound to!
     * 
     * @param target the ajax request target
     * @see #onEvent(org.apache.wicket.Component, org.apache.wicket.event.IEvent)
     */
    protected void onAjaxEvent(AjaxRequestTarget target) {
        // hook
    }

    /**
     * Renders the header item to the given response that binds the domReadyJs behavior to the component. Uses the
     * header item returned by {@link #getOnDomReadyHeaderItem}.
     * 
     * @param response the Response object to render the item to
     */
    protected void renderDomReadyItem(IHeaderResponse response) {
        response.render(getOnDomReadyHeaderItem());
    }

    /**
     * Called when rendering the head, just before {@link #renderDomReadyItem} is called.
     * 
     * @param response Response object
     */
    protected void onRenderHead(IHeaderResponse response) {
        // hook
    }

    /**
     * Creates a new OnDomReadyHeaderItem that is added to the IHeaderResponse.
     * 
     * @return a new OnDomReadyHeaderItem
     */
    protected OnDomReadyHeaderItem getOnDomReadyHeaderItem() {
        StringBuilder js = new StringBuilder();

        prependOnDomReadyScript(js);
        buildDomReadyScript(js);
        appendOnDomReadyScript(js);

        return OnDomReadyHeaderItem.forScript(js.toString());
    }

    /**
     * Allows you to prepend javascript to the OnDomReadyHeaderItem.
     * 
     * @param js the string builder that contains the JavaScript
     */
    protected void prependOnDomReadyScript(StringBuilder js) {
        // hook
    }

    /**
     * Builds the main body of the JavaScript that is loaded into the OnDomReadyHeaderItem.
     * 
     * @param js the string builder that contains the JavaScript
     */
    protected void buildDomReadyScript(StringBuilder js) {
        js.append(domReadyScript());
    }

    /**
     * Allows you to append javascript to the OnDomReadyHeaderItem.
     * 
     * @param js the string builder that contains the JavaScript
     */
    protected void appendOnDomReadyScript(StringBuilder js) {
        // hook
    }

    /**
     * Returns the main body of the JavaScript that is loaded into the OnDomReadyHeaderItem.
     * 
     * @return a CharSequence containing JavaScript code
     */
    protected abstract CharSequence domReadyScript();
}
