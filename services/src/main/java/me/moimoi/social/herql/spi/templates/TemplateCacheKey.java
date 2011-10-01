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
package me.moimoi.social.herql.spi.templates;

import me.moimoi.social.herql.cache.aop.CacheKey;

/**
 *
 * @author suhail
 */
public class TemplateCacheKey implements CacheKey {
    
    private String domain;
    private String template;
    
    public TemplateCacheKey(String domain, String template) {
        this.domain = domain;
        this.template = template;
    }

    @Override
    public String getKey() {
        return this.domain + "/" + this.template;
    }        
}
