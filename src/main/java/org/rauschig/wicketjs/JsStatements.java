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
 * JsStatements
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

    public JsStatements(IJsStatement statement, IJavaScript javaScript) {
        this();
        add(statement);
        add(JsStatement.of(javaScript));
    }

    public JsStatements(IJsStatement... statements) {
        this(new ArrayList<>(Arrays.asList(statements)));
    }

    public JsStatements(IJavaScript... javaScript) {
        this(JsUtils.asStatementList(javaScript));
    }

    public JsStatements(List<IJsStatement> statements) {
        this.statements = statements;
    }

    public JsStatements _(CharSequence statement) {
        return add(statement);
    }

    public JsStatements _(IJsExpression expression) {
        return add(expression);
    }

    public JsStatements _(IJsStatement statement) {
        return add(statement);
    }

    public JsStatements add(CharSequence statement) {
        return add(new JsStatement(statement));
    }

    public JsStatements add(IJsExpression expression) {
        return add(expression.terminate());
    }

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
