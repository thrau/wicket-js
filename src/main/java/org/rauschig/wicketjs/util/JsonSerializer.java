package org.rauschig.wicketjs.util;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Serializes objects into JSON Strings.
 */
public class JsonSerializer {

    private ObjectMapper mapper;

    public JsonSerializer() {
        this(new ObjectMapper());
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
        JsonNode node = valueToTree(value);

        return (node == null) ? nullPlaceholder : node.toString();
    }

    public JsonNode valueToTree(Object value) {
        return getMapper().valueToTree(value);
    }

}
