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

import com.google.inject.AbstractModule;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Object;
import java.util.Hashtable;
import java.util.Properties;
import org.apache.commons.io.IOUtils;
import org.apache.shindig.common.util.ResourceLoader;

/**
 *
 * @author ManzoorS
 */
public class FilterPropertiesModule extends AbstractModule {

    private final static String DEFAULT_PROPERTIES = "filter.properties";
    private final Properties properties;
    
    public FilterPropertiesModule() {
        super();
        this.properties = readPropertyFile(getDefaultPropertiesPath());
    }

    public FilterPropertiesModule(String propertyFile) {
        this.properties = readPropertyFile(propertyFile);
    }

    public FilterPropertiesModule(Properties properties) {
        this.properties = properties;
    }
    
    protected static String getDefaultPropertiesPath() {
        return DEFAULT_PROPERTIES;
    }

    protected Properties getProperties() {
        return properties;
    }
    
    private Properties readPropertyFile(String propertyFile) {
        Properties props = new Properties();
        InputStream is = null;        
        try {
            is = ResourceLoader.openResource(propertyFile);
            props.load(is);

            String value = null;
            for (Object key : props.keySet()) {
                value = (String) props.get((String) key);
                
            }

        } catch (IOException e) {            
        } finally {
            IOUtils.closeQuietly(is);
        }
        return props;
    }

    @Override
    protected void configure() {
      
    }
}
