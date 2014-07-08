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
package org.rauschig.wicketjs.util.json;

import java.io.IOException;

import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.compiler.JsCompiler;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Delegates the value serialization of an {@link org.rauschig.wicketjs.IJavaScript} token to
 * {@link org.rauschig.wicketjs.compiler.JsCompiler}.
 */
public class JsTokenSerializer extends StdSerializer<IJavaScript> {
    public JsTokenSerializer() {
        super(IJavaScript.class);
    }

    @Override
    public void serialize(IJavaScript value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeRawValue(new JsCompiler(value).compile());
    }
}
