/*
 * Copyright 2011 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.moimoi.social.herql.service;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import me.moimoi.social.herql.domain.SocialPerson;
import org.apache.shindig.protocol.model.EnumImpl;
import org.apache.shindig.social.core.model.BodyTypeImpl;
import org.apache.shindig.social.core.model.UrlImpl;
import org.apache.shindig.social.opensocial.model.BodyType;
import org.apache.shindig.social.opensocial.model.Drinker;
import org.apache.shindig.social.opensocial.model.LookingFor;
import org.apache.shindig.social.opensocial.model.NetworkPresence;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.model.Smoker;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.apache.shindig.protocol.model.Enum;
import org.junit.Test;

/**
 *
 * @author suhail
 */
public class SocialPersonTest {

    private static Mongo mongo;
    private static DB db;
    private static Morphia morphia = new Morphia();
    private static Datastore ds;
    private static SocialPerson person;

    public SocialPersonTest() {
    }

    @BeforeClass
    public static void start() throws UnknownHostException {
        mongo = new Mongo("localhost");
        db = mongo.getDB("social");
        ds = morphia.createDatastore(mongo, db.getName());
        //setup default person        
        person = new SocialPerson();
        person.setId("suhailski@gmail.com");
        person.setAboutMe("I have an example of every piece of data");
        person.setAge(Integer.valueOf("32"));
        Calendar date = Calendar.getInstance();
        date.clear();
        date.set(Calendar.YEAR, 1985);
        date.set(Calendar.MONTH, Calendar.MARCH);
        date.set(Calendar.DAY_OF_MONTH, 22);
        person.setBirthday(date.getTime());

        BodyType df = new BodyTypeImpl();
        df.setBuild("sleek and slim");
        df.setEyeColor("brown");
        df.setHairColor("dark brown");
        df.setHeight(new Float(1.78));
        df.setWeight(new Float(68));
        person.setBodyType(df);

        Enum<NetworkPresence> networkPresence = new EnumImpl<NetworkPresence>(NetworkPresence.XA);
        person.setNetworkPresence(new EnumImpl<NetworkPresence>(NetworkPresence.XA));

        person.setDisplayName("Foo Bar Was Here");

        Enum<Drinker> drinker = new EnumImpl<Drinker>(Drinker.SOCIALLY);
        person.setDrinker(drinker);

        person.setEthnicity("vulcan");
        person.setFashion("like to dress like a pirate");
        person.setGender(Person.Gender.male);
        person.setHappiestWhen("listening to music");
        person.setHumor("you have to love the english veriety");
        person.setLivingArrangement("mostly happily married");

        List<Enum<LookingFor>> lookingFor = new ArrayList();
        Enum<LookingFor> looking = new EnumImpl<LookingFor>(LookingFor.ACTIVITY_PARTNERS);
        lookingFor.add(looking);
        looking = new EnumImpl<LookingFor>(LookingFor.FRIENDS);
        lookingFor.add(looking);
        person.setLookingFor(lookingFor);

        person.setNickname("devil is in the detail");
        person.setPreferredUsername("the one test");
        person.setProfileUrl("http://www.moimoi.me/theonetest");
        person.setProfileSong(new UrlImpl("http://www.moimoi.me/theonetest/albums/01/theone.mp3", "My Song", "MUSIC"));
        person.setProfileVideo(new UrlImpl("http://www.moimoi.me/theonetest/catalog/01/theone.mp4", "My Song", "VIDEO"));
        date = Calendar.getInstance();

        person.setRelationshipStatus("married");
        person.setReligion("budhist");
        person.setSexualOrientation("straight");

        Enum<Smoker> smoker = new EnumImpl<Smoker>(Smoker.SOCIALLY);
        person.setSmoker(smoker);

        person.setStatus("up up and away");
        person.setThumbnailUrl("http://www.moimoi.me/theonetest/catalog/01/theone.mp4");
        person.setUpdated(date.getTime());

        person.getActivities().add("Watching film");
        person.getActivities().add("Reading books");
    }

    @AfterClass
    public static void stop() {
        mongo.close();
    }

    @Before
    public void setUp() {
    }
     
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void addCanonical() {
        ds.delete(ds.createQuery(SocialPerson.class));
        Key<SocialPerson> key = ds.save(person);
        Assert.assertNotNull("there should be a key here", key);
        LOG.log(Level.INFO, "{0} {1}", new Object[]{key.toString(), person.getId()});
    }
    
    private static final Logger LOG = Logger.getAnonymousLogger();
}
