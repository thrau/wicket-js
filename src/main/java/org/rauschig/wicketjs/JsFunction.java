package org.rauschig.wicketjs;

import java.util.ArrayList;
import java.util.List;

/**
 * Anonymous function definition.
 */
public class JsFunction extends AbstractJsExpression {

    private List<JsIdentifier> parameters;
    private IJsExpression body;

    public JsFunction(String body) {
        this(new JsExpression(body));
    }

    public JsFunction(String[] parameters, String body) {
        this(body, parameters);
    }

    public JsFunction(String body, String... parameters) {
        this(parameters, new JsExpression(body));
    }

    public JsFunction(IJsExpression body) {
        this(new ArrayList<JsIdentifier>(), body);
    }

    public JsFunction(String[] parameters, IJsExpression body) {
        this(JsExpressionUtils.asIdentifierList(parameters), body);
    }

    public JsFunction(List<JsIdentifier> parameters, IJsExpression body) {
        this.parameters = parameters;
        this.body = body;
    }

    public JsFunction param(String parameter) {
        return addParameter(parameter);
    }

    public JsFunction param(JsIdentifier parameter) {
        return addParameter(parameter);
    }

    public JsFunction addParameter(String parameter) {
        return addParameter(new JsIdentifier(parameter));
    }

    public JsFunction addParameter(JsIdentifier parameter) {
        getParameters().add(parameter);
        return this;
    }

    public List<JsIdentifier> getParameters() {
        return parameters;
    }

    public IJsExpression getBody() {
        return body;
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
