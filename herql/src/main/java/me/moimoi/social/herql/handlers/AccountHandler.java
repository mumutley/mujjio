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

import com.google.code.morphia.Key;
import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.List;
import java.util.concurrent.Future;
import me.moimoi.social.herql.domain.SocialAccount;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.AccountService;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.config.ContainerConfig;
import org.apache.shindig.protocol.Operation;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.protocol.RequestItem;
import org.apache.shindig.protocol.RestfulCollection;
import org.apache.shindig.protocol.Service;
import org.apache.shindig.social.opensocial.model.Account;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.service.SocialRequestItem;

/**
 *
 * @author suhail
 */
@Service(name = "account", path = "/{userId}/{groupId}")
public class AccountHandler {

    private final AccountService accountService;
    private final ContainerConfig config;

    @Inject
    public AccountHandler(AccountService accountService, ContainerConfig config) {
        this.accountService = accountService;
        this.config = config;
    }

    @Operation(httpMethods = "GET")
    public Future<?> find(SocialRequestItem request) throws ProtocolException {
        //http://localhost:8084/social/rest/account/ski/moimoi.me
        String userId = request.getParameter("userId");
        String gourpId = request.getParameter("groupId");
        if(gourpId != null) {
            Account account = accountService.find(userId, gourpId);
            return ImmediateFuture.newInstance(account);
        }
        
        List<Account> accounts = accountService.find(userId);
        RestfulCollection<Account> restfulAccount = new RestfulCollection<Account>(accounts); 
        return ImmediateFuture.newInstance(restfulAccount);
    }
        
    @Operation(httpMethods = "POST", bodyParam = "entity")
    public Future<?> create(SocialRequestItem request) throws ProtocolException {        
        //{"id": "suhailski", "languagesSpoken":["EN"],"birthday":"2011-05-28T18:43:22.038Z","accounts":[{"username":"ski","userId":"email@example.com","domain":"moimoi.com","password":"password"}], "emails":[{"value":"email@example.com","type":"home"}]}
        //http://localhost:8084/social/rest/account
        //Content-Type:application/json
        //Accept:application/json        
        Person register = request.getTypedParameter("entity", SocialPerson.class);                
        Key<Person> key =  accountService.register(register);
        return ImmediateFuture.newInstance(key.toString());
    }
    
    @Operation(httpMethods = "PUT", bodyParam = "entity")
    public Future<?> update(SocialRequestItem request) throws ProtocolException {
        //{"username":"ski","userId":"email@example.com","domain":"moimoi.com","password":"password"}
        //http://localhost:8084/social/rest/account/ski
        String userId = request.getParameter("userId");
        Account account = request.getTypedParameter("entity", SocialAccount.class);        
        Boolean outcome = accountService.add(userId, account);
        return ImmediateFuture.newInstance(outcome);
    }
    
    @Operation(httpMethods = "DELETE", bodyParam = "entity", path = "/@account")
    public Future<?> deleteAccount(SocialRequestItem request) throws ProtocolException {        
        Account account = request.getTypedParameter("entity", SocialAccount.class);  
        return ImmediateFuture.newInstance(account);
    }
    
    @Operation(httpMethods = "GET", path = "/@supportedFields")
    public List<Object> supportedFields(RequestItem request) {
        String container = Objects.firstNonNull(request.getToken().getContainer(), ContainerConfig.DEFAULT_CONTAINER);
        return config.getList(container,"${Cur['gadgets.features'].opensocial.supportedFields.account}");
    }
}
