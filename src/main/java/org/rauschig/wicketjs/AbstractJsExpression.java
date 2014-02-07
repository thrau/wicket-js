package org.rauschig.wicketjs;

/**
 * AbstractJsExpression
 */
public abstract class AbstractJsExpression implements IJsExpression {

    public JsExpressionList _(IJsExpression expression) {
        return add(expression);
    }

    public JsExpressionList add(IJsExpression expression) {
        return new JsExpressionList(this, expression);
    }

}
