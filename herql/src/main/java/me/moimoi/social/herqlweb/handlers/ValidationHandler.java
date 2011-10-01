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
package me.moimoi.social.herqlweb.handlers;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import me.moimoi.social.herql.services.SocialPersonService;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.protocol.Operation;
import org.apache.shindig.protocol.RequestItem;
import org.apache.shindig.protocol.Service;

/**
 *
 * @author suhail
 */
@Service(name = "validator")
public class ValidationHandler {

    private final SocialPersonService accounts;
    private final String defaultDomain;
    
    @Inject
    public ValidationHandler(final SocialPersonService accounts, @Named("default.domain") String domain) {
        this.accounts = accounts;
        this.defaultDomain = domain;
    }
    
    @Operation(httpMethods = "GET", path = "/email")
    public Future<?>  validateRegistration(RequestItem request) {        
        Boolean found = accounts.exists(request.getParameter(ValidationHandler.EMAIL));        
        return ImmediateFuture.newInstance(!found);
    }
    
    private static final String EMAIL = "email";
    private static final Logger LOG = Logger.getLogger(ValidationHandler.class.getCanonicalName());
}
