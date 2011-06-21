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
package me.moimoi.social.herqlweb.spi;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.io.IOException;
import me.moimoi.social.herql.services.TemplateService;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

/**
 *
 * @author suhail
 */
public class StringTemplateService implements TemplateService {
    
    private STGroup st;
    
    @Inject
    public StringTemplateService(@Named("herql.template.dir") String configPath) throws IOException {
        //InputStream configStream = ResourceLoader.open(configPath);
        st = new STGroupDir(configPath);
        
    }
    
    public String getTemplate(String domain, String template) {
        st.getInstanceOf(domain + "" + template);
        return null;
    }
}
