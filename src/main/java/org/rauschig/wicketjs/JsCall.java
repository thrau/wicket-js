package org.rauschig.wicketjs;

import java.util.ArrayList;
import java.util.List;

/**
 * JsCall
 */
public class JsCall implements IJsExpression {

    private IJsExpression function;
    private List<IJsExpression> arguments;

    public JsCall(String functionName, Object... arguments) {
        this(functionName, JsExpressionUtils.asArgumentList(arguments));
    }

    public JsCall(String function) {
        this(new JsIdentifier(function));
    }

    public JsCall(String function, List<IJsExpression> arguments) {
        this(new JsIdentifier(function), arguments);
    }

    public JsCall(IJsExpression function) {
        this(function, new ArrayList<IJsExpression>());
    }

    public JsCall(IJsExpression function, List<IJsExpression> arguments) {
        this.function = function;
        this.arguments = arguments;
    }

    public IJsExpression getFunction() {
        return function;
    }

    public List<IJsExpression> getArguments() {
        return arguments;
    }

    /**
     * Shorthand for {@link #addArgument(Object)}.
     * 
     * @param argument
     * @return this for chaining
     */
    public JsCall arg(Object argument) {
        return addArgument(argument);
    }

    /**
     * Shorthand for {@link #addArgument(IJsExpression)}.
     * 
     * @param argument
     * @return this for chaining
     */
    public JsCall arg(IJsExpression argument) {
        return addArgument(argument);
    }

    public JsCall addArgument(Object argument) {
        return addArgument(JsExpressionUtils.asArgument(argument));
    }

    public JsCall addArgument(IJsExpression argument) {
        arguments.add(argument);
        return this;
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }

}
