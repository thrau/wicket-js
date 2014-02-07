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
