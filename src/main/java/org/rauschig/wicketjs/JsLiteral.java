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
package org.rauschig.wicketjs;

import java.util.Collection;
import java.util.Map;

/**
 * A value wrapper to map literal semantics of Java to JavaScript.
 *
 * TODO: RegularExpression
 */
public abstract class JsLiteral<T> extends AbstractJsExpression {

    private static final long serialVersionUID = -8362618585257944508L;

    public static final JsNull NULL = JsNull.INSTANCE;

    private T value;

    /**
     * Creates a new JavaScript literal for the given value.
     * 
     * @param value the Java value to be wrapped.
     */
    public JsLiteral(T value) {
        setValue(value);
    }

    /**
     * Returns the Java value object.
     * 
     * @return the Java value object
     */
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof JsLiteral)) {
            return value != null && value.equals(o);
        } else if (!getClass().equals(o.getClass())) {
            System.out.println("not the same class");
            return false;
        }

        JsLiteral other = (JsLiteral) o;
        if (value == null) {
            return other.value == null;
        } else {
            return value.equals(other.value);
        }
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value == null ? null : value.toString();
    }

    /**
     * A JavaScript String.
     */
    public static class JsString extends JsLiteral<String> {
        private static final long serialVersionUID = -8368594256531244265L;

        public JsString(String value) {
            super(value);
        }

        @Override
        public void accept(IJsExpressionVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * A JavaScript Boolean.
     */
    public static class JsBoolean extends JsLiteral<Boolean> {
        private static final long serialVersionUID = -2484075331556045893L;

        public static final JsBoolean FALSE = new JsBoolean(false);
        public static final JsBoolean TRUE = new JsBoolean(true);

        public JsBoolean(Boolean value) {
            super(value);
        }

        @Override
        public void accept(IJsExpressionVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * A JavaScript Number.
     */
    public static class JsNumber extends JsLiteral<Number> {
        private static final long serialVersionUID = 8998703899729389745L;

        public JsNumber(Number value) {
            super(value);
        }

        @Override
        public void accept(IJsExpressionVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * A JavaScript array.
     */
    public static class JsArray extends JsLiteral<Object[]> {
        private static final long serialVersionUID = -8615815427231961440L;

        public JsArray(Object[] value) {
            super(value);
        }

        public JsArray(Collection<?> value) {
            super(value.toArray());
        }

        @Override
        public void accept(IJsExpressionVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * A JavaScript object.
     */
    public static class JsObject extends JsLiteral<Object> {
        private static final long serialVersionUID = -1093930669617979141L;

        public JsObject(Object value) {
            super(value);
        }

        @Override
        public void accept(IJsExpressionVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * The null literal.
     */
    public static class JsNull extends JsObject {

        private static final long serialVersionUID = 383649185321212948L;

        public static final JsNull INSTANCE = new JsNull();

        public JsNull() {
            super(null);
        }

        @Override
        public void accept(IJsExpressionVisitor visitor) {
            super.accept(visitor);
        }
    }

    /**
     * Wraps the given Boolean as a JsBoolean.
     * 
     * @param bool the value to wrap
     * @return the JavaScript value
     */
    public static JsBoolean of(Boolean bool) {
        return (bool) ? JsBoolean.TRUE : JsBoolean.FALSE;
    }

    /**
     * Wraps the given Number as a JsNumber.
     * 
     * @param number the value to wrap
     * @return the JavaScript value
     */
    public static JsNumber of(Number number) {
        return new JsNumber(number);
    }

    /**
     * Wraps the given String as a JsString.
     * 
     * @param string the value to wrap
     * @return the JavaScript value
     */
    public static JsString of(String string) {
        return new JsString(string);
    }

    /**
     * Wraps the given Array as a JsArray.
     * 
     * @param array the value to wrap
     * @return the JavaScript value
     */
    public static JsArray of(Object[] array) {
        return new JsArray(array);
    }

    /**
     * Wraps the given Collection as a JsArray.
     * 
     * @param array the value to wrap
     * @return the JavaScript value
     */
    public static JsArray of(Collection<?> array) {
        return new JsArray(array);
    }

    /**
     * Wraps the given Map as a JsObject.
     * 
     * @param map the value to wrap
     * @return the JavaScript value
     */
    public static JsObject of(Map<?, ?> map) {
        return new JsObject(map);
    }

    /**
     * Wraps the given Object as a JsObject.
     * 
     * @param obj the value to wrap
     * @return the JavaScript value
     */
    public static JsObject of(Object obj) {
        return new JsObject(obj);
    }

}
