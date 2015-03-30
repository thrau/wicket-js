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

import org.rauschig.wicketjs.util.JsUtils;

/**
 * A semi-colon terminated list of statements that can be used where a IJsStatement is expected, but multiple statements
 * should be executed.
 * <p>
 * Use the varargs constructor to conveniently concatenate statements
 * </p>
 * 
 * <pre>
 * new JsStatements(new JsCall(&quot;console.log&quot;, &quot;about to pop up an alert&quot;), new JsCall(&quot;alert&quot;, &quot;calling something&quot;));
 * </pre>
 * 
 * 
 * <p>
 * The same thing can be done via chaining
 * </p>
 * 
 * <pre>
 * new JsStatements()._(new JsCall(&quot;console.log&quot;, &quot;about to pop up an alert&quot;))._(
 *         new JsCall(&quot;alert&quot;, &quot;calling something&quot;));
 * </pre>
 * 
 */
public class JsStatements implements IJsStatement {

    private static final long serialVersionUID = -5422270276826187017L;

    private List<IJsStatement> statements;

    public JsStatements() {
        this(new ArrayList<IJsStatement>());
    }

    public JsStatements(IJavaScript javaScript) {
        this(JsStatement.of(javaScript));
    }

    public JsStatements(IJsStatement statement) {
        this();
        add(statement);
    }

    /**
     * Convenience constructor for chaining an IJavaScript from within a JsStatement.
     * 
     * @param statement the first statement to chain
     * @param javaScript the IJavaScript to chain
     */
    public JsStatements(IJsStatement statement, IJavaScript javaScript) {
        this();
        add(statement);
        add(JsStatement.of(javaScript));
    }

    /**
     * Creates a new concatenated statement of the given IJsStatement objects
     * 
     * @param statements the statements to add
     */
    public JsStatements(IJsStatement... statements) {
        this(new ArrayList<>(Arrays.asList(statements)));
    }

    /**
     * Converts all given IJavaScript objects to JsStatement objects and adds them to the JsStatements list.
     * 
     * @param javaScript the IJavaScript instances to add
     */
    public JsStatements(IJavaScript... javaScript) {
        this(JsUtils.asStatementList(javaScript));
    }

    public JsStatements(List<IJsStatement> statements) {
        this.statements = statements;
    }

    /**
     * Shorthand for {@link #add(CharSequence)}
     * 
     * @param statement the JavaScript code to chain
     * @return this for chaining
     * @see #add(CharSequence)
     */
    public JsStatements _(CharSequence statement) {
        return add(statement);
    }

    /**
     * Shorthand for {@link #add(IJsExpression)}
     * 
     * @param expression the expression to chain
     * @return this for chaining
     * @see #add(IJsExpression)
     */
    public JsStatements _(IJsExpression expression) {
        return add(expression);
    }

    /**
     * Shorthand for {@link #add(IJsStatement)}
     * 
     * @param statement the JavaScript code to chain
     * @return this for chaining
     * @see #add(IJsStatement)
     */
    public JsStatements _(IJsStatement statement) {
        return add(statement);
    }

    /**
     * Interpret the given CharSequence as a raw JavaScript statement and chain it to the JsStatements list.
     * 
     * @param statement the JavaScript code to chain
     * @return this for chaining
     */
    public JsStatements add(CharSequence statement) {
        return add(new JsStatement(statement));
    }

    /**
     * Terminate the given IJsExpression as a statement and chain it to the JsStatements list.
     * 
     * @param expression the expression to chain
     * @return this for chaining
     */
    public JsStatements add(IJsExpression expression) {
        return add(expression.terminate());
    }

    /**
     * Chain the given IJsStatement to to the JsStatements list.
     * 
     * @param statement the statement to chain
     * @return this for chaining
     */
    public JsStatements add(IJsStatement statement) {
        statements.add(statement);
        return this;
    }

    public List<IJsStatement> getStatements() {
        return statements;
    }

    @Override
    public void accept(IJsStatementVisitor visitor) {
        visitor.visit(this);
    }
}
