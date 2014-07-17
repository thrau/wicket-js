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
 * A JavaScript <code>if</code> statement.
 * 
 * <pre>
 * if(&lt;expression&gt;){
 *    &lt;thenBlock&gt;
 * }
 * [else{
 *    &lt;elseBlock&gt;
 * }]
 * </pre>
 */
public class JsIf extends AbstractJsStatement {

    private static final long serialVersionUID = -3465855189099342658L;

    private IJsExpression expression;

    private IJsStatement thenBlock;
    private IJsStatement elseBlock;

    public JsIf(CharSequence expression, IJsStatement thenBlock) {
        this(new JsExpression(expression), thenBlock);
    }

    public JsIf(IJsExpression expression, IJavaScript thenBlock) {
        this(expression, thenBlock, null);
    }

    public JsIf(IJsExpression expression, IJavaScript thenBlock, IJavaScript elseBlock) {
        this(expression, JsStatement.of(thenBlock), JsStatement.of(elseBlock));
    }

    public JsIf(IJsExpression expression, IJsStatement thenBlock, IJsStatement elseBlock) {
        this.expression = expression;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    public IJsExpression getExpression() {
        return expression;
    }

    public IJsStatement getThenBlock() {
        return thenBlock;
    }

    public IJsStatement getElseBlock() {
        return elseBlock;
    }

    @Override
    public void accept(IJsStatementVisitor visitor) {
        visitor.visit(this);
    }
}
