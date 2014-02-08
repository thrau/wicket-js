package org.rauschig.wicketjs;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

/**
 * JsTemplate.
 */
public class JsTemplate extends JsExpression {

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

    public JsTemplate var(String key, Object variable) {
        return setVariable(key, variable);
    }

    public JsTemplate setVariable(String key, Object variable) {
        variables.put(key, variable);
        return this;
    }

    @Override
    public CharSequence getExpression() {
        return template.asString(variables);
    }
}
