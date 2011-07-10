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
import com.google.inject.Inject;
import me.moimoi.social.herql.data.IdentityDao;
import me.moimoi.social.herql.domain.SocialIdentity;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.SocialIdentityService;

/**
 *
 * @author suhail
 */
public class SocialIdentityServiceImpl implements SocialIdentityService {
    
    private final IdentityDao dao;
    
    @Inject
    public SocialIdentityServiceImpl(IdentityDao dao){
        this.dao = dao;
    }
    
    @Override
    public Key<SocialIdentity> create(SocialIdentity identity) {
        Key<SocialIdentity> keys = dao.save(identity);
        return keys;
    }

    @Override
    public SocialIdentity get(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(String id, SocialPerson person) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean validate(String code) {
        Query<SocialIdentity> q = dao.createQuery().disableValidation();
        SocialIdentity id =  q.field("activationCode").equal(code).get();
        return (id == null);
    }
}
