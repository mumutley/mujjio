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
package me.moimoi.social.herql.services;

import com.google.code.morphia.Key;
import me.moimoi.social.herql.domain.SocialPerson;
import org.apache.shindig.social.opensocial.spi.PersonService;

/**
 *
 * @author ManzoorS
 */

public interface SocialPersonService extends PersonService {

    String ID = "_id";
    
    Key<SocialPerson> register(SocialPerson person);

    SocialPerson get(String id);
}
