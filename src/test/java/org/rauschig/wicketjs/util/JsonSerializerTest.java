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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * JsonSerializerTest
 */
public class JsonSerializerTest {

    @Test
    public void serialze_withNullObject_returnsNullAsString() throws Exception {
        JsonSerializer serializer = new JsonSerializer(Mockito.mock(ObjectMapper.class));

        assertEquals("null", serializer.serialize(null));
    }

    @Test
    public void serialze_withNullObject_returnsNullPlaceholder() throws Exception {
        JsonSerializer serializer = new JsonSerializer(Mockito.mock(ObjectMapper.class));

        assertEquals("{}", serializer.serialize(null, "{}"));
    }

    @Test
    public void serializeIntegration_serializesObjectCorrectly() throws Exception {
        Value value = new Value();

        value.setAttr1("answer");
        value.setAttr2(42);

        JsonSerializer serializer = new JsonSerializer();

        assertEquals("{\"attr1\":\"answer\",\"attr2\":42,\"nullattr\":null}", serializer.serialize(value));
    }

    @Test
    public void serializeIntegration_serializesMapCorrectly() throws Exception {
        Map<String, Object> value = new LinkedHashMap<>();

        value.put("attr1", "answer");
        value.put("attr2", 42);
        value.put("nullattr", null);

        JsonSerializer serializer = new JsonSerializer();
        assertEquals("{\"attr1\":\"answer\",\"attr2\":42,\"nullattr\":null}", serializer.serialize(value));
    }

    @Test
    public void serializeIntegration_serializesListCorrectly() throws Exception {
        List<Object> value = new ArrayList<>();

        value.add("answer");
        value.add(42);
        value.add(null);

        JsonSerializer serializer = new JsonSerializer();
        assertEquals("[\"answer\",42,null]", serializer.serialize(value));
    }

    public static class Value {
        private String attr1;
        private int attr2;
        private Object nullattr;

        public String getAttr1() {
            return attr1;
        }

        public void setAttr1(String attr1) {
            this.attr1 = attr1;
        }

        public int getAttr2() {
            return attr2;
        }

        public void setAttr2(int attr2) {
            this.attr2 = attr2;
        }

        public Object getNullattr() {
            return nullattr;
        }

        public void setNullattr(Object nullattr) {
            this.nullattr = nullattr;
        }
    }
}
