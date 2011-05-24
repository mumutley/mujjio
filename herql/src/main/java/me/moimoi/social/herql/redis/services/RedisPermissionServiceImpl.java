/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.redis.services;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.moimoi.social.herql.services.PermissionService;
import sma.RedisClient;
import sma.RedisClient.RedisException;

/**
 *
 * @author Suhail
 */
public class RedisPermissionServiceImpl implements PermissionService {
    
    private RedisClient client;
    
    @Inject
    public RedisPermissionServiceImpl(@Named("redis.db.host")  final String host, 
            @Named("redis.db.port")  final Integer port,
            @Named("redis.db.name")  final Integer database,
            @Named("redis.db.creds")  final String creds) {                                
        client = new RedisClient(host, port);
        client.selectdb(database);
    }
    
    /**
     * 
     * @param principle
     * @param role 
     */
    @Override
    public void getPermission(String principle, String role) {
        try {
            client.ping();
        } catch (RedisException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    private static final Logger LOG = Logger.getLogger(RedisPermissionServiceImpl.class.getName());
}
