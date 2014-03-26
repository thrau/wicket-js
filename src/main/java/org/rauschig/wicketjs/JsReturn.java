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

/**
 * A return statement with an optional expression.
 * 
 * <pre>
 *     return[ &lt;expression&gt;];
 * </pre>
 */
public class JsReturn implements IJsStatement {

    private static final long serialVersionUID = 2906173231646725779L;

    private IJsExpression expression;

    /**
     * Creates a void return statement
     */
    public JsReturn() {
    }

    public JsReturn(CharSequence expression) {
        this(new JsExpression(expression));
    }

    public JsReturn(IJsExpression expression) {
        this.expression = expression;
    }

    /**
     * Checks whether the return statement has an optional expression or not.
     * 
     * @return true if no expression is set
     */
    public boolean isVoid() {
        return getExpression() == null;
    }

    public IJsExpression getExpression() {
        return expression;
    }

    @Override
    public void accept(IJsStatementVisitor visitor) {
        visitor.visit(this);
    }
}
