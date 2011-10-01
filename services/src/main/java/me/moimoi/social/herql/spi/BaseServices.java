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

import com.google.code.morphia.Datastore;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.SimpleDatasource;

/**
 *
 * @author suhail
 */
public abstract class BaseServices {
    
    public BaseServices(SimpleDatasource dataSource) {
        this.dataSource = dataSource;
        this.ds = dataSource.getDataSource();
    }
    
    protected SocialPerson getPerson(String id) {
        return dataSource.getDataSource().get(SocialPerson.class, id);
    }
    
    protected void save(SocialPerson person) {
        dataSource.getDataSource().save(person);
    }
            
    final protected SimpleDatasource dataSource; 
    final protected Datastore ds;
}
