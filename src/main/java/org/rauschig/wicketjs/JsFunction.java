package org.rauschig.wicketjs;

/**
 * A Function definition.
 */
public class JsFunction implements IJsExpression {

    private IJsExpression body;

    public JsFunction(String body) {
        this.body = new JsExpression(body);
    }

    public JsFunction(IJsExpression body) {
        this.body = body;
    }

    public IJsExpression getBody() {
        return body;
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
