/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.domain;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.mongodb.DB;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.apache.shindig.social.opensocial.model.Person;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ManzoorS
 */
public class DomainTests {

    private static Mongo mongo;
    private static DB db;
    private static Morphia morphia = new Morphia();
    private static Datastore ds;
    private static Agent person;

    public DomainTests() {
    }

    @BeforeClass
    public static void start() throws UnknownHostException {
        mongo = new Mongo("localhost");
        db = mongo.getDB("social");
        ds = morphia.createDatastore(mongo, db.getName());
        //setup default person        
        person = Agent.create();
        person.setAboutMe("I have an example of every piece of data");
        person.setAge(Integer.valueOf("32"));
        Calendar date = Calendar.getInstance();
        date.clear();
        date.set(Calendar.YEAR, 1985);
        date.set(Calendar.MONTH, Calendar.MARCH);
        date.set(Calendar.DAY_OF_MONTH, 22);
        person.setAnniversary(date.getTime());
        person.setBirthday(date.getTime());
        person.setBodyType("nice and slim");
        person.setConnected(Boolean.TRUE);
        person.setDisplayName("Foo Bar Was Here");
        person.setDrinker("now and again");
        person.setEthnicity("vulcan");
        person.setFashion("like to dress like a pirate");
        person.setGender(Person.Gender.male.name());
        person.setHappiestWhen("listening to music");
        person.setHumor("you have to love the english veriety");
        person.setLivingArrangement("mostly happily married");
        person.setLocation("London, United Kingdom");
        person.setLookingFor("a good time");
        person.setNickname("devil is in the detail");
        person.setNote("don't forget this");
        person.setPreferredUsername("the one test");
        person.setProfileUrl("http://www.moimoi.me/theonetest");
        person.setProfileSong("http://www.moimoi.me/theonetest/albums/01/theone.mp3");
        person.setProfileVideo("http://www.moimoi.me/theonetest/catalog/01/theone.mp4");
        date = Calendar.getInstance();
        person.setPublished(date.getTime());
        person.setRelationshipStatus("married");
        person.setReligion("budhist");
        person.setSexualOrientation("straight");
        person.setSmoker("now and again");
        person.setStatus("up up and away");
        person.setThumbnailUrl("http://www.moimoi.me/theonetest/catalog/01/theone.mp4");
        person.setUpdated(date.getTime()); 
        
        PluralField<String> activity = new PluralField("Watching film", Type.other.name(), Boolean.TRUE);
        person.getActivities().add(activity);
        activity = new PluralField("Reading books", Type.other.name(), Boolean.TRUE);
        person.getActivities().add(activity);
    }

    @AfterClass
    public static void stop() {
        mongo.close();
    }

    @Test
    public void addAgent() {
        ds.delete(ds.createQuery(Agent.class));
        
        //adding two agent instances
        Account account = Account.create("suhailski", "Suhail M", "moimoi.me");
        
        person.getAccounts().add(account);
        
        Key<Agent> key = ds.save(person);
        Assert.assertNotNull("there should be a key here", key);
        LOG.log(Level.INFO, "{0} {1}", new Object[]{key.toString(), person.getId().toString()});

        account = Account.create("suhail", "Suhail Manzoor", "moimoi.me");

        person.setAge(Integer.valueOf("53"));
        person.setAboutMe("This is not baout me");
        person.setId(null);
        person.getAccounts().clear();
        person.getAccounts().add(account);
        
        key = ds.save(person);
        Assert.assertNotNull("there should be a key here", key);

        LOG.log(Level.INFO, "{0} {1}", new Object[]{key.toString(), person.getId().toString()});
    }

    @Test
    public void getAgents() {
        Query<Agent> query = ds.find(Agent.class);
        Iterator<Agent> agents = query.fetch().iterator();
        while (agents.hasNext()) {
            Agent agent = agents.next();
            LOG.info(agent.getAboutMe());
        }
    }
    
    @Test
    public void getAgentByKey() {
        Agent agent = ds.find(Agent.class).field("aboutMe").equal("This is not baout me").get();
        Assert.assertNotNull("there should be a key here", agent);
        Assert.assertEquals("the age should be equal", agent.getAge(), Integer.valueOf("53"));
        LOG.log(Level.INFO, "gender is {0}", agent.getGender());
    }
    
    @Test
    public void findAndUpdateAgent() {       
        Query<Agent> query = ds.find(Agent.class).field("aboutMe").equal("This is not baout me");
        UpdateOperations<Agent> ops = ds.createUpdateOperations(Agent.class).set("age", 61);
        ds.update(query, ops);        
    } 
    
    @Test
    public void getAgentAfterUpdate() {
        Agent agent = ds.find(Agent.class).field("aboutMe").equal("This is not baout me").get();
        Assert.assertNotNull("there should be a key here", agent);
        Assert.assertEquals("the age should be equal", agent.getAge(), Integer.valueOf("61"));
        LOG.log(Level.INFO, "gender is {0}", agent.getAge());
    }
    
    @Test
    public void getEmbeddedAccount() {        
        Agent agent = ds.find(Agent.class, "accounts.userId", "suhail").get();
        Assert.assertNotNull("there should be an account here", agent);
        LOG.log(Level.INFO, "age is {0}", agent.getAge());        
    }
    
    //@Test
    public void removeAccountAndUpdateAgent() {
        Agent agent = ds.find(Agent.class, "accounts.userId", "suhail").get();               
        final boolean removed = agent
                .getAccounts()
                .remove(Account.create("suhail", "Suhail Manzoor", "moimoi.me"));
        
        if(removed) {
            ds.save(agent);
            LOG.log(Level.INFO, "{0}", "Modified account saved " + agent.getAccounts());
        } else {
            LOG.log(Level.INFO, "{0}", "Account not saved");
        }        
    }
    
    @Test
    public void updateAccountField() {
        Agent agent = ds.find(Agent.class, "accounts.userId", "suhail").get();    
        Iterator<Account> ita = agent.getAccounts().iterator();
        while(ita.hasNext()) {
            Account account = ita.next();
            if(account.getUserId().equals("suhail")) {
                account.setUserId("rubicon");
                break;                                
            }
        }
        ds.save(agent);        
    }
    
    private static final Logger LOG = Logger.getAnonymousLogger();
}
