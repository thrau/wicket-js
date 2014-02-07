package org.rauschig.wicketjs;

import org.apache.wicket.util.io.IClusterable;

/**
 * Tagging interface for our simple JavaScript grammar abstraction.
 */
public interface IJsExpression extends IClusterable {
    void accept(IJsExpressionVisitor visitor);
}
