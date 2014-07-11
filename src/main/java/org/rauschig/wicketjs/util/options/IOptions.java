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

import org.apache.wicket.util.io.IClusterable;
import org.rauschig.wicketjs.JsLiteral.JsObject;

public interface IOptions extends IClusterable {
    /**
     * Sets the given options value.
     * 
     * @param key the key to the value
     * @param value the value to set
     * @return this for chaining
     */
    public IOptions set(String key, Object value);

    /**
     * Returns the value of the given options key.
     * 
     * @param key the key of the option value
     * @return the config value
     */
    public Object get(String key);

    /**
     * Removes the given options key.
     * 
     * @param key the key to remove
     * @return this for chaining
     */
    public IOptions unset(String key);

    /**
     * The amount of values in this set.
     * 
     * @return set size
     */
    public int size();

    /**
     * Returns true if there are no values set.
     * 
     * @return true if no values are set, false otherwise
     */
    public boolean isEmpty();

    /**
     * Returns a listing of all keys in this set.
     * 
     * @return a set of keys
     */
    public Collection<String> getKeys();

    /**
     * Returns this IOptions object as a JsObject literal that can be used for rendering.
     * 
     * @return a JsObject literal
     */
    public JsObject asObject();
}
