package org.rauschig.wicketjs;

import java.util.ArrayList;
import java.util.List;

/**
 * JsNamedFunction
 */
public class JsNamedFunction extends JsFunction {

    private JsIdentifier identifier;

    public JsNamedFunction(String identifier, String[] parameters, String body) {
        this(identifier, body, parameters);
    }

    public JsNamedFunction(String identifier, String[] parameters, IJsExpression body) {
        super(parameters, body);
    }

    public JsNamedFunction(String identifier, String body, String... parameters) {
        super(body, parameters);
        this.identifier = new JsIdentifier(identifier);
    }

    public JsNamedFunction(String identifier, String body) {
        this(identifier, new JsExpression(body));
    }

    public JsNamedFunction(String identifier, IJsExpression body) {
        this(new JsIdentifier(identifier), body);
    }

    public JsNamedFunction(JsIdentifier identifier, IJsExpression body) {
        this(identifier, new ArrayList<JsIdentifier>(), body);
    }

    public JsNamedFunction(JsIdentifier identifier, List<JsIdentifier> parameters, IJsExpression body) {
        super(parameters, body);
        this.identifier = identifier;
    }

    public JsIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
