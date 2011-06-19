/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.config;

import com.google.common.collect.ImmutableSet;
import com.google.inject.name.Names;
import java.util.Set;
import me.moimoi.social.herql.handlers.AccountHandler;
import me.moimoi.social.herql.handlers.IdentityHandler;
import me.moimoi.social.herql.handlers.ProfileHandler;
import me.moimoi.social.herql.handlers.SignupHandler;
import me.moimoi.social.herql.handlers.ValidationHandler;
import me.moimoi.social.herql.mongo.services.MongoDataSource;
import me.moimoi.social.herql.services.AccountService;
import me.moimoi.social.herql.services.SocialIdentityService;
import me.moimoi.social.herql.services.ProfileService;
import me.moimoi.social.herql.services.SimpleDatasource;
import me.moimoi.social.herql.services.SocialPersonService;
import me.moimoi.social.herqlweb.spi.HerqlOAuthDataStore;
import me.moimoi.social.herqlweb.spi.OpenSocialActivityStreamService;
import me.moimoi.social.herqlweb.spi.OpenSocialDataService;
import me.moimoi.social.herqlweb.spi.AccountServiceImpl;
import me.moimoi.social.herqlweb.spi.IdentityServiceImpl;
import me.moimoi.social.herqlweb.spi.ProfileServiceImpl;
import me.moimoi.social.herqlweb.spi.SocialIdentityServiceImpl;
import me.moimoi.social.herqlweb.spi.SocialPersonServiceImpl;
import org.apache.shindig.social.core.config.SocialApiGuiceModule;
import org.apache.shindig.social.opensocial.oauth.OAuthDataStore;
import org.apache.shindig.social.opensocial.service.ActivityHandler;
import org.apache.shindig.social.opensocial.service.ActivityStreamHandler;
import org.apache.shindig.social.opensocial.service.AlbumHandler;
import org.apache.shindig.social.opensocial.service.AppDataHandler;
import org.apache.shindig.social.opensocial.service.MediaItemHandler;
import org.apache.shindig.social.opensocial.service.MessageHandler;
import org.apache.shindig.social.opensocial.service.PersonHandler;
import org.apache.shindig.social.opensocial.spi.ActivityService;
import org.apache.shindig.social.opensocial.spi.ActivityStreamService;
import org.apache.shindig.social.opensocial.spi.AlbumService;
import org.apache.shindig.social.opensocial.spi.AppDataService;
import org.apache.shindig.social.opensocial.spi.MediaItemService;
import org.apache.shindig.social.opensocial.spi.MessageService;
import org.apache.shindig.social.opensocial.spi.PersonService;

/**
 *
 * @author ManzoorS
 */
public class HerqlGuiceModule extends SocialApiGuiceModule {

    /* (non-Javadoc)
     * @see org.apache.shindig.social.core.config.SocialApiGuiceModule#configure()
     */
    @Override
    protected void configure() {        
        bind(String.class).annotatedWith(Names.named("json.db")).toInstance("WEB-INF/canonicaldb.json");
        bind(String.class).annotatedWith(Names.named("default.domain")).toInstance("mujjio");
        
        bind(String.class).annotatedWith(Names.named("mongo.db.host")).toInstance("localhost");
        bind(String.class).annotatedWith(Names.named("mongo.db.name")).toInstance("social");
        bind(String.class).annotatedWith(Names.named("oauth.base-url")).toInstance("http://localhost/");

        //TODO need to deprecate in favour of the dao pattern
        bind(SimpleDatasource.class).toProvider(MongoDataSource.class);

        bind(ActivityService.class).to(OpenSocialDataService.class);
        bind(ActivityStreamService.class).to(OpenSocialActivityStreamService.class);
        bind(AlbumService.class).to(OpenSocialDataService.class);
        bind(MediaItemService.class).to(OpenSocialDataService.class);
        bind(AppDataService.class).to(OpenSocialDataService.class);        
        bind(MessageService.class).to(OpenSocialDataService.class);
        bind(OAuthDataStore.class).to(HerqlOAuthDataStore.class);
        
        //TODO need to move to person service        
        bind(SocialPersonService.class).to(SocialPersonServiceImpl.class);
        bind(PersonService.class).to(SocialPersonServiceImpl.class);
        
        bind(ProfileService.class).to(ProfileServiceImpl.class); 
        bind(AccountService.class).to(AccountServiceImpl.class);
        
        bind(SocialIdentityService.class).to(IdentityServiceImpl.class);
        
        super.configure();
    }

    @Override
    protected Set<Class<?>> getHandlers() {
        return ImmutableSet.of(ActivityHandler.class, AppDataHandler.class,
                PersonHandler.class, MessageHandler.class, AlbumHandler.class,
                MediaItemHandler.class, ActivityStreamHandler.class, AccountHandler.class,
                ProfileHandler.class, IdentityHandler.class, ValidationHandler.class, SignupHandler.class);
    }
}
