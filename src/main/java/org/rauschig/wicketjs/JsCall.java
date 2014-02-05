package org.rauschig.wicketjs;

import java.util.List;

/**
 * JsCall
 */
public class JsCall implements IJsExpression {

    private IJsExpression function;
    private List<IJsExpression> arguments;

    public JsCall(String functionName, Object... arguments) {
        this.function = new JsIdentifier(functionName);
    }

    public void addArgument(Object argument) {

    }

    public void addArgument(IJsExpression argument) {
        arguments.add(argument);
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }

    public IJsExpression getFunction() {
        return function;
    }

    public List<IJsExpression> getArguments() {
        return arguments;
    }
}
