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

import java.util.Collection;

/**
 * Creates new <code>ProxyQuery</code> and <code>ProxyQueryBuilder</code>
 * instances.
 *
 * @author allenparslow
 */
public interface ProxyQueryFactory {

    /**
     * Gets the query-builder used to create query fragments.
     *
     * @return the query-builder
     */
    ProxyQueryBuilder getQueryBuilder();

    /**
     * Create a new query-by-proxy instance with the specified root
     * target-class.
     *
     * @param <T>
     *            the type of query
     * @param targetClass
     *            the root target-class..
     *
     * @return a new <code>ProxyQuery</code> instance.
     */
    <T> ProxyQuery<T> createQuery(Class<T> targetClass);

    /**
     * Create a new query-by-proxy instance with the specified root
     * target-class.
     *
     * @param <T>
     *            the type of query
     *
     * @param methodCall
     *            a method call whose result will be the root subquery.
     *
     * @return a new <code>ProxyQuery</code> instance.
     */
    <T> ProxyQuery<T> correlatedSubquery(Collection<T> methodCall);
}
