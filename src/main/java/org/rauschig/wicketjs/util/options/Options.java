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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.wicket.util.io.IClusterable;
import org.rauschig.wicketjs.JsLiteral;
import org.rauschig.wicketjs.JsLiteral.JsObject;

/**
 * Default map-based IOptions implementation.
 */
public class Options implements IOptions, IClusterable {

    private static final long serialVersionUID = -3243969396060873256L;

    public static final Options EMPTY = new Options(Collections.<String, Object>emptyMap());

    private Map<String, Object> map;

    public Options() {
        this(new LinkedHashMap<String, Object>());
    }

    public Options(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public Options set(String key, Object value) {
        map.put(key, value);
        return this;
    }

    @Override
    public Object get(String key) {
        return map.get(key);
    }

    @Override
    public Options unset(String key) {
        map.remove(key);
        return this;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Collection<String> getKeys() {
        return map.keySet();
    }

    @Override
    public JsObject asObject() {
        return JsLiteral.of(map);
    }

    public Map<String, Object> getMap() {
        return map;
    }

}
