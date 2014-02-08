/**
 *    Copyright 2014 Thomas Rausch
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.rauschig.wicketjs;

import java.util.ArrayList;
import java.util.List;

import org.rauschig.wicketjs.util.JsExpressionUtils;

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

    public JsFunction(IJsExpression body, String... parameters) {
        this(parameters, body);
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
