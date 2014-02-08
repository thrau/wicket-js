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

import java.util.ArrayList;
import java.util.List;

import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.JsIdentifier;
import org.rauschig.wicketjs.JsLiteral;

/**
 * JsExpressionUtils
 */
public final class JsExpressionUtils {

    private JsExpressionUtils() {
        // static utility class
    }

    public static List<JsIdentifier> asIdentifierList(String... identifiers) {
        ArrayList<JsIdentifier> list = new ArrayList<>(identifiers.length);

        for (String identifier : identifiers) {
            list.add(new JsIdentifier(identifier));
        }

        return list;
    }

    public static List<IJsExpression> asArgumentList(Object... arguments) {
        ArrayList<IJsExpression> list = new ArrayList<>(arguments.length);

        for (Object argument : arguments) {
            list.add(asArgument(argument));
        }

        return list;
    }

    public static IJsExpression asArgument(Object argument) {
        if (argument instanceof IJsExpression) {
            return (IJsExpression) argument;
        }

        return asLiteral(argument);
    }

    public static JsLiteral<?> asLiteral(Object value) {
        if (value instanceof Number) {
            return JsLiteral.of((Number) value);
        } else if (value instanceof Boolean) {
            return JsLiteral.of((Boolean) value);
        } else if (value instanceof String) {
            return JsLiteral.of((String) value);
        } else {
            return JsLiteral.of(value);
        }

        // TODO: moar! (maps, lists,...) maybe couple this closer to JsLiteral
    }

}
