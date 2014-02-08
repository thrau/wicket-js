package org.rauschig.wicketjs;

import org.apache.wicket.util.io.IClusterable;

/**
 * Tagging interface for our simple JavaScript grammar abstraction.
 */
public interface IJsExpression extends IClusterable {

    /**
     * Accept the given visitor. Visitor Double-dispatch mechanism.
     * 
     * @param visitor the visiting visitor
     */
    void accept(IJsExpressionVisitor visitor);
}
