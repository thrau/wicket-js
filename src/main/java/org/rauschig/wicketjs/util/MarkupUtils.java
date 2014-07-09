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
package org.rauschig.wicketjs.util;

import org.apache.wicket.markup.ComponentTag;

/**
 * Markup utilities.
 */
public final class MarkupUtils {
    private MarkupUtils() {
    }

    /**
     * Checks whether the name of given {@code ComponentTag} equals the given name. Does a case insensitive comparison.
     * 
     * @param tag the tag to compare the name from
     * @param name the tag name to compare against
     * @return true if the tag names match
     */
    public static boolean tagEquals(ComponentTag tag, String name) {
        return tag.getName().equalsIgnoreCase(name);
    }

    /**
     * Checks whether the name of the given {@code ComponentTag} equals any of the given names. Does a case insensitive
     * comparison.
     * 
     * @param tag the tag to compare the name from
     * @param names the tag names to compare against
     * @return true if the tag names match
     */
    public static boolean tagEquals(ComponentTag tag, String... names) {
        for (String name : names) {
            if (tagEquals(tag, name)) {
                return true;
            }
        }
        return false;
    }

}
