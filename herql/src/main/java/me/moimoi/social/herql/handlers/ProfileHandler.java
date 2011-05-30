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
import java.util.concurrent.Future;
import me.moimoi.social.herqlweb.spi.EnumValue;
import me.moimoi.social.herql.services.AccountService;
import me.moimoi.social.herql.services.ProfileService;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.config.ContainerConfig;
import org.apache.shindig.protocol.Operation;
import org.apache.shindig.protocol.RequestItem;
import org.apache.shindig.protocol.Service;
import org.apache.shindig.protocol.model.Enum;
import org.apache.shindig.protocol.model.EnumImpl;
import org.apache.shindig.social.opensocial.model.LookingFor;

/**
 *
 * @author suhail
 */
@Service(name = "profile", path = "/{userId}/{groupId}")
public class ProfileHandler {

    private final ProfileService profileService;
    private final ContainerConfig config;

    @Inject
    public ProfileHandler(ProfileService profileService, ContainerConfig config) {
        this.profileService = profileService;
        this.config = config;
    }

    @Operation(httpMethods = "GET", path = "/{userId}/@lookingFor")
    public Future<?> getLookingFor(RequestItem request) {                
        return ImmediateFuture.newInstance(profileService.getLookingFor(request.getParameter("userId")));
    }

    @Operation(httpMethods = "PUT", bodyParam = "entity", path = "/{userId}/@lookingFor")
    public Future<?> addLookingFor(RequestItem request) {        
        EnumValue enu = request.getTypedParameter("entity", EnumValue.class);                
        Enum<LookingFor> looking = new EnumImpl<LookingFor>(LookingFor.valueOf(enu.getValue()));
        Boolean outcome = profileService.addLookingFor(request.getParameter("userId"), looking);
        return ImmediateFuture.newInstance(outcome);
    }

    @Operation(httpMethods = "DELETE", path = "/{userId}/@lookingFor")
    public Future<?> deleteLookingFor(RequestItem request) {        
        return ImmediateFuture.newInstance(Boolean.TRUE);
    }
}
