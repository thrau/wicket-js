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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.IJsStatement;
import org.rauschig.wicketjs.JsIdentifier;
import org.rauschig.wicketjs.JsLiteral;
import org.rauschig.wicketjs.JsStatement;
import org.rauschig.wicketjs.generator.JsGenerator;

/**
 * Utility class.
 */
public final class JsUtils {

    private JsUtils() {
        // static utility class
    }

    public static List<IJsStatement> asStatementList(IJavaScript... javaScript) {
        if (javaScript == null) {
            return Collections.emptyList();
        }

        ArrayList<IJsStatement> list = new ArrayList<>(javaScript.length);

        for (IJavaScript script : javaScript) {
            list.add(JsStatement.of(script));
        }

        return list;
    }

    /**
     * Wraps each given Strings as a JsIdentifier and aggregates them to a list.
     * 
     * @param identifiers the identifiers
     * @return a list of JsIdentifiers
     */
    public static List<JsIdentifier> asIdentifierList(CharSequence... identifiers) {
        if (identifiers == null) {
            return Collections.emptyList();
        }

        ArrayList<JsIdentifier> list = new ArrayList<>(identifiers.length);

        for (CharSequence identifier : identifiers) {
            list.add(new JsIdentifier(identifier));
        }

        return list;
    }

    /**
     * Casts all given objects to a form s.t. they can be used as an argument list for a JsCall. Expressions are left
     * unchanged, cast and returned; value objects are wrapped using {@link #asLiteral(Object)}.
     * 
     * @param arguments the argument list
     * @return a list of IJsExpression usable as function argument list
     */
    public static List<IJsExpression> asArgumentList(Object... arguments) {
        if (arguments == null) {
            return Collections.emptyList();
        }

        ArrayList<IJsExpression> list = new ArrayList<>(arguments.length);

        for (Object argument : arguments) {
            list.add(asArgument(argument));
        }

        return list;
    }

    /**
     * Casts the given object to a form s.t. it can be used as an argument for a JsCall. Expressions are left unchanged,
     * cast and returned; value objects are wrapped using {@link #asLiteral(Object)}.
     * 
     * @param argument the argument
     * @return a IJsExpression usable as function argument
     */
    public static IJsExpression asArgument(Object argument) {
        if (argument instanceof IJsExpression) {
            return (IJsExpression) argument;
        }

        return asLiteral(argument);
    }

    /**
     * Wraps the given value object into a JsLiteral. If the value is already a JsLiteral instance, it is cast and
     * returned.
     * <p/>
     * Java types are mapped to literal types accordingly. E.g. If you call it using a number, you will get a JsNumber.
     * 
     * @param value the value object
     * @return a JsLiteral representing the given value object
     */
    public static JsLiteral<?> asLiteral(Object value) {
        if (value == null) {
            return JsLiteral.NULL;
        } else if (value instanceof JsLiteral) {
            return (JsLiteral<?>) value;
        } else if (value instanceof Number) {
            return JsLiteral.of((Number) value);
        } else if (value instanceof Boolean) {
            return JsLiteral.of((Boolean) value);
        } else if (value instanceof String) {
            return JsLiteral.of((String) value);
        } else if (value.getClass().isArray()) {
            return JsLiteral.of((Object[]) value);
        } else if (value instanceof Collection) {
            return JsLiteral.of((Collection) value);
        } else if (value instanceof Map) {
            return JsLiteral.of((Map) value);
        } else {
            return JsLiteral.of(value);
        }
    }

    /**
     * Generates a JavaScript source code string from the given IJavaScript tree.
     * 
     * Shorthand for
     * 
     * <pre>
     * new JsGenerator(tree).generate();
     * </pre>
     * 
     * @param tree the JavaScript tree
     * @return the generated JavaScript source
     */
    public static String toString(IJavaScript tree) {
        return new JsGenerator(tree).generate();
    }

    /**
     * Alias for {@code toString(IJavaScript)}.
     * 
     * @see #toString(org.rauschig.wicketjs.IJavaScript)
     */
    public static String js(IJavaScript tree) {
        return toString(tree);
    }

}
