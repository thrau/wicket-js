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
package org.rauschig.wicketjs.util.options;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * Makes properties of another object accessible as IOptions using PropertyModel instances.
 * 
 * If an object <code>obj</code> has the property <code>someProperty</code>, this can be accessed using the
 * PropertyBindingOptions
 * 
 * <pre>
 * IOptions option = new PropertyBindingOptions(obj, &quot;someProperty&quot;);
 * option.get(&quot;someProperty&quot;); // equal to obj.getSomeProperty();
 * </pre>
 */
public class PropertyBindingOptions extends Options {

    private static final long serialVersionUID = -6699404934910922516L;

    private Object modelObject;

    public PropertyBindingOptions(Object object) {
        super();
        this.modelObject = object;
    }

    /**
     * Creates a new IOptions instance that uses the given properties from the given object.
     * 
     * @param object the object to read the properties from
     * @param properties the property expressions to read
     */
    public PropertyBindingOptions(Object object, String... properties) {
        this(object);

        for (String property : properties) {
            add(property);
        }
    }

    /**
     * Adds a new PropertyModel binding to these options using the key as both options key and property expression (on
     * the object the Options were created with).
     * 
     * @param key the key of the option, used also as property expression
     * @return this for chaining
     */
    public PropertyBindingOptions add(String key) {
        return add(key, new PropertyModel<>(modelObject, key));
    }

    /**
     * * Adds a new PropertyModel binding to these options from the key to the property with the given property
     * expression (on the object the Options were created with).
     * 
     * @param key the key of the option
     * @param propertyExpression the property expression that is used to resolve the value of the property
     * @return this for chaining
     */
    public PropertyBindingOptions add(String key, String propertyExpression) {
        return add(key, new PropertyModel<>(modelObject, propertyExpression));
    }

    /**
     * Adds a new mapping to these options from the key to the given model.
     * 
     * @param key the key of the option
     * @param model the model holding the option value
     * @param <T> generic model type
     * @return this for chaining
     */
    public <T> PropertyBindingOptions add(String key, IModel<T> model) {
        getMap().put(key, model);
        return this;
    }

    @Override
    public PropertyBindingOptions set(String key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object get(String key) {
        Object o = getMap().get(key);

        if (o == null) {
            return null;
        } else if (o instanceof IModel) {
            return ((IModel) o).getObject();
        } else {
            throw new IllegalStateException("Content of options map is not an IModel");
        }

    }

}
