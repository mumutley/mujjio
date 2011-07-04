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
package me.moimoi.social.herqlweb.spi;

import com.google.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import me.moimoi.social.herql.domain.SocialIdentity;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.integration.MessangerService;
import me.moimoi.social.herql.services.ContentServices;
import me.moimoi.social.herql.services.SocialIdentityService;
import me.moimoi.social.herql.services.SocialPersonService;
import me.moimoi.social.herql.services.WorkerPool;
import me.moimoi.social.herqlweb.services.RegistrationService;

/**
 *
 * @author suhail
 */
public class RegistrationServiceImpl implements RegistrationService {
    
    private final SocialIdentityService identityService;
    private final SocialPersonService personService;
    private final MessangerService messanger;
    private final ContentServices content;
    private final WorkerPool pool;
    private static final int FIRST = 0;
    
    @Inject
    public RegistrationServiceImpl(
            SocialIdentityService identityService,
            SocialPersonService personService,
            MessangerService messanger,
            ContentServices content,
            WorkerPool pool) {
        
        this.identityService = identityService;
        this.personService = personService;
        this.messanger = messanger;
        this.content = content;
        this.pool = pool;
    }
    
    @Override
    public void register(SocialIdentity identity) {        
        SocialPerson person = identity.getProfiles().get(RegistrationServiceImpl.FIRST);
        this.personService.register(person);
        this.identityService.create(identity);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name", person.getDisplayName());
        map.put("url", "http://www.google.com");                        
        String message = content.transform("mujjio", "verification", map); 
        messanger.setMsg(message);
        messanger.setRecipient(identity.getLoginName());
        messanger.setSubject(WELCOME + person.getDisplayName());
        messanger.send();
        //pool.submit(messanger);
        
    }
    
    private static final String WELCOME = "Welcome to mujjio ";
    private static final Logger LOG = Logger.getLogger(RegistrationServiceImpl.class.getName());
}
