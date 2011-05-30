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
import com.google.inject.Inject;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.AccountService;
import me.moimoi.social.herql.services.SimpleDatasource;
import org.apache.shindig.social.opensocial.model.Account;
import org.apache.shindig.social.opensocial.model.Person;

/**
 *
 * @author suhail
 */
public class AccountServiceImpl implements AccountService {

    @Inject
    public AccountServiceImpl(final SimpleDatasource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Account find(String userId, String domain) {
        Query<SocialPerson> q = dataSource.getDataSource().createQuery(SocialPerson.class).disableValidation();
        q.and(
                q.criteria("accounts.userId").equal(userId),
                q.criteria("accounts.domain").equal(domain));
        SocialPerson person = q.get();

        if (person == null) {
            return null;
        }

        Iterator<Account> ita = person.getAccounts().iterator();
        //in case there are multiple accounts, get the right one
        while (ita.hasNext()) {
            Account account = ita.next();
            if (account.getUserId().equals(userId) && account.getDomain().equals(domain)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public List<Account> find(String id) {
        SocialPerson person = dataSource.getDataSource().get(SocialPerson.class, id);
        if(person == null) return null;
        if (person.getAccounts() != null) {
            return person.getAccounts();
        }

        return null;
    }

    @Override
    public boolean add(String id, Account account) {
        Account existing = this.find(account.getUserId(), account.getDomain());
        //account already exists so return nothing
        if (existing != null) {
            return this.update(id, account);
        }

        SocialPerson person = dataSource.getDataSource().get(SocialPerson.class, id);
        if (person.getAccounts() != null) {
            person.getAccounts().add(account);
            dataSource.getDataSource().save(person);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public boolean update(String id, Account account) {
        SocialPerson person = dataSource.getDataSource().get(SocialPerson.class, id);
        if (person == null) {
            return Boolean.FALSE;
        }

        Iterator<Account> ita = person.getAccounts().iterator();
        //in case there are multiple accounts, get the right one
        while (ita.hasNext()) {
            Account existing = ita.next();
            if (account.getUserId().equals(account.getUserId()) && account.getDomain().equals(account.getDomain())) {
                existing.setUsername(account.getUsername());
                dataSource.getDataSource().save(person);
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    @Override
    public boolean delete(String id, Account account) {
        SocialPerson person = dataSource.getDataSource().get(SocialPerson.class, id);
        if (person == null) {
            return Boolean.FALSE;
        }

        //cannot delete the only account
        if(person.getAccounts().size() == 1) {
            return Boolean.FALSE;
        }
        
        Iterator<Account> ita = person.getAccounts().iterator();
        //in case there are multiple accounts, get the right one
        Account toRemove = null;
        while (ita.hasNext()) {
            Account existing = ita.next();
            if (account.getUserId().equals(account.getUserId()) && account.getDomain().equals(account.getDomain())) {
                person.getAccounts().remove(existing);
                dataSource.getDataSource().save(person);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;        
    }

    @Override
    public Key<Person> register(Person person) {
        SocialPerson existing = dataSource.getDataSource().get(SocialPerson.class, person.getId());
        if (existing != null) {
            return null; //cannot add an exisiting person
        }
        return dataSource.getDataSource().save(person);
    }
    final private SimpleDatasource dataSource;
    private static final Logger LOG = Logger.getLogger(AccountServiceImpl.class.getCanonicalName());
}
