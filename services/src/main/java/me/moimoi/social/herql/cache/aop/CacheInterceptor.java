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
package me.moimoi.social.herql.cache.aop;

import com.google.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.moimoi.social.herql.cache.annotation.Cached;

import net.sf.ehcache.CacheManager;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.shindig.common.cache.Cache;
import org.apache.shindig.common.cache.CacheProvider;

public class CacheInterceptor implements MethodInterceptor {

    private CacheManager cacheManager;
    private CacheKeyGenerator cacheKeyGenerator;
    private CacheProvider provider;
    private static final Logger LOG = Logger.getLogger(CacheInterceptor.class.getName());
    private Cache<String, String> cache;
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        
        LOG.log(Level.INFO, "cache name {0}", provider.getClass().getName());        
        
        Cached c = invocation.getMethod().getAnnotation(Cached.class);        
        cache = getCacheProvider().createCache(c.name());  
                
        CacheKey key = (CacheKey)invocation.getArguments()[0];
        String cached = cache.getElement(key.getKey()) ;
        if(cached == null) {
            Object o =  invocation.proceed();
            cache.addElement(key.getKey(), (String)o);
            return o;
        }                
        
        return cached;
    }
        
    @Inject
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    @Inject
    public void setCacheKeyGenerator(CacheKeyGenerator cacheKeyGenerator) {
        this.cacheKeyGenerator = cacheKeyGenerator;
    }

    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    @Inject
    public void setCacheProvider(CacheProvider provider) {
        this.provider = provider;
    }

    public CacheProvider getCacheProvider() {
        return this.provider;
    }
}