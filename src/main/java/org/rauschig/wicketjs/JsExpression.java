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
 * A raw expression that may contain arbitrary JavaScript code.
 */
public class JsExpression extends AbstractJsExpression {

    private static final long serialVersionUID = -8493639793137369591L;

    /**
     * The "null" keyword.
     */
    public static final JsExpression NULL = new JsExpression("null");

    /**
     * The "this" keyword.
     */
    public static final JsExpression THIS = new JsExpression("this");

    private CharSequence expression;

    /**
     * Wraps a raw JavaScript expression string.
     * 
     * @param expression the JavaScript expression as CharSequence
     */
    public JsExpression(CharSequence expression) {
        this.expression = expression;
    }

    /**
     * Returns the expression as CharSequence.
     * 
     * @return the expression
     */
    public CharSequence getExpression() {
        return expression;
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
