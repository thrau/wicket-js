package org.rauschig.wicketjs;

import java.io.Serializable;

/**
 * Tagging interface for our simple JavaScript grammar abstraction.
 */
public interface IJsExpression extends Serializable {
    void accept(IJsExpressionVisitor visitor);
}
