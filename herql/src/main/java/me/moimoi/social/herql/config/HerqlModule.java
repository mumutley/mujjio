/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.config;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import me.moimoi.social.herql.domain.mutable.MutableSocialPerson;
import me.moimoi.social.herql.mongo.services.MongoProfileServicesImpl;
import me.moimoi.social.herql.mongo.services.MongoDataSource;
import me.moimoi.social.herql.redis.services.RedisPermissionServiceImpl;
import me.moimoi.social.herql.services.MutableObject;
import me.moimoi.social.herql.services.PermissionService;
import me.moimoi.social.herql.services.ProfileService;
import me.moimoi.social.herql.services.SimpleDatasource;
import me.moimoi.social.herql.services.interceptors.Mutator;
import me.moimoi.social.herql.services.interceptors.Creator;
import me.moimoi.social.herql.services.interceptors.SetMethodInterceptor;
import me.moimoi.social.herqlweb.spi.HerqlOAuthDataStore;
import org.apache.shindig.social.opensocial.oauth.OAuthDataStore;

/**
 *
 * @author Suhail
 */
public class HerqlModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("mongo.db.host")).toInstance("localhost");
        bind(String.class).annotatedWith(Names.named("mongo.db.name")).toInstance("social");        
        
        bind(String.class).annotatedWith(Names.named("redis.db.host")).toInstance("localhost");
        bind(Integer.class).annotatedWith(Names.named("redis.db.port")).toInstance(6379);
        bind(Integer.class).annotatedWith(Names.named("redis.db.name")).toInstance(11);
        bind(String.class).annotatedWith(Names.named("redis.db.creds")).toInstance("jredis");
        bind(String.class).annotatedWith(Names.named("oauth.base-url")).toInstance("http://localhost/");
        
        bind(SimpleDatasource.class).toProvider(MongoDataSource.class);
        bind(ProfileService.class).to(MongoProfileServicesImpl.class);
        bind(PermissionService.class).to(RedisPermissionServiceImpl.class);
        
        bind(MutableObject.class).to(MutableSocialPerson.class);
        bind(OAuthDataStore.class).to(HerqlOAuthDataStore.class);
        
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Creator.class), new SetMethodInterceptor());
        bindInterceptor(Matchers.any(),Matchers.annotatedWith(Mutator.class), new SetMethodInterceptor());
    }
}
