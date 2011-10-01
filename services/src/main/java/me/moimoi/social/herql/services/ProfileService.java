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
package me.moimoi.social.herql.services;

import java.util.List;
import org.apache.shindig.protocol.model.Enum;
import org.apache.shindig.social.opensocial.model.LookingFor;

/**
 *
 * @author suhail
 */
public interface ProfileService {

    boolean addLookingFor(String id, Enum<LookingFor> looking);

    List<Enum<LookingFor>> getLookingFor(String id);
    
    public boolean removeLookingFor(String id, Enum<LookingFor> looking);
    
    
    
}
