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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.model.PropertyModel;
import org.rauschig.wicketjs.JsLiteral.JsObject;

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
public class PropertyBindingOptions implements IOptions {

    private static final long serialVersionUID = -6699404934910922516L;

    private Map<String, PropertyModel<Object>> propertyMap = new HashMap<>();

    /**
     * Creates a new IOptions instance that uses the given properties from the given object.
     * 
     * @param object the object to read the properties from
     * @param properties the property expressions to read
     */
    public PropertyBindingOptions(Object object, String... properties) {
        for (String property : properties) {
            propertyMap.put(property, new PropertyModel<>(object, property));
        }
    }

    @Override
    public PropertyBindingOptions set(String key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object get(String key) {
        PropertyModel<Object> property = propertyMap.get(key);

        return (property != null) ? property.getObject() : null;
    }

    @Override
    public PropertyBindingOptions unset(String key) {
        propertyMap.remove(key);
        return this;
    }

    @Override
    public int size() {
        return propertyMap.size();
    }

    @Override
    public boolean isEmpty() {
        return propertyMap.isEmpty();
    }

    @Override
    public Collection<String> getKeys() {
        return propertyMap.keySet();
    }

    @Override
    public JsObject asObject() {
        return new JsObject(propertyMap);
    }

}
