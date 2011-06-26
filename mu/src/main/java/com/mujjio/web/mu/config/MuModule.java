/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mujjio.web.mu.config;

import com.google.inject.name.Names;
import me.moimoi.social.herql.integration.MessangerService;
import me.moimoi.social.herql.integration.spi.MessangerServiceImpl;
import org.apache.shindig.social.core.config.SocialApiGuiceModule;

/**
 *
 * @author suhail
 */
public class MuModule extends SocialApiGuiceModule {

    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("json.db")).toInstance("WEB-INF/canonicaldb.json");
        bind(MessangerService.class).to(MessangerServiceImpl.class);
    }
}
