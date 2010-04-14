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

import org.cementframework.querybyproxy.shared.api.model.conditionals.Conditional;
import org.cementframework.querybyproxy.shared.api.model.values.Subquery;

/**
 * The primary entry point into creating by-proxy criteria queries.
 *
 * @author allenparslow
 *
 * @param <T>
 *            the root type of the query (may be effectively modified by calling
 *            select(proxy)).
 */
public interface ProxyQuery<T> extends StrictQuery<T> {

    /**
     * Makes the current query distinct.
     *
     * @return self-reference.
     */
    ProxyQuery<T> distinct();

    /**
     * Create a sub-query instance using the current query.
     *
     * @param <S>
     *            The type of sub-query
     * @param value
     *            the value or recorded method call representing the singular return
     *            type of the subquery.
     * @return a subquery (immutable).
     */
    <S> Subquery<S> subquery(S value);

    /**
     * Inner joins a recorded entity-proxy nested-property method call to the
     * current from-clause.
     *
     * @param <J>
     *            type-inference.
     * @param targetProperty
     *            a recorded entity-proxy method call.
     * @return A entity-proxy to the nested property.
     */
    <J> J join(J targetProperty);

    /**
     * Fetch joins a recorded entity-proxy nested-property method call to the
     * current from-clause.
     *
     * @param <J>
     *            type-inference.
     * @param targetProperty
     *            a recorded entity-proxy method call.
     * @return A entity-proxy to the nested property.
     */
    <J> J joinFetch(J targetProperty);

    /**
     * Left joins a recorded entity-proxy nested-property method call to the
     * current from-clause.
     *
     * @param <J>
     *            type-inference.
     * @param targetProperty
     *            a recorded entity-proxy method call.
     * @return A entity-proxy to the nested property.
     */
    <J> J leftJoin(J targetProperty);

    /**
     * Right joins a recorded entity-proxy nested-property method call to the
     * current from-clause.
     *
     * @param <J>
     *            type-inference.
     * @param targetProperty
     *            a recorded entity-proxy method call.
     * @return A entity-proxy to the nested property.
     */
    <J> J rightJoin(J targetProperty);

    /**
     * Theta (non-foreign-key-relationship) joins a recorded entity-proxy
     * nested-property method call to the current from-clause.
     *
     * @param <J>
     *            type-inference.
     * @param joinClass
     *            The entity-class for the property (NOTE: do not call
     *            proxy.getClass(), use SomeEntity.class as the proxy's class is
     *            derived).
     *
     * @return A entity-proxy (NOTE: use this entity-proxy rather than the
     *         passed proxy because it is a new instance and will not alias
     *         currently otherwise).
     */
    <J> J thetaJoin(Class<J> joinClass);

    /**
     * Replaces the current where-clause with the specified conditional
     * expression.
     *
     * @param conditionalExpressions
     *            the conditional-expressions to filter with.
     * @return self-reference.
     */
    ProxyQuery<T> where(Conditional<?>... conditionalExpressions);

    /**
     * Replaces the current group-by-clause with the specified selections.
     *
     * @param selections
     *            the selections to group with.
     * @return self-reference.
     */
    ProxyQuery<T> groupBy(Object... selections);

    /**
     * Replaces the current having-clause with the specified conditional
     * expressions.
     *
     * @param conditionalExpressions
     *            the conditional-expressions to filter with.
     * @return self-reference.
     */
    ProxyQuery<T> having(Conditional<?>... conditionalExpressions);

    /**
     * Replaces the current order-by-clause with the specified sorts.
     *
     * @param sorts
     *            the sorts to order the query.
     * @return self-reference.
     */
    ProxyQuery<T> orderBy(Object... sorts);

    /**
     * Gets the query factory that created the query.
     *
     * @return the query factory
     */
    ProxyQueryFactory getQueryFactory();
}
