/*
 * Copyright 2009 the original author or authors.
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
package org.cementframework.querybyproxy.shared.api;

import org.cementframework.querybyproxy.shared.impl.ProxyQuerySessionImpl;

/**
 * Thread-local access to the state between query-builders and proxy-queries.
 *
 * @author allenparslow
 */
public class ProxyQuerySessions {
    private static final ThreadLocal<ProxyQuerySession> INSTANCE =
            new ThreadLocal<ProxyQuerySession>();

    /**
     * Gets the current state between query-builders and proxy-queries.
     *
     * @return the current proxy-query session state.
     */
    public static ProxyQuerySession get() {
        ProxyQuerySession value = INSTANCE.get();
        if (value == null) {
            value = new ProxyQuerySessionImpl();
            INSTANCE.set(value);
        }

        return value;
    }
}
