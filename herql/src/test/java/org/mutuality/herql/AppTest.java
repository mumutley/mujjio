/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mutuality.herql;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import junit.framework.TestCase;

/**
 *
 * @author manzoors
 */
public class AppTest extends TestCase {

    public AppTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testEmbeddedEjb() throws NamingException {
        if(true) return;
        /*EJBContainer ejbC = EJBContainer.createEJBContainer();
        Context ctx = ejbC.getContext();
        App app = (App) ctx.lookup("java:global/classes/App");
        assertNotNull(app);
        String NAME = "Duke";
        String greeting = app.sayHello(NAME);
        assertNotNull(greeting);
        assertTrue(greeting.equals("Hello " + NAME));
        ejbC.close();*/
    }
}
