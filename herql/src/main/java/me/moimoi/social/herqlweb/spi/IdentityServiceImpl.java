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
import com.google.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.moimoi.social.herql.data.IdentityDao;
import me.moimoi.social.herql.domain.SocialIdentity;
import me.moimoi.social.herql.services.IdentityService;
import me.moimoi.social.herql.services.SimpleDatasource;
import org.bson.types.ObjectId;

/**
 *
 * @author suhail
 */
public class IdentityServiceImpl extends BaseServices implements IdentityService {

    private final IdentityDao dao;
    @Inject
    public IdentityServiceImpl(SimpleDatasource ds, IdentityDao dao) {        
        super(ds);
        this.dao = dao;
    }
    
    
    

    @Override
    public Key<SocialIdentity> register(SocialIdentity identity) {
        Key<SocialIdentity> key = dao.save(identity);
        LOG.log( Level.INFO, "dao is here or now {0}", key.getId().toString());
        return key;
    }
    
    public SocialIdentity get(String id) {
        ObjectId ids = new ObjectId("suhail");
        
        return dao.get(ids);
    }
    private static final Logger LOG = Logger.getLogger(IdentityServiceImpl.class.getCanonicalName());
}
