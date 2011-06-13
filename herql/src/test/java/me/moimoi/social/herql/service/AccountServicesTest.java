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
import com.google.code.morphia.query.UpdateOperations;
import com.mongodb.DB;
import com.mongodb.Mongo;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import me.moimoi.social.herql.data.HerqlDAO;
import me.moimoi.social.herql.data.PersonDao;
import me.moimoi.social.herql.domain.SocialPerson;
import org.apache.shindig.protocol.model.EnumImpl;
import org.apache.shindig.social.opensocial.model.LookingFor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.shindig.protocol.model.Enum;
import sun.misc.BASE64Encoder;
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

    public void testAndQueryOnAccount() {
        Query<SocialPerson> q = ds.createQuery(SocialPerson.class).disableValidation();

        q.and(
                q.criteria("accounts.userId").equal("email@example.com"),
                q.criteria("accounts.domain").equal("moimoi.com"));
        SocialPerson person = q.get();
    }

    public void testAddLookingFor() {
        Query<SocialPerson> q = ds.createQuery(SocialPerson.class).disableValidation();
        Query<SocialPerson> updateQuery = ds.createQuery(SocialPerson.class).field("_id").equal("suhailski");
        Enum<LookingFor> looking = new EnumImpl<LookingFor>(LookingFor.RANDOM);
        UpdateOperations<SocialPerson> ops = ds.createUpdateOperations(SocialPerson.class).add("seeking", looking, true);
        ds.update(updateQuery, ops);
    }

    public void testRemoveLookingFor() {
        Query<SocialPerson> q = ds.createQuery(SocialPerson.class).disableValidation();
        Query<SocialPerson> updateQuery = ds.createQuery(SocialPerson.class).field("_id").equal("suhailski");
        Enum<LookingFor> looking = new EnumImpl<LookingFor>(LookingFor.RANDOM);
        UpdateOperations<SocialPerson> ops = ds.createUpdateOperations(SocialPerson.class).removeAll("seeking", looking);
        ds.update(updateQuery, ops);
    }
    
    
    public void testBasicObject() {
        PersonDao dao = new PersonDao(morphia, mongo);
        System.out.println(dao.findOne("_id", "suhail", null));                
    }
    
    public void encryptPassword() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String plaintext = "password";
        
        MessageDigest md = MessageDigest.getInstance("SHA-256"); //step 2
        md.update(plaintext.getBytes("UTF-8")); //step 3

        byte raw[] = md.digest(); //step 4
        String hash = (new BASE64Encoder()).encode(raw); //step 5
        System.out.println("HASH " + hash);
        
        //W6ph5Mm5Pz8GgiULbPgzG37mj9g=
        
        plaintext = "password";
        md.update(plaintext.getBytes("UTF-8")); //step 3

        byte raws[] = md.digest(); //step 4
        String hashs = (new BASE64Encoder()).encode(raw); //step 5
        
        System.out.println("HASH " + hashs);
        System.out.println(MessageDigest.isEqual(raw, raws));
    }
    
    public void foo() {
        /**
         * Class has properties 
         *  - property has a name
         * and class has relationships
         *  - relationship has a name
         * 
         * This is a tree structure (potentially without cycles)
         */
    }
}
