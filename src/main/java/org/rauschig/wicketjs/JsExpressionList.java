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
 * JsExpressionList
 */
public class JsExpressionList implements IJsExpression {

    private static final long serialVersionUID = -4270940313346891683L;

    private List<IJsExpression> expressions;

    public JsExpressionList() {
        this(new ArrayList<IJsExpression>());
    }

    public JsExpressionList(IJsExpression... expressions) {
        this(new ArrayList<>(Arrays.asList(expressions)));
    }

    public JsExpressionList(List<IJsExpression> expressions) {
        this.expressions = expressions;
    }

    public List<IJsExpression> getExpressions() {
        return expressions;
    }

    public JsExpressionList add(String expression) {
        return add(new JsExpression(expression));
    }

    public JsExpressionList add(IJsExpression expression) {
        expressions.add(expression);
        return this;
    }

    /**
     * Shorthand for {@link #add(String)}.
     * 
     * @param expression the expression to add
     * @return this for chaining
     */
    public JsExpressionList _(String expression) {
        return add(expression);
    }

    /**
     * Shorthand for {@link #add(IJsExpression)}.
     * 
     * @param expression the expression to add
     * @return this for chaining
     */
    public JsExpressionList _(IJsExpression expression) {
        return add(expression);
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
