package org.rauschig.wicketjs;

import java.io.Serializable;

/**
 * JsIdentifier
 */
public class JsIdentifier implements IJsExpression {

    private final String identifier;

    public JsIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
