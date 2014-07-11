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

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.junit.Test;
import org.mockito.Mockito;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.JsLiteral;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonSerializerTest.
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

    @Test
    public void serializeIntegration_serializeJsTokenCorrectly() throws Exception {
        JsCall call = new JsCall("getAnswer", "to life the universe and everything");
        assertEquals("getAnswer('to life the universe and everything')", new JsonSerializer().serialize(call));
    }

    @Test
    public void serializeIntegration_serializeNestedMapsWithJsTokenCorrectly() throws Exception {
        Map<String, Object> map = new LinkedHashMap<>();
        Map<String, Object> nestedMap = new LinkedHashMap<>();

        map.put("answer", 42);
        map.put("fun", new JsCall("getAnswer", "to life the universe and everything"));
        map.put("nested", nestedMap);

        nestedMap.put("literal", new JsLiteral.JsString("foo"));

        JsonSerializer serializer = new JsonSerializer();

        assertEquals(
                "{\"answer\":42,\"fun\":getAnswer('to life the universe and everything'),\"nested\":{\"literal\":\"foo\"}}",
                serializer.serialize(new JsLiteral.JsObject(map)));
    }

    @Test
    public void serializeIntegration_serializeIModelsCorrectly() throws Exception {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("question", "life the universe and everything");
        map.put("answer", 42);

        IModel<LinkedHashMap<String, Object>> model = Model.of(map);

        JsonSerializer serializer = new JsonSerializer();

        assertEquals("{\"question\":\"life the universe and everything\",\"answer\":42}", serializer.serialize(model));
    }

    @Test
    public void serializeIntegration_serializeNestedPropertyModelsCorrectly() throws Exception {
        Value v = new Value();

        v.setAttr1("life the universe and everything");
        v.setAttr2(42);

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("question", new PropertyModel<>(v, "attr1"));
        map.put("answer", new PropertyModel<>(v, "attr2"));

        IModel<LinkedHashMap<String, Object>> model = Model.of(map);

        JsonSerializer serializer = new JsonSerializer();

        assertEquals("{\"question\":\"life the universe and everything\",\"answer\":42}", serializer.serialize(model));
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
