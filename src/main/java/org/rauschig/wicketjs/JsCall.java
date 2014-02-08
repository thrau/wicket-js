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
 * Represents a member call.
 */
public class JsCall extends AbstractJsExpression {

    private static final long serialVersionUID = -1541822119879211306L;

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
