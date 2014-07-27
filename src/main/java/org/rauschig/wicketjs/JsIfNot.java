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
 * Negated JsIf.
 */
public class JsIfNot extends JsIf {

    private static final long serialVersionUID = -9102741603679762525L;

    public JsIfNot(CharSequence expression, IJsStatement thenBlock) {
        super(expression, thenBlock);
        not();
    }

    public JsIfNot(IJsExpression expression, IJavaScript thenBlock) {
        super(expression, thenBlock);
        not();
    }

    public JsIfNot(IJsExpression expression, IJavaScript thenBlock, IJavaScript elseBlock) {
        super(expression, thenBlock, elseBlock);
        not();
    }

    public JsIfNot(IJsExpression expression, IJsStatement thenBlock, IJsStatement elseBlock) {
        super(expression, thenBlock, elseBlock);
        not();
    }
}
