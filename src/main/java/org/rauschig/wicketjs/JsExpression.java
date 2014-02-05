package org.rauschig.wicketjs;

/**
 * A raw expression that may contain arbitrary JavaScript code.
 */
public class JsExpression implements IJsExpression {

    public static final JsExpression NULL = new JsExpression("null");
    public static final JsExpression THIS = new JsExpression("this");

    private CharSequence expression;

    public JsExpression(CharSequence expression) {
        this.expression = expression;
    }

    public CharSequence getExpression() {
        return expression;
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
