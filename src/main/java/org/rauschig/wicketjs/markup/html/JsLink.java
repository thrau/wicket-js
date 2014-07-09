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
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.behavior.JQueryEventBehavior;
import org.rauschig.wicketjs.behavior.JsBehavior;
import org.rauschig.wicketjs.util.MarkupUtils;

/**
 * A component that allows to execute a custom defined JavaScript callback via html anchor tag.
 */
public abstract class JsLink extends AbstractLink {

    private static final long serialVersionUID = 1L;

    public JsLink(String id) {
        super(id);
    }

    public JsLink(String id, IModel<?> model) {
        super(id, model);
    }

    /**
     * Returns the callback that is executed when the user clicks the link.
     * 
     * @return java script
     */
    public abstract IJavaScript onClick();

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(newJsBehavior("click"));
    }

    /**
     * Creates the JsBehavior that is added to this component with the given event.
     * 
     * @param event the dom event that the behavior is triggered on ("click" by default)
     * @return a new JsBehavior
     */
    protected JsBehavior newJsBehavior(String event) {
        return new JQueryEventBehavior(event) {
            private static final long serialVersionUID = 1L;

            @Override
            protected IJavaScript callback() {
                return JsLink.this.onClick();
            }

        };
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
