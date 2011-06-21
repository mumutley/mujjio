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
import java.util.Set;
import java.util.concurrent.Future;
import me.moimoi.social.herql.data.PersonDao;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.SocialPersonService;
import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.protocol.RestfulCollection;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.spi.CollectionOptions;
import org.apache.shindig.social.opensocial.spi.GroupId;
import org.apache.shindig.social.opensocial.spi.UserId;

/**
 *
 * @author ManzoorS
 */
public class SocialPersonServiceImpl implements SocialPersonService {

    private final PersonDao dao;

    @Inject
    public SocialPersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    @Override
    public Key<SocialPerson> register(SocialPerson person) {
        return dao.save(person);
    }

    @Override
    public SocialPerson get(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Future<RestfulCollection<Person>> getPeople(Set<UserId> userIds, GroupId groupId, CollectionOptions collectionOptions, Set<String> fields, SecurityToken token) throws ProtocolException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Future<Person> getPerson(UserId id, Set<String> fields, SecurityToken token) throws ProtocolException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean exists(String id) {
        return dao.exists("_id", id);
    }
}
