package org.rauschig.wicketjs.markup;

/**
 * Contract for retrieval of markup ids from objects that may be a component or hold one. This is required for the
 * easier use of the JQuery object as it heavily relies on markup ids to function properly.
 */
public interface IComponentMarkupIdProvider {
    /**
     * Returns the markup id.
     * 
     * @return a markup id.
     */
    String getComponentMarkupId();
}
