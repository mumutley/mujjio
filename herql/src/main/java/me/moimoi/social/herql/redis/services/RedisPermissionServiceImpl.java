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
import org.jredis.JRedis;
import org.jredis.RedisException;
import org.jredis.connector.ConnectionSpec;
import org.jredis.ri.alphazero.JRedisClient;
import org.jredis.ri.alphazero.connection.DefaultConnectionSpec;

/**
 *
 * @author Suhail
 */
public class RedisPermissionServiceImpl implements PermissionService {
    
    private JRedis jredis = null;
    
    @Inject
    public RedisPermissionServiceImpl(@Named("redis.db.host")  final String host, 
            @Named("redis.db.port")  final Integer port,
            @Named("redis.db.name")  final Integer database,
            @Named("redis.db.creds")  final String creds) {
                
        ConnectionSpec spec = DefaultConnectionSpec.newSpec(host, port, database, creds.getBytes());
        jredis = new JRedisClient(spec);
    }
    
    /**
     * 
     * @param principle
     * @param role 
     */
    @Override
    public void getPermission(String principle, String role) {
        try {
            jredis.ping();
        } catch (RedisException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    private static final Logger LOG = Logger.getLogger(RedisPermissionServiceImpl.class.getName());
}
