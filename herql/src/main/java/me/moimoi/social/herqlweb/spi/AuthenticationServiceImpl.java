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
import me.moimoi.social.herql.services.SocialIdentityService;
import me.moimoi.social.herqlweb.forms.LoginForm;
import me.moimoi.social.herqlweb.services.AuthenticationService;

/**
 *
 * @author ManzoorS
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    public final SocialIdentityService identity;
    @Inject
    public AuthenticationServiceImpl(SocialIdentityService identityService) {
        identity = identityService;
    }
    
    @Override
    public Boolean authenticate(LoginForm form) {
        
        SocialIdentity loggedIn = identity.validateCredentials(
                                    form.getActivationCode(), 
                                    form.getEmail(), 
                                    form.getPassword());        
        return (loggedIn != null);
    }
    
    @Override
    public Boolean initailize(LoginForm form) {
        
        if(this.authenticate(form)) {            
            identity.set("active", Boolean.TRUE, form.getEmail());
            return Boolean.TRUE;
        }        
        return Boolean.FALSE;        
    }
    
}
