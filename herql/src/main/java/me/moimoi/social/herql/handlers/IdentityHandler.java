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
package me.moimoi.social.herql.handlers;

import com.google.inject.Inject;
import java.util.Date;
import java.util.concurrent.Future;
import me.moimoi.social.herql.domain.SocialIdentity;
import me.moimoi.social.herql.services.IdentityService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.config.ContainerConfig;
import org.apache.shindig.protocol.Operation;
import org.apache.shindig.protocol.RequestItem;
import org.apache.shindig.protocol.Service;

/**
 *
 * @author ManzoorS
 */

@Service(name = "identity")
public class IdentityHandler {
    
    private final IdentityService idService;
    private final ContainerConfig config;
    
    @Inject
    public IdentityHandler(IdentityService idService, ContainerConfig config) {
        this.idService = idService;
        this.config = config;
    }
    
    @Operation(httpMethods = "POST", bodyParam = "entity", path = "/{userId}")
    public Future<?> add(RequestItem request) {
        return ImmediateFuture.newInstance(Boolean.TRUE);
    }
    
    @Operation(httpMethods = "GET", path = "/{userId}")
    public Future<?> get(RequestItem request) {
        
        SocialIdentity identity = new SocialIdentity();
        identity.setActive(Boolean.TRUE);
        identity.setJoined(new Date());
        identity.setLastLogin(new Date());
        identity.setLoginName(request.getParameter("userId"));
        identity.setPassword(DigestUtils.sha512Hex("password"));
        return ImmediateFuture.newInstance(identity);
    }    
}
