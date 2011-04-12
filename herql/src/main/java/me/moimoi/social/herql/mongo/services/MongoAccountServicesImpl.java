/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.mongo.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.moimoi.social.herql.services.ProfileService;
import me.moimoi.social.herql.services.SimpleDatasource;
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
public class MongoAccountServicesImpl implements ProfileService {

    private final SimpleDatasource dataSource;
    
    
    @Inject
    public MongoAccountServicesImpl(final SimpleDatasource dataSource) {
        this.dataSource = dataSource;
        LOG.log(Level.INFO, "initializing {0}", dataSource.getDataSource().getDB().getName());
    }
      /*      @Named("mongo.db.host")  final String host,
            @Named("mongo.db.port")  final String port) {
        
        LOG.log(Level.INFO, "--->> initializing {0} ", MongoAccountServicesImpl.class.getCanonicalName() + " " + host);
        
        this.host = host;
        this.port = Integer.valueOf(port).intValue();        
    } */
    
    @Override
    public void register(Account account) {
        throw new UnsupportedOperationException("Not supported yet.");
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
    
    private static final Logger LOG = Logger.getLogger(MongoAccountServicesImpl.class.getCanonicalName());
}
