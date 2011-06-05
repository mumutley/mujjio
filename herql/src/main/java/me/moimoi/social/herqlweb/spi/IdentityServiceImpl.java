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

import com.google.code.morphia.Key;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.moimoi.social.herql.data.IdentityDao;
import me.moimoi.social.herql.data.PersonDao;
import me.moimoi.social.herql.domain.SocialIdentity;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.IdentityService;
import me.moimoi.social.herql.services.SocialPersonService;

/**
 *
 * @author suhail
 */
public class IdentityServiceImpl implements IdentityService {

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
            identity.setActive(Boolean.TRUE);
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
    
    private static final String IDENTITY_KEY = "loginName";
    private static final Logger LOG = Logger.getLogger(IdentityServiceImpl.class.getCanonicalName());
}
//mencoder dvd://1 -vf crop=720:352:0:64,scale=704:304 -ovc xvid -xvidencopts bvhq=1:chroma_opt:quant_type=mpeg:bitrate=658:pass=1 -oac copy -o /dev/null

//mencoder dvd://1 -vf crop=720:352:0:64,scale=704:304 -ovc xvid -xvidencopts bvhq=1:chroma_opt:quant_type=mpeg:bitrate=658:pass=2 -alang en -oac mp3lame -lameopts br=96:cbr:vol=6 -o 300.avi