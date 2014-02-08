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
 * An anonymous function definition.
 */
public class JsFunction implements IJsExpression {

    private static final long serialVersionUID = -2514816269156224568L;

    private List<JsIdentifier> parameters;
    private IJsStatement body;

    public JsFunction(String body) {
        this(new JsExpressionStatement(body));
    }

    public JsFunction(String body, String... parameters) {
        this(parameters, new JsExpressionStatement(body));
    }

    public JsFunction(IJavaScript body) {
        this(JsStatement.of(body));
    }

    public JsFunction(IJsStatement body) {
        this(new ArrayList<JsIdentifier>(), body);
    }

    public JsFunction(IJavaScript body, String... parameters) {
        this(parameters, body);
    }

    public JsFunction(String[] parameters, IJavaScript body) {
        this(JsExpressionUtils.asIdentifierList(parameters), body);
    }

    public JsFunction(List<JsIdentifier> parameters, IJavaScript body) {
        this.parameters = parameters;
        this.body = JsStatement.of(body);
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

    public IJsStatement getBody() {
        return body;
    }

    public IJsStatement terminate() {
        return JsStatement.of(this);
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
