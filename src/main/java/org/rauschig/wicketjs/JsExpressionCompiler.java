package org.rauschig.wicketjs;

import java.util.List;

/**
 * JsExpressionCompiler
 */
public class JsExpressionCompiler implements IJsExpressionVisitor {

    private StringBuilder js;

    private IJsExpression expression;

    public JsExpressionCompiler(IJsExpression expression) {
        this.expression = expression;
    }

    public String compile() {
        if (js != null) {
            return js.toString();
        }

        js = new StringBuilder();

        expression.accept(this);

        return js.toString();
    }

    @Override
    public void visit(JsLiteral.JsNumber visitable) {
        js.append(visitable.getValue().toString()); // TODO: number formatting
    }

    @Override
    public void visit(JsLiteral.JsBoolean visitable) {
        js.append(visitable.getValue().toString());
    }

    @Override
    public void visit(JsLiteral.JsString visitable) {
        js.append("'");
        js.append(visitable.getValue());
        js.append("'");
    }

    @Override
    public void visit(JsIdentifier visitable) {
        js.append(visitable.getIdentifier());
    }

    @Override
    public void visit(JsExpression visitable) {
        js.append(visitable.getExpression());
    }

    @Override
    public void visit(JsCall visitable) {
        visitable.getFunction().accept(this);
        js.append("(");

        List<IJsExpression> arguments = visitable.getArguments();
        if (arguments != null && !arguments.isEmpty()) {
            int i = 0;
            for (; i < (arguments.size() - 1); i++) {
                arguments.get(i).accept(this);
                js.append(",");
            }
            arguments.get(i).accept(this);
        }

        js.append(");");
    }

    @Override
    public void visit(JsFunction visitable) {

    }

    @Override
    public void visit(JsNamedFunction visitable) {

    }
}
