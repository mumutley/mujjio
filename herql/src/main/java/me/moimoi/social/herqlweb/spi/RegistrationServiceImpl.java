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
import me.moimoi.social.herql.domain.SocialIdentity;
import me.moimoi.social.herql.integration.MessangerService;
import me.moimoi.social.herql.services.ContentServices;
import me.moimoi.social.herql.services.SocialIdentityService;
import me.moimoi.social.herql.services.SocialPersonService;
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
    private static final int FIRST = 0;
    
    @Inject
    public RegistrationServiceImpl(
            SocialIdentityService identityService,
            SocialPersonService personService,
            MessangerService messanger,
            ContentServices content) {
        
        this.identityService = identityService;
        this.personService = personService;
        this.messanger = messanger;
        this.content = content;
        
    }
    
    @Override
    public void register(SocialIdentity identity) {        
        this.personService.register(identity.getProfiles().get(RegistrationServiceImpl.FIRST));
        this.identityService.create(identity);
        String message = content.getSimple(null, "verify");
        messanger.send(message);
    }
}
