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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class PropertyBindingOptionsTest {

    private TestModel model;

    @Before
    public void setUp() throws Exception {
        model = new TestModel("foo", true);
    }

    @Test
    public void get_returnsValuesFromObject() throws Exception {
        PropertyBindingOptions options = new PropertyBindingOptions(model, "theory", "logic");
        assertEquals("foo", options.get("theory"));
        assertEquals(true, options.get("logic"));

        model.setLogic(false);
        assertEquals("foo", options.get("theory"));
        assertEquals(false, options.get("logic"));
    }

    @Test
    public void get_existingButUnboundProperty_returnsNull() throws Exception {
        PropertyBindingOptions options = new PropertyBindingOptions(model, "logic");

        assertNull(options.get("theory"));
        assertEquals(true, options.get("logic"));
    }

    @Test
    public void get_nonExistingButBoundProperty_returnsNull() throws Exception {
        PropertyBindingOptions options = new PropertyBindingOptions(model, "theory", "logic");

        assertNull(options.get("propertyDoesNotExist"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void set_throwsUnsupportedOperationException() throws Exception {
        PropertyBindingOptions options = new PropertyBindingOptions(model, "theory", "logic");

        options.set("theory", "logic");
    }

    private static class TestModel {
        private String theory;
        private Boolean logic;

        public TestModel(String theory, Boolean logic) {
            this.theory = theory;
            this.logic = logic;
        }

        public String getTheory() {
            return theory;
        }

        public void setTheory(String theory) {
            this.theory = theory;
        }

        public Boolean getLogic() {
            return logic;
        }

        public void setLogic(Boolean logic) {
            this.logic = logic;
        }
    }
}
