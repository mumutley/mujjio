/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herqlweb.config;

import com.google.common.collect.ImmutableSet;
import com.google.inject.name.Names;
import java.util.Set;
import me.moimoi.social.herql.integration.MessangerService;
import me.moimoi.social.herql.integration.spi.EmailMessangerServiceImpl;
import me.moimoi.social.herql.spi.HerqlOAuthDataStore;
import me.moimoi.social.herqlweb.handlers.AccountHandler;
import me.moimoi.social.herqlweb.handlers.IdentityHandler;
import me.moimoi.social.herqlweb.handlers.ProfileHandler;
import me.moimoi.social.herqlweb.handlers.SignupHandler;
import me.moimoi.social.herqlweb.handlers.ValidationHandler;
import me.moimoi.social.herqlweb.services.RegistrationService;
import me.moimoi.social.herqlweb.spi.RegistrationServiceImpl;
import org.apache.shindig.social.core.config.SocialApiGuiceModule;
import org.apache.shindig.social.opensocial.oauth.OAuthDataStore;
import org.apache.shindig.social.opensocial.service.ActivityHandler;
import org.apache.shindig.social.opensocial.service.ActivityStreamHandler;
import org.apache.shindig.social.opensocial.service.AlbumHandler;
import org.apache.shindig.social.opensocial.service.AppDataHandler;
import org.apache.shindig.social.opensocial.service.MediaItemHandler;
import org.apache.shindig.social.opensocial.service.MessageHandler;
import org.apache.shindig.social.opensocial.service.PersonHandler;

/**
 *
 * @author ManzoorS
 */
public class HerqlwebModule extends SocialApiGuiceModule {

    /* (non-Javadoc)
     * @see org.apache.shindig.social.core.config.SocialApiGuiceModule#configure()
     */
    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("default.domain")).toInstance("mujjio");
        bind(String.class).annotatedWith(Names.named("oauth.base-url")).toInstance("http://localhost/");

        //TODO need to deprecate in favour of the dao pattern
        //bind(SimpleDatasource.class).toProvider(MongoDataSource.class);
        bind(RegistrationService.class).to(RegistrationServiceImpl.class);
        bind(MessangerService.class).to(EmailMessangerServiceImpl.class);
        
        bind(OAuthDataStore.class).to(HerqlOAuthDataStore.class);

        super.configure();
    }

    @Override
    protected Set<Class<?>> getHandlers() {
        return ImmutableSet.of(ActivityHandler.class, AppDataHandler.class,
                PersonHandler.class, MessageHandler.class, AlbumHandler.class,
                MediaItemHandler.class, ActivityStreamHandler.class, /* AccountHandler.class, */
                /*ProfileHandler.class, IdentityHandler.class,*/ ValidationHandler.class, SignupHandler.class);
    }
}
