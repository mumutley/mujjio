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

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import me.moimoi.social.herql.config.HerqlModule;
import org.apache.shindig.auth.AnonymousSecurityToken;
import org.apache.shindig.social.opensocial.spi.AppDataService;
import org.apache.shindig.social.opensocial.spi.PersonService;
import org.apache.shindig.social.opensocial.spi.UserId;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Suhail
 */
public class HerqlOpensocialDataServiceTest {
    
    private Injector injector;
    
    public HerqlOpensocialDataServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        injector = Guice.createInjector(new HerqlModule(), new DataserviceModule());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test //@Ignore("Not Ready to Run") 
    public void getPersonTest() {
        
        PersonService service = injector.getInstance(PersonService.class);
        AppDataService dataService = injector.getInstance(AppDataService.class);        
        Assert.assertNotNull(service);
        Assert.assertNotNull(dataService);
        UserId id = new UserId(UserId.Type.viewer, "suhailski");
        String[] fields = {"id", "age", "name", "gender", "profileUrl", "thumbnailUrl"};
        Set<String> set = new HashSet<String>(Arrays.asList(fields));
        
        service.getPerson(id, set, new AnonymousSecurityToken());
    }
}
