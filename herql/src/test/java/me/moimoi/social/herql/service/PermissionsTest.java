/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.service;

import com.google.inject.Injector;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sma.RedisClient;

/**
 *
 * @author Suhail
 */
public class PermissionsTest {

    private Injector injector;
    private RedisClient client;

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
        //injector = Guice.createInjector(new HerqlModule());
        client = new RedisClient();
        client.selectdb(13);

    }

    @After
    public void tearDown() {
        client.flushdb();
        client.close();
    }

    @Test
    public void testConnection() {
        //PermissionService service = injector.getInstance(PermissionService.class);
        //service.getPermission("suhailski", "ANONYMOUS");
        Assert.assertEquals("PONG", client.ping());
    }

    @Test
    public void testAddPermissionWithSet() {
        client.set("profile.aboutMe.read.janedoe.janedoe", "1");
        client.set("profile.aboutMe.write.janedoe.janedoe", "1");
        client.set("profile.aboutMe.read.janedoe.anonymous", "1");
        client.set("profile.age.read.janedoe.anonymous", "1");
        client.set("profile.age.write.janedoe.anonymous", "0");
        client.set("profile.aboutMe.write.janedoe.anonymous", "0");
        Set<String> result = new HashSet<String>(Arrays.asList(client.keys("profile.*.read.janedoe.anonymous")));
        System.out.println("result " + result);
    }

    @Test
    public void testAddPermissionWithHSet() {        
        client.hset("janedoe.janedoe", "profile.aboutMe", "rw");
    }
}
