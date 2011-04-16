/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.mongo.services;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.moimoi.social.herql.domain.SocialAccount;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.SimpleDatasource;

/**
 *
 * @author Suhail
 */
public class MongoDataSource implements SimpleDatasource, Provider<SimpleDatasource> {

    private static Mongo mongo;
    private static DB db;
    private static Datastore ds;
    private static Morphia morphia = new Morphia();
    private String host;
    private String dbName;
    
    @Inject        
    public MongoDataSource(@Named("mongo.db.host")  final String host, @Named("mongo.db.name")  final String name) {
        this.host = host;
        this.dbName = name;
        morphia.map(SocialPerson.class)
               .map(SocialAccount.class);
    }
   
    @Override
    public SimpleDatasource get() {
        try {
            mongo = new Mongo(host);
            db = mongo.getDB(dbName);            
            ds = morphia.createDatastore(mongo, db.getName());            
        } catch (UnknownHostException ex) {
            Logger.getLogger(MongoDataSource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MongoException ex) {
            Logger.getLogger(MongoDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }
    
    @Override
    public Datastore getDataSource() {
        return ds;
    }
}
