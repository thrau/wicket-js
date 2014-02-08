package org.rauschig.wicketjs.compiler;

import java.util.List;

import org.rauschig.wicketjs.IJsExpression;

/**
 * JsExpressionJoiner
 */
public class JsExpressionJoiner extends AbstractJsExpressionCompiler {
    private List<IJsExpression> expressions;
    private String delimiter;

    public JsExpressionJoiner(List<IJsExpression> expressions, String delimiter) {
        this.expressions = expressions;
        this.delimiter = delimiter;
    }

    @Override
    protected void compileInto(StringBuilder builder) {
        visitAndJoin(delimiter, expressions);
    }
}
