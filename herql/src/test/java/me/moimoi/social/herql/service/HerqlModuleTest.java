/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.service;

import com.google.code.morphia.Key;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.logging.Logger;
import junit.framework.Assert;
import me.moimoi.social.herql.config.HerqlModule;
import me.moimoi.social.herql.config.MutatorModule;
import me.moimoi.social.herql.domain.EnumType;
import me.moimoi.social.herql.domain.SocialAccount;
import me.moimoi.social.herql.services.ProfileService;
import net.guts.common.injection.InjectionListeners;
import net.guts.event.EventModule;
import org.apache.shindig.social.opensocial.model.Account;
import org.apache.shindig.social.opensocial.model.NetworkPresence;
import org.apache.shindig.social.opensocial.model.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.shindig.protocol.model.Enum;
/**
 *
 * @author manzoorsam not 
 */
public class HerqlModuleTest  {

    private Injector injector;
    
    public HerqlModuleTest() {
        
    }

    @Before
    public void setUp() throws Exception {        
        injector = Guice.createInjector(new HerqlModule());
    }

    @After
    public void tearDown() throws Exception {        
    }
    
    @Test
    public void testSaveAccount() {        
        ProfileService profiles = injector.getInstance(ProfileService.class);              
        Account account = SocialAccount.create("suhail", "suhailski", "moimoi.me", "veritas");
        
        Person person =  profiles.create();
        
        person.setId(account.getUserId());
         
        person.getAccounts().add(account);
        person.setIsOwner(Boolean.TRUE);
                
       person.setNetworkPresence(new EnumType<NetworkPresence>(NetworkPresence.XA));
        
        Key<Person> key = profiles.register(person);       
        
        Assert.assertEquals(key.getId(), person.getId());
    }
    
    @Test
    public void testUpdateSimpleAccountProperty() {        
        ProfileService profiles = injector.getInstance(ProfileService.class);   
        
        Person person = profiles.find("suhail");                        
        Assert.assertEquals(person.getId(), "suhail");
        
        person.setAboutMe("Its really good.");        
        person.setAge(61);
        person.setStatus("I have completed a good design for updating the state of objects.");
        profiles.update(person);
                          
        /*Query<SocialPerson> list = sd.getDataSource().find(SocialPerson.class);
        Iterator<SocialPerson> agents = list.fetch().iterator();
        LOG.log(Level.INFO, "{0}", list.fetch().iterator().hasNext());        
        
        while (agents.hasNext()) {
            Person agent = agents.next();
            LOG.info(agent.getId());
        }*/             
    }
    
    @Test
    public void testUpdateUnsetAccount() {        
        ProfileService profiles = injector.getInstance(ProfileService.class);           
        Person person = profiles.find("suhail");                        
        Assert.assertEquals(person.getId(), "suhail");                
        person.setAge(null);
        profiles.update(person);                          
    }
    
    private static final Logger LOG = Logger.getAnonymousLogger();
}
