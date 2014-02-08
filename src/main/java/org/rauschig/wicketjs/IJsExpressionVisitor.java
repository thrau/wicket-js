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

import java.io.Serializable;

/**
 * A visitor of the IJsExpression hierarchy.
 */
public interface IJsExpressionVisitor extends Serializable {

    void visit(JsLiteral.JsNumber visitable);

    void visit(JsLiteral.JsBoolean visitable);

    void visit(JsLiteral.JsString visitable);

    void visit(JsLiteral.ObjectLiteral visitable);

    void visit(JsIdentifier visitable);

    void visit(JsExpression visitable);

    void visit(JsCall visitable);

    void visit(JsFunction visitable);

    void visit(JsNamedFunction visitable);

    void visit(JsExpressionList visitable);
}
