/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.moimoi.social.herql.config.HerqlModule;
import me.moimoi.social.herql.services.PermissionService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Suhail
 */
public class PermissionsTest {
    
    private Injector injector;
    
    public PermissionsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        injector = Guice.createInjector(new HerqlModule());
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void testConnection() {
        PermissionService service = injector.getInstance(PermissionService.class);
        service.getPermission("suhailski", "ANONYMOUS");
    }
}
