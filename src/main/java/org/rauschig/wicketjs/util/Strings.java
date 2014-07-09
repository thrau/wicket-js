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

/**
 * String utility class.
 */
public final class Strings {
    /**
     * The empty string
     */
    public static final String EMPTY_STRING = "";

    private Strings() {
        // static utility class
    }

    /**
     * Joins the given array with the given delimiter. Empty or null arrays will return an empty string.
     * 
     * @param array the array to join
     * @param delimiter the glue
     * @return a string
     */
    public static String join(Object[] array, CharSequence delimiter) {
        if (isEmpty(array)) {
            return EMPTY_STRING;
        }

        int len = array.length;
        StringBuilder str = new StringBuilder(len * 16);

        if (array[0] != null) {
            str.append(array[0]);
        }

        for (int i = 1; i < len; i++) {
            if (array[i] != null) {
                str.append(delimiter);
                str.append(array[i].toString());
            }
        }

        return str.toString();
    }

    private static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

}
