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
package org.apache.shindig.samples.springexample.spi;

import java.util.Date;
import me.moimoi.social.herql.domain.EntryType;
import me.moimoi.social.herql.domain.Registration;
import me.moimoi.social.herql.domain.SocialAccount;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.AccountService;
import org.apache.shindig.social.core.model.ListFieldImpl;
import org.apache.shindig.social.opensocial.model.Account;
import org.apache.shindig.social.opensocial.model.ListField;
import org.apache.shindig.social.opensocial.model.Person;

/**
 *
 * @author suhail
 */
public class AccountServiceImpl implements AccountService {

   

    @Override
    public Account find(String userId, String domain) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Person update(Registration register) {
        return null;
    }
    
    
    @Override
    public Person register(Registration register) {
        Person person = SocialPerson.create();        
        person.setId(register.getUserName());
        SocialAccount account = SocialAccount.create(register.getEmail(), register.getUserName(), "moimoi.com", register.getPassword());        
        person.getAccounts().add(account);
        ListField email = new ListFieldImpl(EntryType.home.name(), register.getEmail());
        person.getEmails().add(email);
        person.getLanguagesSpoken().add(register.getLanguage());
        person.setBirthday(new Date());
        return person;
    }
    
}
