/*
 * Copyright 2011 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.moimoi.social.herql.config;

import com.google.inject.name.Names;
import me.moimoi.social.herql.services.ContentServices;
import me.moimoi.social.herql.services.SocialIdentityService;
import me.moimoi.social.herql.services.SocialPersonService;
import me.moimoi.social.herql.services.TemplateService;
import me.moimoi.social.herql.spi.IdentityServiceImpl;
import me.moimoi.social.herql.spi.OpenSocialActivityStreamService;
import me.moimoi.social.herql.spi.OpenSocialDataService;
import me.moimoi.social.herql.spi.SimpleContentServiceImpl;
import me.moimoi.social.herql.spi.SocialPersonServiceImpl;
import me.moimoi.social.herql.spi.StringTemplateService;
import org.apache.shindig.social.core.config.SocialApiGuiceModule;
import org.apache.shindig.social.opensocial.spi.ActivityService;
import org.apache.shindig.social.opensocial.spi.ActivityStreamService;
import org.apache.shindig.social.opensocial.spi.AlbumService;
import org.apache.shindig.social.opensocial.spi.AppDataService;
import org.apache.shindig.social.opensocial.spi.MediaItemService;
import org.apache.shindig.social.opensocial.spi.MessageService;
import org.apache.shindig.social.opensocial.spi.PersonService;

/**
 *
 * @author suhail
 */
public class HerqlModule extends SocialApiGuiceModule {

    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("mongo.db.host")).toInstance("localhost");
        bind(String.class).annotatedWith(Names.named("mongo.db.name")).toInstance("social");

        bind(ActivityService.class).to(OpenSocialDataService.class);
        bind(ActivityStreamService.class).to(OpenSocialActivityStreamService.class);
        bind(AlbumService.class).to(OpenSocialDataService.class);
        bind(MediaItemService.class).to(OpenSocialDataService.class);
        bind(AppDataService.class).to(OpenSocialDataService.class);
        bind(MessageService.class).to(OpenSocialDataService.class);
        
        
        bind(ContentServices.class).to(SimpleContentServiceImpl.class);
        bind(TemplateService.class).to(StringTemplateService.class) ;
        //TODO need to move to person service        
        bind(SocialPersonService.class).to(SocialPersonServiceImpl.class);
        bind(PersonService.class).to(SocialPersonServiceImpl.class);

        //bind(ProfileService.class).to(ProfileServiceImpl.class);
        //bind(AccountService.class).to(AccountServiceImpl.class);

        bind(SocialIdentityService.class).to(IdentityServiceImpl.class);       
    }
}
