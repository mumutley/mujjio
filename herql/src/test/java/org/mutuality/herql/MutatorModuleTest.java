/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mutuality.herql;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.moimoi.social.herql.config.HerqlModule;
import me.moimoi.social.herql.config.MutatorModule;
import net.guts.common.injection.InjectionListeners;
import net.guts.event.EventModule;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Suhail
 */
public class MutatorModuleTest {

    private Injector injector;

    public MutatorModuleTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        injector = Guice.createInjector(new EventModule(), new MutatorModule(), new HerqlModule());
        InjectionListeners.injectListeners(injector);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testInitialization() {
        //Consumer conusmer = injector.getInstance(Consumer.class);
        //Supplier supplier = injector.getInstance(Supplier.class);
        
        
        
    }
}
