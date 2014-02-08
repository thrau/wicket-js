package org.rauschig.wicketjs.util;

import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.JsIdentifier;
import org.rauschig.wicketjs.JsLiteral;

import java.util.ArrayList;
import java.util.List;

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
        JsLiteral<?> literal;

        if (value instanceof Number) {
            literal = new JsLiteral.JsNumber((Number) value);
        } else if (value instanceof Boolean) {
            literal = new JsLiteral.JsBoolean((Boolean) value);
        } else if (value instanceof String) {
            literal = new JsLiteral.JsString((String) value);
        } else {
            literal = new JsLiteral.ObjectLiteral(value);
        }

        // TODO: moar! (maps, lists,...) maybe couple this closer to JsLiteral

        return literal;
    }

}
