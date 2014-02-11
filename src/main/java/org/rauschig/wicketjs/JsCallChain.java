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
import java.util.Arrays;
import java.util.List;

/**
 * JsCallChain
 */
public class JsCallChain extends AbstractJsExpression {

    private static final long serialVersionUID = -5447236481139701035L;

    private List<IJsExpression> expressions;

    public JsCallChain() {
        this(new ArrayList<IJsExpression>());
    }

    public JsCallChain(IJsExpression expression) {
        this();
        chain(expression);
    }

    public JsCallChain(IJsExpression... expressions) {
        this(new ArrayList<>(Arrays.asList(expressions)));
    }

    protected JsCallChain(List<IJsExpression> expressions) {
        this.expressions = expressions;
    }

    public JsCallChain _(IJsExpression expression) {
        return chain(expression);
    }

    public JsCallChain _(String identifier) {
        return chain(new JsIdentifier(identifier));
    }

    public JsCallChain _(String functionName, Object... arguments) {
        return chain(new JsCall(functionName, arguments));
    }

    public JsCallChain chain(IJsExpression expression) {
        expressions.add(expression);
        return this;
    }

    public JsCallChain chain(IJsExpression... expressions) {
        this.expressions.addAll(Arrays.asList(expressions));
        return this;
    }

    public JsCallChain chain(String identifier) {
        return chain(new JsIdentifier(identifier));
    }

    public JsCallChain chain(String functionName, Object... arguments) {
        return chain(new JsCall(functionName, arguments));
    }

    public JsCallChain call(String functionName) {
        return chain(new JsCall(functionName));
    }

    public JsCallChain call(String functionName, Object... arguments) {
        return chain(functionName, arguments);
    }

    public List<IJsExpression> getExpressions() {
        return expressions;
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.accept(this);
    }

}
