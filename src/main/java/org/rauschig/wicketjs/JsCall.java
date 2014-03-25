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

import org.rauschig.wicketjs.util.JsUtils;

/**
 * Represents a member function call.
 * <p/>
 * 
 * <pre>
 * new JsCall("console.log", "logging", 42, "this", JsExpression.THIS, new JsFunction(...));
 * </pre>
 * 
 * would compile to
 * 
 * <pre>
 * console.log('logging', 42, 'this', this, function(...){...});
 * </pre>
 */
public class JsCall extends AbstractJsExpression {

    private static final long serialVersionUID = -1541822119879211306L;

    private IJsExpression function;

    private List<IJsExpression> arguments;

    /**
     * Creates a new JsCall, calling the given functionName with the given arguments. Each argument is converted into a
     * JS syntax token.
     * 
     * @param functionName the function to call
     * @param arguments the arguments of the call
     * @see org.rauschig.wicketjs.util.JsUtils#asArgumentList(Object...)
     */
    public JsCall(String functionName, Object... arguments) {
        this(functionName, JsUtils.asArgumentList(arguments));
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
     * Alias for {@link #addArgument(Object)}.
     * 
     * @see #addArgument(Object)
     */
    public JsCall arg(Object argument) {
        return addArgument(argument);
    }

    /**
     * Alias for {@link #addArgument(IJsExpression)}.
     * 
     * @see #addArgument(IJsExpression)
     */
    public JsCall arg(IJsExpression argument) {
        return addArgument(argument);
    }

    /**
     * Converts the given Object into a JS syntax token and adds it as argument to the call.
     * 
     * @param argument the argument to add
     * @return this for chaining
     * @see org.rauschig.wicketjs.util.JsUtils#asArgument(Object)
     */
    public JsCall addArgument(Object argument) {
        return addArgument(JsUtils.asArgument(argument));
    }

    /**
     * Adds the given argument to the call.
     * 
     * @param argument the argument to add
     * @return this for chaining
     */
    public JsCall addArgument(IJsExpression argument) {
        arguments.add(argument);
        return this;
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }

}
