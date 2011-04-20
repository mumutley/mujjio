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
import me.moimoi.social.herql.domain.MutableAgent;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.MutableObject;
import me.moimoi.social.herql.services.ProfileService;
import me.moimoi.social.herql.services.SimpleDatasource;
import me.moimoi.social.herql.services.interceptors.Creator;
import net.guts.event.Channel;
import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.protocol.RestfulCollection;
import org.apache.shindig.social.opensocial.model.Account;
import org.apache.shindig.social.opensocial.model.MutablePerson;
import org.apache.shindig.social.opensocial.spi.CollectionOptions;
import org.apache.shindig.social.opensocial.spi.GroupId;
import org.apache.shindig.social.opensocial.spi.UserId;

/**
 * Mongodb based implementation of the account service
 * @author Suhail
 */

@Singleton
public class MongoAccountServicesImpl implements ProfileService {

    private static final Logger LOG = Logger.getLogger(MongoAccountServicesImpl.class.getCanonicalName());
    
    private final SimpleDatasource dataSource;
    private final Channel<MutablePerson> channel;
    private final MutableObject instance;
    
    @Inject
    public MongoAccountServicesImpl(final SimpleDatasource dataSource, Channel<MutablePerson> channel, MutableObject instance) {
        this.dataSource = dataSource;
        this.channel = channel;        
        this.instance = instance;
        LOG.log(Level.INFO, "initializing {0}", dataSource.getDataSource().getDB().getName() + " " + this.instance.getClass().getName());
    }      
    
    @Override
    public Key<MutablePerson> register(MutablePerson account) {
        LOG.log(Level.INFO, "saved {0}", account);
        Key<MutablePerson> key = dataSource.getDataSource().save(account);
        LOG.log(Level.INFO, "saved {0}", key);
        return key;
    }

    @Override
    public Account find(String userId, String domain) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Future<RestfulCollection<MutablePerson>> getPeople(Set<UserId> userIds, 
        GroupId groupId, CollectionOptions collectionOptions, Set<String> fields, 
        SecurityToken token) throws ProtocolException {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Future<MutablePerson> getPerson(UserId id, Set<String> fields, SecurityToken token) throws ProtocolException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public MutablePerson find(String _id) {
        MutablePerson person = dataSource.getDataSource().get(SocialPerson.class, _id);
        this.instance.setDelegate(person);
        ((MutableAgent)this.instance).setUpdateOperations(getUpdateOperation());        
        return (MutableAgent)this.instance;       
    }

    @Override @Creator
    public MutablePerson create() {
        MutablePerson p = SocialPerson.create();
        channel.publish(p);
        return p;
    }        

    @Override
    public UpdateOperations<SocialPerson> getUpdateOperation() {
        return dataSource.getDataSource().createUpdateOperations(SocialPerson.class);
    }
    
    @Override
    public void update(MutablePerson account) {
        UpdateOperations<SocialPerson> ops = ((MutableAgent)this.instance).getUpdateOperation();
        Query<SocialPerson> query = getQuery();
        dataSource.getDataSource().update(query, ops);
    }
    
    private Query<SocialPerson> getQuery() {
        String _id = ((MutableAgent)this.instance).getId();
        return dataSource.getDataSource().find(SocialPerson.class).field(Mapper.ID_KEY).equal(_id);
    }

}
