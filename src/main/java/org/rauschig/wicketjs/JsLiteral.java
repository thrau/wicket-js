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
 * JsLiteral
 */
public abstract class JsLiteral<T> extends AbstractJsExpression {
    private T value;

    public JsLiteral(T value) {
        setValue(value);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public static class JsString extends JsLiteral<String> {

        public JsString(String value) {
            super(value);
        }

        @Override
        public void accept(IJsExpressionVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class JsBoolean extends JsLiteral<Boolean> {

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

    public static class JsNumber extends JsLiteral<Number> {

        public JsNumber(Number value) {
            super(value);
        }

        @Override
        public void accept(IJsExpressionVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class ObjectLiteral extends JsLiteral<Object> {

        public ObjectLiteral(Object value) {
            super(value);
        }

        @Override
        public void accept(IJsExpressionVisitor visitor) {
            visitor.visit(this);
        }
    }

}
