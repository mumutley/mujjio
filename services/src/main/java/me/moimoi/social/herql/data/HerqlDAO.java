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
package me.moimoi.social.herql.data;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ManzoorS
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class HerqlDAO<T, K> extends BasicDAO<T, K> implements RawDAO {

    
    public HerqlDAO(Morphia morphia, Mongo mongo) {
        super(mongo, morphia, "social");
    }

    public HerqlDAO(Morphia morphia, Mongo mongo, String db) {
        super(mongo, morphia, db);
        super.ensureIndexes();
    }
     
    @Override
    public DBObject findOne(String key, Object value, String[] fieldNames) {
        
        DBCollection collection =  this.ds.getCollection(entityClazz);
        DBObject lookup = new BasicDBObject("_id", "suhail");
        
        Map<String, Integer> ids = new HashMap<String, Integer>();
        ids.put("className", 0);
        ids.put("joined", 1);
        ids.put("password", 1);
        ids.put("profiles", 0);        
        
        DBObject a = new BasicDBObject("className", ids);
        
        return collection.findOne(lookup, a);        
    }

    @Override
    public void close() {
        this.getDatastore().getMongo().close();
    }
}
