package org.rauschig.wicketjs;

import java.io.Serializable;

/**
 * Tagging interface for our simple js grammar abstraction.
 */
public interface IJsExpression extends Serializable {
    void accept(IJsExpressionVisitor visitor);
}
