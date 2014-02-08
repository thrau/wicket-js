package org.rauschig.wicketjs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JsExpressionList
 */
public class JsExpressionList implements IJsExpression {

    private List<IJsExpression> expressions;

    public JsExpressionList() {
        this(new ArrayList<IJsExpression>());
    }

    public JsExpressionList(IJsExpression... expressions) {
        this(new ArrayList<>(Arrays.asList(expressions)));
    }

    public JsExpressionList(List<IJsExpression> expressions) {
        this.expressions = expressions;
    }

    public List<IJsExpression> getExpressions() {
        return expressions;
    }

    public JsExpressionList add(String expression) {
        return add(new JsExpression(expression));
    }

    public JsExpressionList add(IJsExpression expression) {
        expressions.add(expression);
        return this;
    }

    /**
     * Shorthand for {@link #add(String)}.
     * 
     * @param expression the expression to add
     * @return this for chaining
     */
    public JsExpressionList _(String expression) {
        return add(expression);
    }

    /**
     * Shorthand for {@link #add(IJsExpression)}.
     * 
     * @param expression the expression to add
     * @return this for chaining
     */
    public JsExpressionList _(IJsExpression expression) {
        return add(expression);
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
