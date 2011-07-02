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

import net.sf.ehcache.CacheManager;


import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import me.moimoi.social.herql.cache.StringBasedKeyGenerator;
import me.moimoi.social.herql.cache.annotation.Cached;
import me.moimoi.social.herql.cache.aop.CacheInterceptor;
import me.moimoi.social.herql.cache.aop.CacheKeyGenerator;

public class CacheModule extends AbstractModule {

    @Override
    protected void configure() {

        //bind(CacheManager.class).toInstance(CacheManager.create());
        bind(CacheKeyGenerator.class).to(getCacheKeyGeneratorClass());
        CacheInterceptor cacheInterceptor = new CacheInterceptor();
        requestInjection(cacheInterceptor);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Cached.class), cacheInterceptor);
    }

    protected Class getCacheKeyGeneratorClass() {
        return StringBasedKeyGenerator.class;
    }
}