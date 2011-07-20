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
package me.moimoi.social.herql.spi;

import com.google.code.morphia.Key;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.inject.Inject;
import java.util.logging.Logger;
import me.moimoi.social.herql.data.IdentityDao;
import me.moimoi.social.herql.data.PersonDao;
import me.moimoi.social.herql.domain.SocialIdentity;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.SocialIdentityService;
import me.moimoi.social.herql.services.SocialPersonService;
import me.moimoi.social.herql.spi.errors.InconsistentStateException;
import me.moimoi.social.herql.spi.errors.InvalidAuthenticationCode;
import me.moimoi.social.herql.spi.errors.InvalidPasswordException;
import me.moimoi.social.herql.spi.errors.UserNotFoundException;

/**
 *
 * @author suhail
 */
public class IdentityServiceImpl implements SocialIdentityService {

    private final IdentityDao dao;
    private final PersonDao pdao;
    private static final int FIRST = 0;

    @Inject
    public IdentityServiceImpl(IdentityDao dao, PersonDao pdao) {
        this.dao = dao;
        this.pdao = pdao;
    }

    @Override
    public Key<SocialIdentity> create(SocialIdentity identity) {
        SocialPerson existing = pdao.findOne(SocialPersonService.ID, identity.getProfiles().get(FIRST));
        if (this.get(identity.getLoginName()) == null && existing == null) {
            Key<SocialPerson> pkey = pdao.save(identity.getProfiles().get(0));
            identity.setActive(Boolean.FALSE);
            Key<SocialIdentity> key = dao.save(identity);
            return key;
        }
        return null;
    }

    @Override
    public SocialIdentity get(String id) {
        return dao.findOne(IdentityServiceImpl.IDENTITY_KEY, id);
    }

    @Override
    public void save(String id, SocialPerson person) {
        SocialPerson existing = pdao.findOne(SocialPersonService.ID, person.getId());
        if (this.get(id) != null && existing != null) {
            Query<SocialIdentity> q = dao.getDatastore().createQuery(SocialIdentity.class);
            q.field(IDENTITY_KEY).equal(id);
            UpdateOperations<SocialIdentity> ops = dao.getDatastore().createUpdateOperations(SocialIdentity.class);
            ops.add("profiles", person);
            dao.update(q, ops);
        }
    }

    @Override
    public SocialIdentity getRegistrationStatus(String code, Boolean status) {
        Query<SocialIdentity> q = dao.createQuery().disableValidation();
        q.and(q.criteria("activationCode").equal(code),
                q.criteria("active").equal(status));
        SocialIdentity found = q.get();
        if (q != null) {
            return found;
        }
        return null;
    }

    @Override
    public SocialIdentity validateCredentials(String code, String userId, String password) {
        Query<SocialIdentity> q = dao.createQuery().disableValidation();  
        SocialIdentity id = dao.findOne("_id", userId);
        
        //a user by the given id exists
        if(id != null ) {                        
            //now check if the account has been activated. It shouldn't be
            /*if(!id.getActive()) {
                throw new InconsistentStateException("The user " + userId + " has already verified the account.");
            } else */ if(!password.equals(id.getPassword())) {
                throw new InvalidPasswordException("The user " + userId + " has given the wrong credentials.");
            } else if(!code.equals(id.getActivationCode())) {
                throw new InvalidAuthenticationCode("The user " + userId + " has given the wrong authentication code.");
            }
            
            return id;
            
        } 
        //{"email":"suhailski@gmail.com","password":"password","activationCode":"886e93dd-2063-43d8-8535-eef6eef86bf9"}
        //http://localhost:8080/social/rest/signup/welcome
        throw new UserNotFoundException("The user " + userId + " is not registered with mujjio");               
    }
    
    private static final String IDENTITY_KEY = "loginName";
    private static final Logger LOG = Logger.getLogger(IdentityServiceImpl.class.getCanonicalName());
}
//mencoder dvd://1 -vf crop=720:352:0:64,scale=704:304 -ovc xvid -xvidencopts bvhq=1:chroma_opt:quant_type=mpeg:bitrate=658:pass=1 -oac copy -o /dev/null

//mencoder dvd://1 -vf crop=720:352:0:64,scale=704:304 -ovc xvid -xvidencopts bvhq=1:chroma_opt:quant_type=mpeg:bitrate=658:pass=2 -alang en -oac mp3lame -lameopts br=96:cbr:vol=6 -o 300.avi