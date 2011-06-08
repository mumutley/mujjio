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
package me.moimoi.social.herql.data;

import com.google.code.morphia.Morphia;
import com.google.inject.Inject;
import com.mongodb.Mongo;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.DataAccess;
import org.bson.types.ObjectId;

/**
 *
 * @author ManzoorS
 */
public class PersonDao extends HerqlDAO<SocialPerson, ObjectId> implements DataAccess {

    @Inject
    public PersonDao(Morphia morphia, Mongo mongo) {
        super(morphia, mongo);
    }
}
