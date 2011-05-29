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
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.DB;
import com.mongodb.Mongo;
import me.moimoi.social.herql.domain.SocialPerson;
import org.apache.shindig.social.opensocial.model.Account;
import org.apache.shindig.social.opensocial.model.Person;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.junit.Test;
//import static org.junit.Assert.*;

/**
 *
 * @author suhail
 */
public class AccountServicesTest {

    private static Mongo mongo;
    private static DB db;
    private static Morphia morphia = new Morphia();
    private static Datastore ds;
    
    public AccountServicesTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        mongo = new Mongo("localhost");
        db = mongo.getDB("social");
        ds = morphia.createDatastore(mongo, db.getName());
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testAndQueryOnAccount() {        
        Query<SocialPerson> q = ds.createQuery(SocialPerson.class).disableValidation();
        
        q.and(
            q.criteria("accounts.userId").equal("email@example.com"),
            q.criteria("accounts.domain").equal("moimoi.com")
        ); 
        SocialPerson person = q.get();
        System.out.println(person.getAccounts());        
        
    }
}
