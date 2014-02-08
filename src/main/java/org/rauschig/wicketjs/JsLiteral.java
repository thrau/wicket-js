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

/**
 * A value wrapper to map literal semantics of Java to JavaScript.
 * <p/>
 * TODO: array/collection literals TODO: RegularExpression
 */
public abstract class JsLiteral<T> extends AbstractJsExpression {

    private static final long serialVersionUID = -8362618585257944508L;

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
     * Wraps the given Object as a JsObject.
     *
     * @param obj the value to wrap
     * @return the JavaScript value
     */
    public static JsObject of(Object obj) {
        return new JsObject(obj);
    }

}
