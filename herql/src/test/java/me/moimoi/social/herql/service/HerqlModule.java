/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.service;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
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
        bind(String.class).annotatedWith(Names.named("oauth.base-url")).toInstance("http://localhost/");
                       
        bind(OAuthDataStore.class).to(HerqlOAuthDataStore.class);        
    }
}
