/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.service;

import com.google.code.morphia.Key;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import me.moimoi.social.herql.domain.EnumType;
import me.moimoi.social.herql.domain.ListFieldType;
import me.moimoi.social.herql.domain.SocialAccount;
import me.moimoi.social.herql.services.ProfileService;
import org.apache.shindig.social.core.model.ListFieldImpl;
import org.apache.shindig.social.core.model.UrlImpl;
import org.apache.shindig.social.opensocial.model.Account;
import org.apache.shindig.social.opensocial.model.ListField;
import org.apache.shindig.social.opensocial.model.NetworkPresence;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.model.Url;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
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
        injector = Guice.createInjector(new HerqlModule(), new DataserviceModule());
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
        
        ListField email = new ListFieldImpl();
        email.setType(ListFieldType.home.name());
        email.setPrimary(Boolean.TRUE);
        email.setValue("suhailski@gmail.com");
        person.getEmails().add(email);
        
        Url url = new UrlImpl();
        url.setLinkText("Standard Model");
        url.setValue("http://goo.gl/Ed27t");
        person.getUrls().add(url);
                
        Key<Person> key = profiles.save(person);       
        
        Assert.assertEquals(key.getId(), person.getId());
    }
    
    @Test @Ignore
    public void testUpdateSimpleAccountProperty() {        
        ProfileService profiles = injector.getInstance(ProfileService.class);   
        
        Person person = profiles.find("suhail");                        
        Assert.assertEquals(person.getId(), "suhail");
        
        person.setAboutMe("Its really good.");        
        person.setAge(61);
        person.setStatus("I have completed a good design for updating the state of objects.");
        profiles.update(person);                                         
    }
        
    @Test
    public void testUpdatePresence() { 
        ProfileService profiles = injector.getInstance(ProfileService.class);           
        Person person = profiles.find("suhail");                        
        person.setNetworkPresence(new EnumType<NetworkPresence>(NetworkPresence.OFFLINE));
        profiles.update(person);
    }
    
    @Test
    public void testUpdateUnset() {        
        ProfileService profiles = injector.getInstance(ProfileService.class);           
        Person person = profiles.find("suhail");                        
        Assert.assertEquals(person.getId(), "suhail"); 
        LOG.log(Level.INFO, " network presence {0}", person.getNetworkPresence().getDisplayValue());
        person.setAge(null);        
        profiles.update(person);                          
    }
    
    @Test
    public void testFetchAccountAndUpdate() {
        ProfileService profiles = injector.getInstance(ProfileService.class);           
        Person person = profiles.find("suhail", Account.class);
        
        LOG.log(Level.INFO, "email {0}", person.getEmails().get(0).getValue());
        LOG.log(Level.INFO, "urls {0}", person.getUrls().get(0).getValue());
        
        Account account = person.getAccounts().get(0);
        account.setUserId("biscuit");        
        profiles.save(person);
    }
    
    private static final Logger LOG = Logger.getAnonymousLogger();
}
