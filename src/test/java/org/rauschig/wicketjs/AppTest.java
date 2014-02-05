package org.rauschig.wicketjs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest  {
    
    private App app;

    @Before
    public void setUp() {
        this.app = new App();
    }

    @After
    public void tearDown() {
        app = null;
    }

    /**
     * Rigourous Test :-)
     */
    @Test
    public void testApp() {
        assertTrue(app.isTrue());
    }

}
