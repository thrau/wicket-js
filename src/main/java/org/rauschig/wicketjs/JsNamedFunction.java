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
import java.util.List;

/**
 * A named function definition.
 * 
 * The JavaScript of which would look like:
 * 
 * <pre>
 *     function &lt;identifier&gt; ([parameters]) {
 *         &lt;body&gt;
 *     }
 * </pre>
 */
public class JsNamedFunction extends JsFunction {

    private static final long serialVersionUID = 2365745891851116336L;

    private JsIdentifier identifier;

    public JsNamedFunction(CharSequence identifier, String[] parameters, CharSequence body) {
        this(identifier, body, parameters);
    }

    public JsNamedFunction(CharSequence identifier, String[] parameters, IJsExpression body) {
        super(parameters, body);
    }

    public JsNamedFunction(CharSequence identifier, String[] parameters, IJsStatement body) {
        super(parameters, body);
    }

    public JsNamedFunction(CharSequence identifier, CharSequence body, String... parameters) {
        super(body, parameters);
        this.identifier = new JsIdentifier(identifier);
    }

    public JsNamedFunction(CharSequence identifier, CharSequence body) {
        this(identifier, new JsExpression(body));
    }

    public JsNamedFunction(CharSequence identifier, IJsExpression body) {
        this(new JsIdentifier(identifier), body);
    }

    public JsNamedFunction(CharSequence identifier, IJsStatement body) {
        this(new JsIdentifier(identifier), body);
    }

    public JsNamedFunction(JsIdentifier identifier, IJsExpression body) {
        this(identifier, new ArrayList<JsIdentifier>(), body);
    }

    public JsNamedFunction(JsIdentifier identifier, IJsStatement body) {
        this(identifier, new ArrayList<JsIdentifier>(), body);
    }

    public JsNamedFunction(JsIdentifier identifier, List<JsIdentifier> parameters, IJsExpression body) {
        this(identifier, parameters, new JsExpressionStatement(body));
    }

    public JsNamedFunction(JsIdentifier identifier, List<JsIdentifier> parameters, IJsStatement body) {
        super(parameters, body);
        this.identifier = identifier;
    }

    public JsIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
