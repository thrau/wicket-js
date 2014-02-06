package org.rauschig.wicketjs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JsIdentifierTest
 */
public class JsIdentifierTest {

    @Test
    public void equals_sameObject_returnsTrue() throws Exception {
        JsIdentifier i = new JsIdentifier("this");
        assertTrue(i.equals(i));
    }

    @Test
    public void equals_sameIdentifierString_returnsTrue() throws Exception {
        JsIdentifier i = new JsIdentifier("this");
        assertTrue(i.equals("this"));
    }

    @Test
    public void equals_sameIdentifier_returnsTrue() throws Exception {
        JsIdentifier a = new JsIdentifier("this");
        JsIdentifier b = new JsIdentifier("this");

        assertTrue(a.equals(b));
    }

    @Test
    public void equals_differentIdentifier_returnsFalse() throws Exception {
        JsIdentifier a = new JsIdentifier("this");
        JsIdentifier b = new JsIdentifier("that");
        assertFalse(a.equals(b));
    }

    @Test
    public void equals_null_returnsFalse() throws Exception {
        JsIdentifier i = new JsIdentifier("this");
        assertFalse(i.equals(null));
    }
}
