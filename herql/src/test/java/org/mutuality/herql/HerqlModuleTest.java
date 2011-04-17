/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mutuality.herql;

import com.google.code.morphia.Key;
import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import me.moimoi.social.herql.config.HerqlModule;
import me.moimoi.social.herql.config.MutatorModule;
import me.moimoi.social.herql.domain.SocialAccount;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.ProfileService;
import me.moimoi.social.herql.services.SimpleDatasource;
import net.guts.common.injection.InjectionListeners;
import net.guts.event.EventModule;
import org.apache.shindig.social.opensocial.model.Account;
import org.apache.shindig.social.opensocial.model.Person;
import org.junit.After;
import org.junit.Before;
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
        injector = Guice.createInjector(new EventModule(), new MutatorModule(), new HerqlModule());
        InjectionListeners.injectListeners(injector);
    }

    @After
    public void tearDown() throws Exception {        
    }
    
    @Test
    public void testSaveAccount() {
        SimpleDatasource sd = injector.getInstance(SimpleDatasource.class);                       
        ProfileService profiles = injector.getInstance(ProfileService.class);              
        Account account = SocialAccount.create("suhail", "suhailski", "moimoi.me", "veritas");
        
        Person person =  profiles.create();
        
        person.setId(account.getUserId());
        
        person.getAccounts().add(account);
        person.setIsOwner(Boolean.TRUE);        
        Key<Person> key = profiles.register(person);       
        
        Assert.assertEquals(key.getId(), person.getId());
    }
    
    @Test
    public void testUpdateAccount() {
        SimpleDatasource sd = injector.getInstance(SimpleDatasource.class);               
        
        Person person = sd.getDataSource().get(SocialPerson.class, "suhail");
        Assert.assertEquals(person.getId(), "suhail");
        person.setAboutMe("Its really good.");        
        
        UpdateOperations<SocialPerson> ops = sd.getDataSource().createUpdateOperations(SocialPerson.class).set("age", 61);
        ops.set("aboutMe", "I love cats");
        
        Query<SocialPerson> query = sd.getDataSource().find(SocialPerson.class).field(Mapper.ID_KEY).equal("suhail");        
        sd.getDataSource().update(query, ops);        
        
        Query<SocialPerson> list = sd.getDataSource().find(SocialPerson.class);
        Iterator<SocialPerson> agents = list.fetch().iterator();
        LOG.log(Level.INFO, "{0}", list.fetch().iterator().hasNext());        
        
        while (agents.hasNext()) {
            Person agent = agents.next();
            LOG.info(agent.getId());
        }                
    }
    
    private static final Logger LOG = Logger.getAnonymousLogger();
}
