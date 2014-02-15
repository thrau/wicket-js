package org.rauschig.wicketjs.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * StringsTest
 */
public class StringsTest {
    @Test
    public void join_withNull_returnsEmptyString() throws Exception {
        assertEquals(Strings.EMPTY_STRING, Strings.join(null, ","));
    }

    @Test
    public void join_withEmptyArray_returnsEmptyString() throws Exception {
        assertEquals(Strings.EMPTY_STRING, Strings.join(new Object[0], ","));
    }

    @Test
    public void join_withEmptyDelimiter_returnsCorrectString() throws Exception {
        assertEquals("123", Strings.join(new Integer[] { 1, 2, 3 }, ""));
    }

    @Test
    public void join_returnsCorrectString() throws Exception {
        assertEquals("1--2--3", Strings.join(new Integer[] { 1, 2, 3 }, "--"));
    }
}
