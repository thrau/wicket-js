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

import org.rauschig.wicketjs.util.JsUtils;

/**
 * A variable definition statement with an optional assignment.
 * 
 * <pre>
 *     var &lt;identifier&gt;[ = &lt;value&gt;]
 * </pre>
 */
public class JsVariableDefinition extends AbstractJsStatement {

    private static final long serialVersionUID = 1042673656813210505L;

    private JsAssignment assignment;

    public JsVariableDefinition(String identifier) {
        this(new JsIdentifier(identifier));
    }

    public JsVariableDefinition(JsIdentifier identifier) {
        this(identifier, (IJsExpression) null);
    }

    public JsVariableDefinition(String identifier, Object value) {
        this(new JsIdentifier(identifier), value);
    }

    public JsVariableDefinition(JsIdentifier identifier, Object value) {
        this(identifier, JsUtils.asArgument(value));
    }

    public JsVariableDefinition(JsIdentifier identifier, IJsExpression value) {
        this.assignment = new JsAssignment(identifier, value);
    }

    public JsAssignment getAssignment() {
        return assignment;
    }

    @Override
    public void accept(IJsStatementVisitor visitor) {
        visitor.visit(this);
    }
}
