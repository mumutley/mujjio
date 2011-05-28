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

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.List;
import java.util.concurrent.Future;
import me.moimoi.social.herql.domain.SocialAccount;
import me.moimoi.social.herql.services.AccountService;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.config.ContainerConfig;
import org.apache.shindig.protocol.Operation;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.protocol.RequestItem;
import org.apache.shindig.protocol.Service;
import org.apache.shindig.social.opensocial.model.Account;
import org.apache.shindig.social.opensocial.service.SocialRequestItem;

/**
 *
 * @author suhail
 */
@Service(name = "account", path = "/{userId}/{domain}")
public class AccountHandler {

    private final AccountService accountService;
    private final ContainerConfig config;

    @Inject
    public AccountHandler(AccountService accountService, ContainerConfig config) {
        this.accountService = accountService;
        this.config = config;
    }

    @Operation(httpMethods = "GET")
    public Future<?> get(SocialRequestItem request) throws ProtocolException {
        Account account = SocialAccount.create("suhail", "ski", "moimoi", "password");
        return ImmediateFuture.newInstance(account);
    }

    @Operation(httpMethods = "GET", path = "/@supportedFields")
    public List<Object> supportedFields(RequestItem request) {
        String container = Objects.firstNonNull(request.getToken().getContainer(), ContainerConfig.DEFAULT_CONTAINER);
        return config.getList(container,"${Cur['gadgets.features'].opensocial.supportedFields.account}");
    }
}
