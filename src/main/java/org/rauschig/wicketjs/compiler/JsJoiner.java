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
package org.rauschig.wicketjs.compiler;

import java.util.List;

import org.rauschig.wicketjs.IJavaScript;

/**
 * JsJoiner
 */
public class JsJoiner<T extends IJavaScript> extends AbstractJsCompiler {

    private List<T> visitables;
    private String delimiter;

    public JsJoiner(List<T> visitables, String delimiter) {
        this.visitables = visitables;
        this.delimiter = delimiter;
    }

    @Override
    protected void compileInto(StringBuilder builder) {
        visitAndJoin(delimiter, visitables);
    }
}
