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

    public static String join(Object[] array, String delimiter) {
        int len = array.length;

        if (len < 1) {
            return EMPTY_STRING;
        }

        StringBuilder str = new StringBuilder(len * 16);

        if (array[0] != null) {
            str.append(array[0]);
        }

        for (int i = 1; i < len; i++) {
            if (array[i] != null) {
                str.append(delimiter);
                str.append(array[i]);
            }
        }

        return str.toString();
    }

}
