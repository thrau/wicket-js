package org.rauschig.wicketjs;

/**
 * JsIdentifier
 */
public class JsIdentifier extends AbstractJsExpression {

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (obj instanceof String) {
            return identifier.equals(obj);
        } else if (obj instanceof JsIdentifier) {
            return identifier.equals(((JsIdentifier) obj).getIdentifier());
        } else {
            return super.equals(obj);
        }
    }
}
