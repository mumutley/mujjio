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
import java.util.List;

import org.cementframework.querybyproxy.shared.api.model.FromClause;
import org.cementframework.querybyproxy.shared.api.model.GroupByClause;
import org.cementframework.querybyproxy.shared.api.model.HavingClause;
import org.cementframework.querybyproxy.shared.api.model.OrderByClause;
import org.cementframework.querybyproxy.shared.api.model.QueryFragment;
import org.cementframework.querybyproxy.shared.api.model.SelectClause;
import org.cementframework.querybyproxy.shared.api.model.WhereClause;
import org.cementframework.querybyproxy.shared.api.model.conditionals.Conditional;
import org.cementframework.querybyproxy.shared.api.model.values.QueryValue;
import org.cementframework.querybyproxy.shared.api.model.values.StrictQueryValue;
import org.cementframework.querybyproxy.shared.api.model.values.Subquery;

/**
 * The primary entry point into creating by-proxy criteria queries.
 *
 * <p>
 * Represent query that allows only strictly-typed grammar as parameters for its
 * methods.
 * </p>
 *
 * @author allenparslow
 *
 * @param <T>
 *            the root type of the query (may be effectively modified by calling
 *            select(proxy)).
 */
public interface StrictQuery<T> extends QueryFragment {

    /**
     * Gets the root proxy for the query.
     *
     * @return the root proxy.
     */
    T getRootProxy();

    /**
     * Makes the current query distinct.
     *
     * @return self-reference.
     */
    StrictQuery<T> distinct();

    /**
     * Selects the root proxy.
     *
     * @return a typed query used to find results.
     */
    TypedQuery<T> select();

    /**
     * Executes the current query producing a list of objects.
     *
     * @return the resulting list of objects.
     */
    List<T> find();

    /**
     * Executes the current query producing a single object.
     *
     * @return the resulting object.
     */
    T findSingleResult();

    /**
     * Selects a singular value.
     *
     * @param <S>
     *            type inference based on recorded entity-proxy method call.
     *
     * @param selection
     *            a recorded entity-proxy method call.
     *
     * @return a typed query used to find results.
     */
    <S> TypedQuery<S> select(S selection);

    /**
     * Selects multiple values (produces Object[] row results).
     *
     * @param selections
     *            the items to select.
     *
     * @return a dynamic query used to find results (Object[]).
     */
    DynamicQuery select(Object... selections);

    /**
     * Inner joins a recorded entity-proxy collection-property method call to
     * the current from-clause.
     *
     * @param <C>
     *            type-inference.
     *
     * @param collectionProperty
     *            a recorded entity-proxy method call.
     * @return A entity-proxy to the collection property.
     */
    <C> C join(Collection<C> collectionProperty);

    /**
     * Left joins a recorded entity-proxy collection-property method call to the
     * current from-clause.
     *
     * @param <C>
     *            type-inference.
     *
     * @param collectionProperty
     *            a recorded entity-proxy method call.
     * @return A entity-proxy to the collection property.
     */
    <C> C leftJoin(Collection<C> collectionProperty);

    /**
     * Right joins a recorded entity-proxy collection-property method call to
     * the current from-clause.
     *
     * @param <C>
     *            type-inference.
     *
     * @param collectionProperty
     *            a recorded entity-proxy method call.
     * @return A entity-proxy to the collection property.
     */
    <C> C rightJoin(Collection<C> collectionProperty);

    /**
     * Fetch joins a recorded entity-proxy collection-property method call to
     * the current from-clause.
     *
     * @param <C>
     *            type-inference.
     *
     * @param collectionProperty
     *            a recorded entity-proxy method call.
     * @return A entity-proxy to the collection property.
     */
    <C> C joinFetch(Collection<C> collectionProperty);

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
    <J> J join(StrictQueryValue<J> targetProperty);

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
    <J> J leftJoin(StrictQueryValue<J> targetProperty);

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
    <J> J rightJoin(StrictQueryValue<J> targetProperty);

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
    <J> J joinFetch(StrictQueryValue<J> targetProperty);

    /**
     * Selects a singular value.
     *
     * @param <S>
     *            type inference based on <code>QueryValue</code>.
     *
     * @param value
     *            selection for the query.
     *
     * @return a typed query used to find results.
     */
    <S> TypedQuery<S> select(StrictQueryValue<S> value);

