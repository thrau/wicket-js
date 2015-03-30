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

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

/**
 * Loads the contents of a {@code TextTemplate} as a JsExpression.
 * 
 * TODO: split expression/statement template
 */
public class JsTemplate extends JsExpression {

    private static final long serialVersionUID = -3406739800398789055L;

    private TextTemplate template;

    private Map<String, Object> variables;

    public JsTemplate(String file, Object scope) {
        this(file, scope.getClass());
    }

    public JsTemplate(String file, Class<?> clazz) {
        this(new PackageTextTemplate(clazz, file));
    }

    public JsTemplate(TextTemplate template) {
        this(template, new HashMap<String, Object>());
    }

    public JsTemplate(TextTemplate template, Map<String, Object> variables) {
        super("");

        this.template = template;
        this.variables = variables;
    }

    /**
     * Alias for {@link #setVariable(String, Object)}
     * 
     * @param key the variable name
     * @param variable the variable value
     * @return this for chaining
     * @see #setVariable(String, Object)
     */
    public JsTemplate var(String key, Object variable) {
        return setVariable(key, variable);
    }

    /**
     * Sets the value of a variable that is used to interpolate the template when loading it.
     * 
     * @param key the variable name
     * @param value the variable value
     * @return this for chaining
     */
    public JsTemplate setVariable(String key, Object value) {
        variables.put(key, value);
        return this;
    }

    @Override
    public CharSequence getExpression() {
        return template.asString(variables);
    }
}
