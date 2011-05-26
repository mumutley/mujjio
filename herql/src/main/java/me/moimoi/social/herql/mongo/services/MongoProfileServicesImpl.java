/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.mongo.services;

import com.google.code.morphia.Key;
import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.moimoi.social.herql.domain.mutable.MutableSocialPerson;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.MutableObject;
import me.moimoi.social.herql.services.ProfileService;
import me.moimoi.social.herql.services.SimpleDatasource;
import me.moimoi.social.herql.services.interceptors.Creator;
import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.protocol.RestfulCollection;
import org.apache.shindig.social.opensocial.model.Account;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.spi.CollectionOptions;
import org.apache.shindig.social.opensocial.spi.GroupId;
import org.apache.shindig.social.opensocial.spi.UserId;

/**
 * Mongodb based implementation of the account service
 * @author Suhail
 */

@Singleton
public class MongoProfileServicesImpl implements ProfileService {

    private static final Logger LOG = Logger.getLogger(MongoProfileServicesImpl.class.getCanonicalName());
    
    private final SimpleDatasource dataSource;
    private final MutableObject instance;
    
    @Inject
    public MongoProfileServicesImpl(final SimpleDatasource dataSource, MutableObject instance) {
        this.dataSource = dataSource;
        this.instance = instance;
        LOG.log(Level.INFO, "initializing {0}", dataSource.getDataSource().getDB().getName() + " " + this.instance.getClass().getName());
    }      
    
    @Override
    public Key<Person> save(Person account) {
        LOG.log(Level.INFO, "saved {0}", account);
        Key<Person> key = dataSource.getDataSource().save(account);
        LOG.log(Level.INFO, "saved {0}", key);
        return key;
    }

    @Override
    public Account find(String userId, String domain) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Future<RestfulCollection<Person>> getPeople(Set<UserId> userIds, 
        GroupId groupId, CollectionOptions collectionOptions, Set<String> fields, 
        SecurityToken token) throws ProtocolException {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Future<Person> getPerson(UserId id, Set<String> fields, SecurityToken token) throws ProtocolException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Person find(String _id) {
        SocialPerson person = dataSource.getDataSource().get(SocialPerson.class, _id);        
        return person;       
    }

    @Override
    public Person find(String _id, Class type) {
        SocialPerson person = dataSource.getDataSource().get(SocialPerson.class, _id);
        return person;     
    }
    
    @Override @Creator
    public Person create() {
        SocialPerson p = SocialPerson.create();
        return p;
    }        

    @Override
    public UpdateOperations<SocialPerson> getUpdateOperation() {
        return dataSource.getDataSource().createUpdateOperations(SocialPerson.class);
    }
    
    @Override
    public void update(Person account) {
        UpdateOperations<SocialPerson> ops = ((MutableSocialPerson)this.instance).getUpdateOperation();
        Query<SocialPerson> query = getQuery();
        dataSource.getDataSource().update(query, ops);
    }
    
    private Query<SocialPerson> getQuery() {
        String _id = ((MutableSocialPerson)this.instance).getId();
        return dataSource.getDataSource().find(SocialPerson.class).field(Mapper.ID_KEY).equal(_id);
    }

}
