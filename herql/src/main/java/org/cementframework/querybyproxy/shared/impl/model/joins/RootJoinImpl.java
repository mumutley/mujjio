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
package org.cementframework.querybyproxy.shared.impl.model.joins;

import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoinModifier;
import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoinType;

/**
 * Represents the root join in a query.
 *
 * @author allenparslow
 */
public class RootJoinImpl extends AbstractJoin {
    private static final long serialVersionUID = 1452188122898381187L;

    private final Object proxy;
    private final Class<?> proxyClass;

    /**
     * Create a new <code>ProxyJoin</code> instance.
     *
     * @param proxy
     *            the target proxy.
     * @param proxyClass
     *            the class for the join (used for naming).
     */
    public RootJoinImpl(
            Class<?> proxyClass,
            Object proxy) {
        super(QueryJoinType.INNER, QueryJoinModifier.NONE);
        this.proxy = proxy;
        this.proxyClass = proxyClass;
    }

    /**
     * Gets the target proxy.
     *
     * @return the target proxy.
     */
    public Object getProxy() {
        return proxy;
    }

    /**
     * Gets the class for the join (used for naming).
     *
     * @return the class for the join.
     */
    public Class<?> getProxyClass() {
        return proxyClass;
    }
}
