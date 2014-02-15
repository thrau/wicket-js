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

import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.IJsExpressionVisitor;
import org.rauschig.wicketjs.IJsStatement;
import org.rauschig.wicketjs.IJsStatementVisitor;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.JsCallChain;
import org.rauschig.wicketjs.JsExpression;
import org.rauschig.wicketjs.JsExpressionStatement;
import org.rauschig.wicketjs.JsFunction;
import org.rauschig.wicketjs.JsIdentifier;
import org.rauschig.wicketjs.JsIf;
import org.rauschig.wicketjs.JsLiteral;
import org.rauschig.wicketjs.JsNamedFunction;
import org.rauschig.wicketjs.JsStatement;
import org.rauschig.wicketjs.JsStatements;
import org.rauschig.wicketjs.util.JsonSerializer;

/**
 * Abstract implementation of the {@link org.rauschig.wicketjs.IJavaScript} syntax tree visitors used to build a
 * JavaScript string from a given syntax tree.
 * <p/>
 * The implementation is stateful as it uses a {@code StringBuilder} internally and <em>not</em> thread safe.
 */
public abstract class AbstractJsCompiler implements IJsExpressionVisitor, IJsStatementVisitor {

    /**
     * The default size to initialize the StringBuilder with
     */
    protected static final int DEFAULT_BUFFER_SIZE = 256;

    /**
     * The StringBuilder that holds the visited and compiled syntax tokens.
     */
    protected StringBuilder js;

    protected JsonSerializer jsonSerializer;

    /**
     * Lazy-init method for getting a JsonSerializer instance.
     * 
     * @return a JsonSerializer
     */
    protected JsonSerializer getJsonSerializer() {
        if (jsonSerializer == null) {
            jsonSerializer = createJsonSerializer();
        }

        return jsonSerializer;
    }

    /**
     * Factory method for creating a new JsonSerializer used by the lazy-init method {@link #getJsonSerializer()}.
     * 
     * @return a new JsonSerializer instance
     */
    protected JsonSerializer createJsonSerializer() {
        return new JsonSerializer();
    }

    /**
     * Executes the visitor and returns the compiled JavaScript as a string.
     * <p/>
     * The method returns the cached compilation result on multiple calls, rather than re-compiling the script.
     * 
     * @return JavaScript code as a String
     */
    public String compile() {
        if (js != null) {
            return js.toString();
        }

        js = new StringBuilder(DEFAULT_BUFFER_SIZE);
        compileInto(js);
        return js.toString();
    }

    /**
     * Called in {@link #compile()} with the initialized StringBuilder. Subclasses override this method to initialize
     * the compilation.
     * 
     * @param builder the StringBuilder containing the java script
     */
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
    public void visit(JsLiteral.JsArray visitable) {
        js.append(getJsonSerializer().serialize(visitable.getValue()));
    }

    @Override
    public void visit(JsLiteral.JsObject visitable) {
        js.append(getJsonSerializer().serialize(visitable.getValue()));
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
    public void accept(JsCallChain visitable) {
        visitAndJoin(".", visitable.getExpressions());
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

    @Override
    public void visit(JsStatements visitable) {
        visitAndJoin("", visitable.getStatements());
    }

    @Override
    public void visit(JsIf visitable) {
        js.append("if(");
        visitable.getExpression().accept(this);
        js.append(")");

        visitBlock(visitable.getThenBlock());

        if (visitable.getElseBlock() != null) {
            js.append("else");
            visitBlock(visitable.getElseBlock());
        }
    }

    protected void visitBlock(IJsStatement visitable) {
        js.append("{");
        visitable.accept(this);
        js.append("}");
    }

    /**
     * Visits the Arguments of the given {@link org.rauschig.wicketjs.JsCall}..
     * 
     * @param visitable the JsCall to visit
     */
    protected void visitArguments(JsCall visitable) {
        js.append("(");
        visitAndJoin(",", visitable.getArguments());
        js.append(")");
    }

    /**
     * Visit a {@link org.rauschig.wicketjs.JsFunction}s parameters and then its body.
     * 
     * @param visitable the JsFunction to visit
     */
    protected void visitFunctionParametersAndBody(JsFunction visitable) {
        visitFunctionParameters(visitable);
        visitFunctionBody(visitable);
    }

    /**
     * Visit a {@link org.rauschig.wicketjs.JsFunction}s parameters.
     * 
     * @param visitable the JsFunction to visit
     */
    protected void visitFunctionParameters(JsFunction visitable) {
        js.append("(");
        visitAndJoin(",", visitable.getParameters());
        js.append(")");
    }

    /**
     * Visit a {@link org.rauschig.wicketjs.JsFunction}s body.
     * 
     * @param visitable the JsFunction to visit
     */
    protected void visitFunctionBody(JsFunction visitable) {
        js.append("{");
        visitable.getBody().accept(this);
        js.append("}");
    }

    /**
     * Visits each IJavaScript element within the Iterable and appends the given delimiter after each item.
     * 
     * @param delimiter the delimiter used to join the items
     * @param iterable the items
     */
    protected void visitAndJoin(String delimiter, Iterable<? extends IJavaScript> iterable) {
        if (iterable == null) {
            return;
        }

        Iterator<? extends IJavaScript> iterator = iterable.iterator();

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
