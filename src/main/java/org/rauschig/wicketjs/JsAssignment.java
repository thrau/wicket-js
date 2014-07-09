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
 * An assignment expression.
 * <p/>
 * 
 * <pre>
 *     &lt;left&gt; = &lt;right&gt;
 * </pre>
 */
public class JsAssignment extends AbstractJsExpression {

    private static final long serialVersionUID = -3250863627225988870L;

    private IJsExpression left;
    private IJsExpression right;

    public JsAssignment(CharSequence identifier, Object value) {
        this(new JsIdentifier(identifier), JsUtils.asArgument(value));
    }

    public JsAssignment(IJsExpression leftSide, IJsExpression rightSide) {
        this.left = leftSide;
        this.right = rightSide;
    }

    public IJsExpression getLeftSide() {
        return left;
    }

    public IJsExpression getRightSide() {
        return right;
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.accept(this);
    }
}
