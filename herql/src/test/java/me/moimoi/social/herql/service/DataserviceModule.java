/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package me.moimoi.social.herql.service;

import org.apache.shindig.social.opensocial.oauth.OAuthDataStore;
import org.apache.shindig.social.opensocial.spi.ActivityService;
import org.apache.shindig.social.opensocial.spi.ActivityStreamService;
import org.apache.shindig.social.opensocial.spi.AlbumService;
import org.apache.shindig.social.opensocial.spi.AppDataService;
import org.apache.shindig.social.opensocial.spi.MediaItemService;
import org.apache.shindig.social.opensocial.spi.MessageService;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import me.moimoi.social.herqlweb.spi.HerqlOAuthDataStore;
import me.moimoi.social.herqlweb.spi.OpenSocialDataService;
import me.moimoi.social.herqlweb.spi.OpenSocialActivityStreamService;

/**
 * Provides bindings for sample-only implementations of social API
 * classes.  This class should never be used in production deployments,
 * but does provide a good overview of the pieces of Shindig that require
 * custom container implementations.
 */
public class DataserviceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(String.class).annotatedWith(Names.named("json.db")).toInstance("WEB-INF/canonicaldb.json");
    //bind(String.class).annotatedWith(Names.named("shindig.canonical.json.db")).toInstance("sampledata/canonicaldb.json");
    bind(ActivityService.class).to(OpenSocialDataService.class);
    bind(ActivityStreamService.class).to(OpenSocialActivityStreamService.class);
    bind(AlbumService.class).to(OpenSocialDataService.class);
    bind(MediaItemService.class).to(OpenSocialDataService.class);
    bind(AppDataService.class).to(OpenSocialDataService.class);
    //bind(PersonService.class).to(HerqlOpensocialDataService.class);
    bind(MessageService.class).to(OpenSocialDataService.class);
    bind(OAuthDataStore.class).to(HerqlOAuthDataStore.class);
  }
}
