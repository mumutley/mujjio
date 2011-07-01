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

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.moimoi.social.herql.cache.annotation.Cached;
import me.moimoi.social.herql.services.TemplateService;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

/**
 *
 * @author suhail
 */
public class StringTemplateService implements TemplateService {

    private STGroup st;
    private static final Logger LOG = Logger.getLogger(StringTemplateService.class.getName());
    
    @Inject
    public StringTemplateService(@Named("herql.template.dir") String configPath) throws IOException {
        LOG.log(Level.INFO, "dir {0}", configPath);
        st = new STGroupDir(configPath);
        LOG.log(Level.INFO, " --%-- dir {0}", configPath);
    }

    @Cached(name = "templates")
    @Override
    public String apply(String domain, String template, Map<String, Object> params) {
        LOG.info(" --%-- in apply");
        
        try {
            System.out.println("dir " + new File(".").getCanonicalPath());
        } catch (IOException ex) {
            Logger.getLogger(StringTemplateService.class.getName()).log(Level.INFO, null, ex);
        }

        ST sta = st.getInstanceOf(domain + "/" + template);

        if (params == null) {
            throw new IllegalArgumentException("Parameter not found");
        }

        if (sta == null) {
            throw new RuntimeException("Template for " + domain + " and " + template + "not found");
        }

        Iterator<String> keys = params.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = params.get(key);
            sta.add(key, value);
        }
        return sta.render();
    }
}
