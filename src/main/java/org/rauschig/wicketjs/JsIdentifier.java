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
 * JsIdentifier
 */
public class JsIdentifier extends AbstractJsExpression {

    private static final long serialVersionUID = 1191736085584765342L;

    private final String identifier;

    public JsIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void accept(IJsExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (obj instanceof String) {
            return identifier.equals(obj);
        } else if (obj instanceof JsIdentifier) {
            return identifier.equals(((JsIdentifier) obj).getIdentifier());
        } else {
            return super.equals(obj);
        }
    }
}
