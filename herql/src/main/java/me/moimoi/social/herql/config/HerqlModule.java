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
import me.moimoi.social.herql.services.MutableObject;
import me.moimoi.social.herql.services.ProfileService;
import me.moimoi.social.herql.services.SimpleDatasource;
import me.moimoi.social.herql.services.interceptors.Mutator;
import me.moimoi.social.herql.services.interceptors.Creator;
import me.moimoi.social.herql.services.interceptors.SetMethodInterceptor;

/**
 *
 * @author Suhail
 */
public class HerqlModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("mongo.db.host")).toInstance("localhost");
        bind(String.class).annotatedWith(Names.named("mongo.db.name")).toInstance("social");        
                
        bind(SimpleDatasource.class).toProvider(MongoDataSource.class);
        bind(ProfileService.class).to(MongoProfileServicesImpl.class);
        
        bind(MutableObject.class).to(MutableSocialPerson.class);
        
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Creator.class), new SetMethodInterceptor());
        bindInterceptor(Matchers.any(),Matchers.annotatedWith(Mutator.class), new SetMethodInterceptor());
    }
}
