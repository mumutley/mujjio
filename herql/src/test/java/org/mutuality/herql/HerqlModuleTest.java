/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mutuality.herql;

import com.google.code.morphia.Key;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javax.naming.NamingException;
import junit.framework.TestCase;
import me.moimoi.social.herql.config.HerqlModule;
import me.moimoi.social.herql.domain.SocialAccount;
import me.moimoi.social.herql.domain.SocialPersonImpl;
import me.moimoi.social.herql.services.ProfileService;
import me.moimoi.social.herql.services.SimpleDatasource;
import org.apache.shindig.social.opensocial.model.Person;

/**
 *
 * @author manzoorsam not 
 */
public class HerqlModuleTest extends TestCase {

    private Injector injector;
    
    public HerqlModuleTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        injector = Guice.createInjector(new HerqlModule());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testEb() throws NamingException {
        
    }
    
    public void testSavePersons() throws NamingException {
        SimpleDatasource sd = injector.getInstance(SimpleDatasource.class);
        System.out.println(sd.getDataSource());        
        ProfileService profiles = injector.getInstance(ProfileService.class);
        System.out.println(profiles.getClass());
        
        SocialAccount account = SocialAccount.create("suhail", "suhailski", "moimoi.me");
        account.setPassword("veritas");
        
        Person person = new SocialPersonImpl();
        person.setId(account.getUserId());
        
        Key<Person> key = profiles.register(person); 
        System.out.println(key.getId());
        
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
