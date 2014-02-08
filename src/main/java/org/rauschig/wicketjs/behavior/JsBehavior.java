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
package org.rauschig.wicketjs.behavior;

import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.compiler.JsCompiler;

/**
 * Abstract class that adds custom JavaScript as Behavior to Components.
 */
public abstract class JsBehavior extends AbstractJsBehavior {

    private static final long serialVersionUID = -4036050499449407153L;

    @Override
    protected CharSequence domReadyScript() {
        return new JsCompiler(domReadyJs()).compile();
    }

    /**
     * The JavaScript added as OnDomReadyHeaderItem when the component head is rendered.
     * 
     * @return custom JavaScript
     */
    protected abstract IJavaScript domReadyJs();
}
