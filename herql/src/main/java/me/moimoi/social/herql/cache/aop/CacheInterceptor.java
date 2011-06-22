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
import me.moimoi.social.herql.cache.annotation.Cached;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.shindig.common.cache.CacheProvider;

public class CacheInterceptor implements MethodInterceptor {

    private CacheManager cacheManager;
    private CacheKeyGenerator cacheKeyGenerator;
    private CacheProvider provider;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        setupCacheIfNecessary(invocation);
        Cached c = invocation.getMethod().getAnnotation(Cached.class);
        System.out.println("cache name " + c.cache());
        return getResultFromCacheOrMethod(invocation);

    }

    private Object getResultFromCacheOrMethod(MethodInvocation invocation) throws Throwable {
        Cache cache = getCache(invocation);
        String cacheKey = getCacheKey(invocation);
        Element element = cache.get(cacheKey);
        if (element != null) {
            return element.getValue();
        } else {
            return getResultAndCache(invocation, cache, cacheKey);
        }

    }

    private Object getResultAndCache(MethodInvocation invocation, Cache cache,
            String cacheKey) throws Throwable {
        Object methodResult = invocation.proceed();
        Element elementResult = new Element(cacheKey, methodResult);
        cache.put(elementResult);
        return methodResult;
    }

    private String getCacheKey(MethodInvocation invocation) {

        return getCacheKeyGenerator().getCacheKey(invocation);
    }

    private void setupCacheIfNecessary(MethodInvocation invocation) {
        if (!cacheCreated(invocation)) {
            createCache(invocation);

        }
    }

    private void createCache(MethodInvocation invocation) {
        getCacheManager().addCache(getCacheName(invocation));
    }

    private boolean cacheCreated(MethodInvocation invocation) {
        return getCacheManager().cacheExists(getCacheName(invocation));
    }

    private Cache getCache(MethodInvocation invocation) {
        return getCacheManager().getCache(getCacheName(invocation));
    }

    private String getCacheName(MethodInvocation invocation) {        
        return invocation.getMethod().toString();
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
        return null;
    }
}