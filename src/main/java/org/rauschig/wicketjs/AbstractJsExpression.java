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

import org.rauschig.wicketjs.generator.JsGenerator;

/**
 * AbstractJsExpression
 */
public abstract class AbstractJsExpression implements IJsExpression {

    private static final long serialVersionUID = 3563470453408826790L;

    @Override
    public JsExpressionStatement terminate() {
        return JsStatement.of(this);
    }

    /**
     * Returns the compiled JQuery expression.
     * 
     * @return the JQuery expression as plain java script
     */
    public String js() {
        return new JsGenerator(this).generate();
    }

}
