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
import java.util.List;
import java.util.logging.Logger;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.ProfileService;
import me.moimoi.social.herql.services.SimpleDatasource;
import org.apache.shindig.social.opensocial.model.LookingFor;
import org.apache.shindig.protocol.model.Enum;

/**
 *
 * @author suhail
 */
public class ProfileServiceImpl extends BaseServices implements ProfileService {

    @Inject
    public ProfileServiceImpl(SimpleDatasource ds) {
        super(ds);
    }
    
    @Override
    public boolean addLookingFor(String id, Enum<LookingFor> looking) {
        SocialPerson person = getPerson(id);
        if(person == null) return Boolean.FALSE;
        
        person.getLookingFor().add(looking);
        this.save(person);
        
        return Boolean.TRUE;
    }
    
    @Override
    public List<Enum<LookingFor>> getLookingFor(String id) {
        SocialPerson person = getPerson(id);
        if(person != null) return person.getLookingFor();
        
        return null;
    }
    
    private static final Logger LOG = Logger.getLogger(ProfileServiceImpl.class.getCanonicalName());
}
