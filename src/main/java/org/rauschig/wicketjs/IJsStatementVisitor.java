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
 * A visitor of the IJsStatement hierarchy.
 */
public interface IJsStatementVisitor {

    /**
     * Visits the given Statement.
     * 
     * @param visitable the Statement to visit
     */
    void visit(JsStatement visitable);

    /**
     * Visits the given Statement.
     * 
     * @param visitable the Statement to visit
     */
    void visit(JsStatements visitable);

    /**
     * Visits the given Statement.
     * 
     * @param visitable the Statement to visit
     */
    void visit(JsExpressionStatement visitable);

    /**
     * Visits the given Statement.
     * 
     * @param visitable the Statement to visit
     */
    void visit(JsIf visitable);

    /**
     * Visits the given Statement.
     * 
     * @param visitable the Statement to visit
     */
    void visit(JsVariableDefinition visitable);

    /**
     * Visits the given Statement.
     * 
     * @param visitable the Statement to visit
     */
    void visit(JsReturn visitable);
}
