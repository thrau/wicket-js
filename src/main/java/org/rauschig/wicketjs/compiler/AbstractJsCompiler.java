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
package org.rauschig.wicketjs.compiler;

import java.util.Iterator;

import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.IJsExpressionVisitor;
import org.rauschig.wicketjs.IJsStatement;
import org.rauschig.wicketjs.IJsStatementVisitor;
import org.rauschig.wicketjs.IJsVisitable;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.JsExpression;
import org.rauschig.wicketjs.JsExpressionStatement;
import org.rauschig.wicketjs.JsFunction;
import org.rauschig.wicketjs.JsIdentifier;
import org.rauschig.wicketjs.JsLiteral;
import org.rauschig.wicketjs.JsNamedFunction;
import org.rauschig.wicketjs.JsStatement;

/**
 * AbstractJsCompiler
 */
public abstract class AbstractJsCompiler implements IJsExpressionVisitor, IJsStatementVisitor {

    protected static final int DEFAULT_BUFFER_SIZE = 256;

    protected StringBuilder js;

    public String compile() {
        if (js != null) {
            return js.toString();
        }

        js = new StringBuilder(DEFAULT_BUFFER_SIZE);
        compileInto(js);
        return js.toString();
    }

    protected abstract void compileInto(StringBuilder builder);

    @Override
    public void visit(JsLiteral.JsNumber visitable) {
        js.append(visitable.getValue().toString()); // TODO: number formatting
    }

    @Override
    public void visit(JsLiteral.JsBoolean visitable) {
        js.append(visitable.getValue().toString());
    }

    @Override
    public void visit(JsLiteral.JsString visitable) {
        js.append("'");
        js.append(visitable.getValue());
        js.append("'");
    }

    @Override
    public void visit(JsLiteral.ObjectLiteral visitable) {
        // TODO: jsonify
        throw new UnsupportedOperationException("Can not yet compile object literals");
    }

    @Override
    public void visit(JsIdentifier visitable) {
        js.append(visitable.getIdentifier());
    }

    @Override
    public void visit(JsExpression visitable) {
        js.append(visitable.getExpression());
    }

    @Override
    public void visit(JsCall visitable) {
        visitable.getFunction().accept(this);
        visitArguments(visitable);
    }

    @Override
    public void visit(JsFunction visitable) {
        js.append("function");
        visitFunctionParametersAndBody(visitable);
    }

    @Override
    public void visit(JsNamedFunction visitable) {
        js.append("function ");
        visitable.getIdentifier().accept(this);
        visitFunctionParametersAndBody(visitable);
    }

    @Override
    public void visit(JsExpressionStatement visitable) {
        visitable.getExpression().accept(this);
        js.append(";");
    }

    @Override
    public void visit(JsStatement visitable) {
        js.append(visitable.getStatement());
        js.append(";");
    }

    protected void visitArguments(JsCall visitable) {
        js.append("(");
        visitAndJoin(",", visitable.getArguments());
        js.append(")");
    }

    protected void visitFunctionParametersAndBody(JsFunction visitable) {
        visitFunctionParameters(visitable);
        visitFunctionBody(visitable);
    }

    protected void visitFunctionParameters(JsFunction visitable) {
        js.append("(");
        visitAndJoin(",", visitable.getParameters());
        js.append(")");
    }

    protected void visitFunctionBody(JsFunction visitable) {
        js.append("{");
        visitable.getBody().accept(this);
        js.append("}");
    }

    protected void visitAndJoin(String delimiter, Iterable<? extends IJsVisitable> list) {
        if (list == null) {
            return;
        }

        Iterator<? extends IJsVisitable> iterator = list.iterator();

        while (iterator.hasNext()) {
            Object next = iterator.next();

            if (next instanceof IJsExpression) {
                ((IJsExpression) next).accept(this);
            } else if (next instanceof IJsStatement) {
                ((IJsStatement) next).accept(this);
            } else {
                continue;
            }

            if (iterator.hasNext()) {
                js.append(delimiter);
            }
        }
    }

}
