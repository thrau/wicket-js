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
package org.rauschig.wicketjs.util;

import org.rauschig.wicketjs.util.json.JavaScriptModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Serializes objects into JSON Strings and treats IJavaScript tokens as raw (unquoted) values.
 */
public class JsonSerializer {

    private static final Logger LOG = LoggerFactory.getLogger(JsonSerializer.class);

    private ObjectMapper mapper;

    public JsonSerializer() {
        this(new ObjectMapper().registerModule(new JavaScriptModule()));
    }

    protected JsonSerializer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    protected ObjectMapper getMapper() {
        return mapper;
    }

    public String serialize(Object value) {
        return serialize(value, "null");
    }

    public String serialize(Object value, String nullPlaceholder) {
        if (value == null) {
            return nullPlaceholder;
        }

        try {
            return getMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            LOG.error("Could not serialize {}. Returning empty String.", value, e);
            return "";
        }
    }

}