    /**
     * Create a sub-query instance using the current query.
     *
     * @param <S>
     *            The type of sub-query
     * @param value
     *            the value representing the singular return
     *            type of the subquery.
     * @return a subquery (immutable).
     */
    <S> Subquery<S> subquery(StrictQueryValue<S> value);

    /**
     * Create a sub-query instance using the current query.
     *
     * @return a subquery (immutable).
     */
    Subquery<T> subquery();

    /**
     * Builds a <code>DynamicMultiQuery</code> (Object[]) using the current
     * state of this query.
     *
     * @return a <code>DynamicMultiQuery</code>.
     */
    DynamicQuery build();

    /**
     * Gets the select-clause this query.
     *
     * @return the select-clause (immutable).
     */
    SelectClause getSelect();

    /**
     * Gets the from-clause this query.
     *
     * @return the select-clause (immutable).
     */
    FromClause getFrom();

    /**
     * Gets the where-clause this query.
     *
     * @return the where-clause (immutable).
     */
    WhereClause getWhere();

    /**
     * Gets the group-by-clause this query.
     *
     * @return the group-by-clause (immutable).
     */
    GroupByClause getGroupBy();

    /**
     * Gets the having-clause this query.
     *
     * @return the having-clause (immutable).
     */
    HavingClause getHaving();

    /**
     * Gets the order-by-clause this query.
     *
     * @return the order-by-clause (immutable).
     */
    OrderByClause getOrderBy();

    /**
     * Selects using jpql constuctor [new] syntax.
     *
     * <p>
     * The result return will by of the constructor's class type.
     * </p>
     *
     * <p>
     * The constructor class doesn't not need to be jpa entity mapped class
     * </p>
     *
     * @param <S>
     *            type inference based on constructor class.
     *
     * @param constructorClass
     *            The type of data-transfer-object that will be produced.
     * @param constructorArguments
     *            Constructor arguments (must match a constructor signature for
     *            the constructor-class).
     *
     * @return a typed query used to find results.
     */
    <S> TypedQuery<S> select(Class<S> constructorClass, Object... constructorArguments);

    /**
     * Add a single current where-clause with the specified conditional
     * expression.
     *
     * <p>
     * Added to any current conditionals in the where clause
     * </p>
     * <p>
     * May be called multiple times to form a complete where clause
     * </p>
     *
     * <p>
     * NOTE: all methods on the resulting alternate query-value form return void
     * to prevent usage out side the current query to which it is re-directing
     * query-value calls to.
     * </p>
     *
     * @param recordedMethodCall
     *            the conditional-expressions to filter with.
     * @return a query-value (where all methods return void).
     * @param <S>
     *            type-inference
     */
    <S> QueryValue<S> andWhere(S recordedMethodCall);

    /**
     * Add a single current "or" where with the specified conditional
     * expression.
     *
     * <p>
     * Added to any current conditionals in the where clause
     * </p>
     * <p>
     * May be called multiple times to form a complete where clause
     * </p>
     *
     * <p>
     * NOTE: all methods on the resulting alternate query-value form return void
     * to prevent usage out side the current query to which it is re-directing
     * query-value calls to.
     * </p>
     *
     * @param recordedMethodCall
     *            the conditional-expressions to filter with.
     * @return a query-value (where all methods return void).
     * @param <S>
     *            type-inference
     */
    <S> QueryValue<S> orWhere(S recordedMethodCall);

    /**
     * Replaces the current where-clause with the specified conditional
     * expression.
     *
     * @param conditionalExpressions
     *            the conditional-expressions to filter with.
     * @return self-reference.
     */
    StrictQuery<T> where(Conditional<?>... conditionalExpressions);

    /**
     * Replaces the current group-by-clause with the specified selections.
     *
     * @param selections
     *            the selections to group with.
     * @return self-reference.
     */
    StrictQuery<T> groupBy(Object... selections);

    /**
     * Replaces the current having-clause with the specified conditional
     * expressions.
     *
     * @param conditionalExpressions
     *            the conditional-expressions to filter with.
     * @return self-reference.
     */
    StrictQuery<T> having(Conditional<?>... conditionalExpressions);

    /**
     * Replaces the current order-by-clause with the specified sorts.
     *
     * @param sorts
     *            the sorts to order the query.
     * @return self-reference.
     */
    StrictQuery<T> orderBy(Object... sorts);
}
