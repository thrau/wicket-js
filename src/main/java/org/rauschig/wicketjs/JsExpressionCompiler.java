package org.rauschig.wicketjs;

/**
 * JsExpressionCompiler
 */
public class JsExpressionCompiler extends AbstractJsExpressionCompiler {

    protected IJsExpression expression;

    public JsExpressionCompiler(IJsExpression expression) {
        this.expression = expression;
    }

    protected void compileInto(StringBuilder builder) {
        expression.accept(this);
    }
}
