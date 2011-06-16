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
import java.net.ProtocolException;
import java.util.concurrent.Future;
import me.moimoi.social.herql.services.AccountService;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.config.ContainerConfig;
import org.apache.shindig.protocol.Operation;
import org.apache.shindig.protocol.Service;
import org.apache.shindig.social.opensocial.service.SocialRequestItem;

/**
 *
 * @author suhail
 */

@Service(name = "signup")
public class SignupHandler {
   
    private final AccountService accountService;
    private final ContainerConfig config;
    
    @Inject
    public SignupHandler(AccountService accountService, ContainerConfig config) {
        this.accountService = accountService;
        this.config = config;        
    }
    
    @Operation(httpMethods = "POST", bodyParam = "entity")
    public Future<?> create(SocialRequestItem request) throws ProtocolException {               
       return ImmediateFuture.newInstance(Boolean.FALSE); 
    }
}
