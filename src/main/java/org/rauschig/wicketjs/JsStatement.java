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
 * A raw statement that may contain arbitrary JavaScript code.
 */
public class JsStatement extends AbstractJsStatement {

    private static final long serialVersionUID = -5422270276826187017L;

    private CharSequence statement;

    /**
     * Wraps a raw JavaScript statement string.
     * 
     * @param statement the JavaScript statement as CharSequence
     */
    public JsStatement(CharSequence statement) {
        this.statement = statement;
    }

    /**
     * Returns the statement as CharSequence.
     * 
     * @return the statement
     */
    public CharSequence getStatement() {
        return statement;
    }

    /**
     * Converts the given IJavaScript syntax tree node to a statement. If it is an expression, it will be terminated to
     * a {@link org.rauschig.wicketjs.JsExpressionStatement}, if it is already a statement, it is cast and returned.
     * 
     * @param javaScript the syntax tree node
     * @return a Statement
     */
    public static IJsStatement of(IJavaScript javaScript) {
        if (javaScript instanceof IJsStatement) {
            return (IJsStatement) javaScript;
        } else if (javaScript instanceof IJsExpression) {
            return of((IJsExpression) javaScript);
        } else {
            return null;
        }
    }

    /**
     * Terminates the given IJsExpression to a Statement.
     * 
     * @param expression the expression to terminate
     * @return a JsExpressionStatement wrapping the given expression
     */
    public static JsExpressionStatement of(IJsExpression expression) {
        return new JsExpressionStatement(expression);
    }

    @Override
    public void accept(IJsStatementVisitor visitor) {
        visitor.visit(this);
    }
}
