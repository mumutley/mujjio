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
package me.moimoi.social.herql.spi;

import com.google.inject.Inject;
import java.util.Map;
import me.moimoi.social.herql.services.ContentServices;
import me.moimoi.social.herql.services.TemplateService;

/**
 *
 * @author suhail
 */
public class SimpleContentServiceImpl implements ContentServices {
 
    private final TemplateService tempaltes;
    
    @Inject
    public SimpleContentServiceImpl(TemplateService templates) {
        this.tempaltes = templates;
    }
    
    @Override
    public String getSimple(String node, String property, Map<String, Object> params) {
        return tempaltes.getTemplate(node, property, params);
    }
    
}
