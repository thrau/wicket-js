package org.rauschig.wicketjs.behavior;

import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.compiler.JsExpressionCompiler;

/**
 * JsBehavior.
 */
public abstract class JsBehavior extends AbstractJsBehavior {
    @Override
    protected CharSequence domReadyScript() {
        return new JsExpressionCompiler(domReadyJs()).compile();
    }

    protected abstract IJsExpression domReadyJs();
}
