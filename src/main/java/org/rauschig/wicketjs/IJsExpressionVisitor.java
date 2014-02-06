package org.rauschig.wicketjs;

/**
 * A visitor of the IJsExpression hierarchy.
 */
public interface IJsExpressionVisitor {

    void visit(JsLiteral.JsNumber visitable);

    void visit(JsLiteral.JsBoolean visitable);

    void visit(JsLiteral.JsString visitable);

    void visit(JsLiteral.ObjectLiteral visitable);

    void visit(JsIdentifier visitable);

    void visit(JsExpression visitable);

    void visit(JsCall visitable);

    void visit(JsFunction visitable);

    void visit(JsNamedFunction visitable);
}
