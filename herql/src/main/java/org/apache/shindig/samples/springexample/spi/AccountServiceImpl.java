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
package org.apache.shindig.samples.springexample.spi;

import me.moimoi.social.herql.services.AccountService;
import org.apache.shindig.social.opensocial.model.Account;

/**
 *
 * @author suhail
 */
public class AccountServiceImpl implements AccountService {

    @Override
    public void register(Account account) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Account find(String userId, String domain) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
