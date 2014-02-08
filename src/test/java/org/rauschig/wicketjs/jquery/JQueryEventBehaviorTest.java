package org.rauschig.wicketjs.jquery;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.apache.wicket.Component;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.rauschig.wicketjs.IJsExpression;
import org.rauschig.wicketjs.behavior.AbstractJsBehavior;

/**
 * JQueryEventBehaviorTest
 */
public class JQueryEventBehaviorTest {

    Component component;

    @Before
    public void setUp() throws Exception {
        component = Mockito.mock(Component.class);
        Mockito.when(component.getMarkupId()).thenReturn("id");
    }

    @After
    public void tearDown() throws Exception {
        component = null;
    }

    @Test
    public void behavior_withoutAdditionalSelector_returnsCorrectJavaScript() throws Exception {
        JQueryEventBehavior behavior = new JQueryEventBehavior("click") {
            @Override
            protected IJsExpression callback() {
                return JQuery.$(this).toggleClass("test");
            }
        };

        behavior.bind(component);

        CharSequence js = getJavaScript(behavior);

        assertEquals("$('#id').bind('click',function(eventObject){$('#id').toggleClass('test')})", js);
    }

    @Test
    public void behavior_withAdditionalSelector_returnsCorrectJavaScript() throws Exception {
        JQueryEventBehavior behavior = new JQueryEventBehavior("click", "button") {
            @Override
            protected IJsExpression callback() {
                return JQuery.$(this).toggleClass("test");
            }
        };

        behavior.bind(component);

        CharSequence js = getJavaScript(behavior);

        assertEquals("$('#id').find('button').bind('click',function(eventObject){$('#id').toggleClass('test')})", js);
    }

    private CharSequence getJavaScript(JQueryEventBehavior behavior) throws Exception {
        Method method = AbstractJsBehavior.class.getDeclaredMethod("domReadyScript");
        method.setAccessible(true);
        CharSequence js = (CharSequence) method.invoke(behavior);
        method.setAccessible(false);
        return js;
    }
}
