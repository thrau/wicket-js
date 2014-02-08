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

import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.IJsStatement;

/**
 * An AbstractJsCompiler implementation that compiles a IJsStatement or IJsExpression.
 */
public class JsCompiler extends AbstractJsCompiler {

    protected IJavaScript visitable;

    /**
     * Creates a new JsCompiler that compiles the given IJavaScript.
     * 
     * @param visitable the Statement or Expression to compile
     */
    public JsCompiler(IJavaScript visitable) {
        if (visitable == null) {
            throw new IllegalArgumentException("No Expression or Statement given");
        }

        this.visitable = visitable;
    }

    @Override
    protected void compileInto(StringBuilder builder) {
        if (visitable instanceof IJsExpression) {
            ((IJsExpression) visitable).accept(this);
        } else if (visitable instanceof IJsStatement) {
            ((IJsStatement) visitable).accept(this);
        } else {
            throw new IllegalArgumentException("Unknown visitable type " + visitable.getClass());
        }
    }
}
