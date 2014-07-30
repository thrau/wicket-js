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
package org.rauschig.wicketjs.markup.html;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.rauschig.wicketjs.util.MarkupUtils;

/**
 * AbstractLink that executes some client side functionality, or is a placeholder for something of the sort. It simply
 * replaces the <code>href</code> attribute of a link with an empty javascript statement.
 */
public abstract class AbstractJsLink extends AbstractLink {

    private static final long serialVersionUID = 1L;

    public AbstractJsLink(String id) {
        super(id);
    }

    public AbstractJsLink(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        if (isLinkEnabled()) {
            // disable any href attr in markup
            if (MarkupUtils.tagEquals(tag, "a", "link", "area")) {
                tag.put("href", "javascript:;");
            }
        } else {
            disableLink(tag);
        }
    }

}
