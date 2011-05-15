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
package me.moimoi.social.herqlweb;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.Mongo;
import me.moimoi.social.herql.domain.SocialPerson;
import org.apache.shindig.social.core.model.NameImpl;
import org.apache.shindig.social.opensocial.model.Name;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.model.Person.Gender;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Suhail
 */
public class DomainTests {

    private static Mongo mongo;
    private static DB db;
    private static Morphia morphia = new Morphia();
    private static Datastore ds;
    private static SocialPerson person;

    public DomainTests() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        mongo = new Mongo("localhost");
        db = mongo.getDB("social");
        ds = morphia.createDatastore(mongo, db.getName());
        //setup default person        
        person = new SocialPerson();
        
        person.setAboutMe("I am just a man");
        person.setId("suhailshi");
        person.setAge(43);
        
        Name name = new NameImpl();
        name.setGivenName("Suhail");
        name.setFamilyName("Manzoor");
        name.setFormatted("Suhail Manzoor");
        person.setName(name);
        
        person.setGender(Gender.male);
        person.setProfileUrl("/suhailski");
        person.setThumbnailUrl("/albums/perofile.jpg");                       
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addPerson() {
        Key<SocialPerson> key = ds.save(person);
    }
}
