package org.rauschig.wicketjs;

/**
 * JsNamedFunction
 */
public class JsNamedFunction extends JsFunction {

    private JsIdentifier identifier;

    public JsNamedFunction(String identifier, String body) {
        super(body);
        this.identifier = new JsIdentifier(identifier);
    }

    public JsNamedFunction(JsIdentifier identifier, IJsExpression body) {
        super(body);
        this.identifier = identifier;
    }

    public JsIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
