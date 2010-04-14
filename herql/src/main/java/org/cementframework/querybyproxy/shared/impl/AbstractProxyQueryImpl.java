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
package org.cementframework.querybyproxy.shared.impl;

import static org.cementframework.querybyproxy.shared.impl.StaticProxyQueryBuilder.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.cementframework.querybyproxy.shared.api.DynamicQuery;
import org.cementframework.querybyproxy.shared.api.ProxyQuery;
import org.cementframework.querybyproxy.shared.api.TypedQuery;
import org.cementframework.querybyproxy.shared.api.model.FromClause;
import org.cementframework.querybyproxy.shared.api.model.GroupByClause;
import org.cementframework.querybyproxy.shared.api.model.HavingClause;
import org.cementframework.querybyproxy.shared.api.model.OrderByClause;
import org.cementframework.querybyproxy.shared.api.model.SelectClause;
import org.cementframework.querybyproxy.shared.api.model.WhereClause;
import org.cementframework.querybyproxy.shared.api.model.conditionals.Conditional;
import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoin;
import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoinModifier;
import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoinType;
import org.cementframework.querybyproxy.shared.api.model.selections.Selection;
import org.cementframework.querybyproxy.shared.api.model.sorts.QuerySort;
import org.cementframework.querybyproxy.shared.api.model.sorts.QuerySortOperator;
import org.cementframework.querybyproxy.shared.api.model.values.StrictQueryValue;
import org.cementframework.querybyproxy.shared.api.model.values.Subquery;
import org.cementframework.querybyproxy.shared.impl.model.FromClauseImpl;
import org.cementframework.querybyproxy.shared.impl.model.GroupByClauseImpl;
import org.cementframework.querybyproxy.shared.impl.model.HavingClauseImpl;
import org.cementframework.querybyproxy.shared.impl.model.OrderByClauseImpl;
import org.cementframework.querybyproxy.shared.impl.model.SelectClauseImpl;
import org.cementframework.querybyproxy.shared.impl.model.WhereClauseImpl;
import org.cementframework.querybyproxy.shared.impl.model.joins.PathJoinImpl;
import org.cementframework.querybyproxy.shared.impl.model.joins.RootJoinImpl;
import org.cementframework.querybyproxy.shared.impl.model.joins.ThetaJoinImpl;
import org.cementframework.querybyproxy.shared.impl.model.selections.ConstructorValueImpl;
import org.cementframework.querybyproxy.shared.impl.model.selections.SelectProxyImpl;
import org.cementframework.querybyproxy.shared.impl.model.sorts.DirectionalQuerySortImpl;
import org.cementframework.querybyproxy.shared.impl.model.values.ConditionalExpressionCallback;
import org.cementframework.querybyproxy.shared.impl.model.values.ProxyPathExpressionImpl;
import org.cementframework.querybyproxy.shared.impl.model.values.RedirectingQueryValue;
import org.cementframework.querybyproxy.shared.impl.model.values.SubqueryValueImpl;
import org.cementframework.recordingproxy.api.RecordedMethodCall;
import org.cementframework.recordingproxy.api.RecordingSessions;
import org.cementframework.recordingproxy.impl.MethodCallUtils;

/**
 * The primary entry point into creating by-proxy criteria queries.
 *
 * @author allenparslow
 * @param <T>
 *            the root type of the query (may be effectively modified by calling
 *            select(proxy)).
 */
public abstract class AbstractProxyQueryImpl<T>
        implements ProxyQuery<T>, ConditionalExpressionCallback {

    private static final long serialVersionUID = 1260820008360638062L;
    private final T proxy;
    private List<QueryJoin> joins = new ArrayList<QueryJoin>();
    private List<Conditional<?>> whereExpressions = new ArrayList<Conditional<?>>();
    private List<Conditional<?>> havingExpressions = new ArrayList<Conditional<?>>();

    private DynamicQuery query;

    private SelectClause select;
    private GroupByClause groupBy;
    private OrderByClause orderBy;

    /**
     * Create a new instance.
     *
     * @param proxy
     *            the target proxy.
     * @param rootProxyClass
     *            the target proxy's entity class.
     */
    public AbstractProxyQueryImpl(
            T proxy,
            Class<?> rootProxyClass) {
        this.proxy = proxy;

        select = new SelectClauseImpl(false);
        this.groupBy = new GroupByClauseImpl();
        this.orderBy = new OrderByClauseImpl();

        joins.add(new RootJoinImpl(rootProxyClass, proxy));
    }

    /**
     * Create a new <strong>correlated subquery</strong> instance.
     *
     * @param proxy
     *            the target proxy. associate collection property.
     * @param join
     *            the root query join for the correlated subquery.
     */
    public AbstractProxyQueryImpl(
            T proxy,
            QueryJoin join) {
        this.proxy = proxy;

        select = new SelectClauseImpl(false);
        this.groupBy = new GroupByClauseImpl();
        this.orderBy = new OrderByClauseImpl();

        joins.add(join);
    }

    /**
     * {@inheritDoc}
     */
    public AbstractProxyQueryImpl<T> distinct() {

        setSelect(new SelectClauseImpl(getSelect(), true));

        RecordingSessions.get().clear();

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public AbstractProxyQueryImpl<T> groupBy(Object... selections) {

        List<Selection> selects = new ArrayList<Selection>();
        for (Object obj : selections) {
            selects.add(createQueryValue(obj));
        }
        groupBy = new GroupByClauseImpl(selects);

        RecordingSessions.get().clear();

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public AbstractProxyQueryImpl<T> orderBy(Object... sorts) {
        List<QuerySort> list = new ArrayList<QuerySort>();
        for (Object sortObject : sorts) {
            if (sortObject == null) {
                DirectionalQuerySortImpl sort = new DirectionalQuerySortImpl(
                        createQueryValue(null), QuerySortOperator.ASC);
                list.add(sort);
            } else if (sortObject instanceof DirectionalQuerySortImpl) {
                list.add((QuerySort) sortObject);
            } else if (sortObject instanceof Selection) {
                list.add(new DirectionalQuerySortImpl((Selection) sortObject,
                        QuerySortOperator.ASC));
            }
        }
        orderBy = new OrderByClauseImpl(list);

        RecordingSessions.get().clear();

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <S> TypedQuery<S> select(Class<S> constructorClass, Object... constructorArguments) {
        if (constructorClass == null) {
            RecordingSessions.get().clear();
            throw new IllegalArgumentException("targetClass is required");
        }

        List<StrictQueryValue> arugments =
                new ArrayList<StrictQueryValue>(constructorArguments.length);
        for (Object object : constructorArguments) {
            arugments.add(createQueryValue(object));
        }

        TypedQuery<S> typedQuery = select(new ConstructorValueImpl<S>(constructorClass, arugments));

        RecordingSessions.get().clear();

        return typedQuery;
    }

    /**
     * {@inheritDoc}
     */
    public <S> TypedQuery<S> select(S selection) {

        TypedQuery<S> typedQuery = select(get(selection));

        RecordingSessions.get().clear();

        return typedQuery;
    }

    /**
     * {@inheritDoc}
     */
    public DynamicQuery select(Object... selections) {
        List<Selection> selects = new ArrayList<Selection>();
        for (Object obj : selections) {
            selects.add(createQueryValue(obj));
        }
        setSelect(new SelectClauseImpl(selects, getSelect().isDistinct()));

        setQuery(createDynamicQuery());

        DynamicQuery dynamicQuery = getQuery();

        RecordingSessions.get().clear();

        return dynamicQuery;
    }

    /**
     * Creates a new dynamic query (one that returns rows of type Object[]).
     *
     * @return a new dynamic query.
     */
    public abstract DynamicQuery createDynamicQuery();

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <S> RedirectingQueryValue<S> orWhere(S recordedMethodCall) {
        or();

        RedirectingQueryValue value =
                new RedirectingQueryValue<S>(createQueryValue(recordedMethodCall), this);

        RecordingSessions.get().clear();

        return value;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <S> RedirectingQueryValue<S> andWhere(S recordedMethodCall) {

        RedirectingQueryValue value =
                new RedirectingQueryValue<S>(createQueryValue(recordedMethodCall), this);

        RecordingSessions.get().clear();

        return value;
    }

    /**
     * {@inheritDoc}
     */
    public AbstractProxyQueryImpl<T> where(Conditional<?>... conditionalExpressions) {
        this.whereExpressions = Arrays.asList(conditionalExpressions);

        RecordingSessions.get().clear();

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public AbstractProxyQueryImpl<T> having(Conditional<?>... conditionalExpressions) {
        havingExpressions = Arrays.asList(conditionalExpressions);

        RecordingSessions.get().clear();

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public <S> Subquery<S> subquery(StrictQueryValue<S> value) {

        List<Selection> list = new ArrayList<Selection>();
        list.add(value);

        setSelect(new SelectClauseImpl(list, getSelect().isDistinct()));

        SubqueryValueImpl<S> subquery = new SubqueryValueImpl<S>(
                getSelect(),
                getFrom(),
                getWhere(),
                getGroupBy(),
                getHaving(),
                getOrderBy());

        RecordingSessions.get().clear();

        return subquery;
    }

    /**
     * {@inheritDoc}
     */
    public <S> Subquery<S> subquery(S value) {
        return subquery(createQueryValue(value));
    }

    /**
     * {@inheritDoc}
     */
    public Subquery<T> subquery() {

        List<Selection> list = new ArrayList<Selection>();
        list.add(new SelectProxyImpl(getRootProxy()));

        setSelect(new SelectClauseImpl(list, getSelect().isDistinct()));

        SubqueryValueImpl<T> subquery = new SubqueryValueImpl<T>(
                getSelect(),
                getFrom(),
                getWhere(),
                getGroupBy(),
                getHaving(),
                getOrderBy());

        RecordingSessions.get().clear();

        return subquery;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> find() {
        return select().find();
    }

    /**
     * {@inheritDoc}
     */
    public T findSingleResult() {
        return select().findSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public TypedQuery<T> select() {

        List<Selection> list = new ArrayList<Selection>();
        list.add(new SelectProxyImpl(getRootProxy()));

        setSelect(new SelectClauseImpl(list, getSelect().isDistinct()));

        TypedQuery<T> typedQuery = createTypedQuery();

        RecordingSessions.get().clear();

        return typedQuery;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <S> TypedQuery<S> select(StrictQueryValue<S> value) {

        List<Selection> list = new ArrayList<Selection>();
        list.add(value);

        setSelect(new SelectClauseImpl(list, getSelect().isDistinct()));

        TypedQuery<S> typedQuery = createTypedQuery();

        RecordingSessions.get().clear();

        return typedQuery;
    }

    @SuppressWarnings("unchecked")
    protected abstract TypedQuery createTypedQuery();

    /**
     * {@inheritDoc}
     */
    public SelectClause getSelect() {
        return select;
    }

    protected void setSelect(SelectClause select) {
        this.select = select;
    }

    /**
     * {@inheritDoc}
     */
    public FromClause getFrom() {
        return new FromClauseImpl(joins);
    }

    /**
     * {@inheritDoc}
     */
    public WhereClause getWhere() {
        return new WhereClauseImpl(whereExpressions);
    }

    /**
     * {@inheritDoc}
     */
    public GroupByClause getGroupBy() {
        return groupBy;
    }

    /**
     * {@inheritDoc}
     */
    public HavingClause getHaving() {
        return new HavingClauseImpl(havingExpressions);
    }

    /**
     * {@inheritDoc}
     */
    public OrderByClause getOrderBy() {
        return orderBy;
    }

    /**
     * {@inheritDoc}
     */
    public T getRootProxy() {
        return proxy;
    }

    protected DynamicQuery getQuery() {
        return query;
    }

    protected void setQuery(DynamicQuery query) {
        this.query = query;
    }

    /**
     * {@inheritDoc}
     */
    public DynamicQuery build() {
        if (query == null) {
            RecordingSessions.get().clear();
            throw new IllegalStateException("select() was not called");
        }

        RecordingSessions.get().clear();

        return query;
    }

    @SuppressWarnings("unchecked")
    <C> C join(
            Collection<C> collectionProperty,
            QueryJoinType joinType,
            QueryJoinModifier joinModifier) {

        RecordedMethodCall call = RecordingSessions.get().getSafeLastCall();

        C joinProxy = (C) MethodCallUtils.proxy(call.getElementType());

        QueryJoin join = new PathJoinImpl(
                joinType,
                joinModifier,
                joinProxy,
                call);

        joins.add(join);

        RecordingSessions.get().clear();

        return joinProxy;
    }

    /**
     * {@inheritDoc}
     */
    public <C> C join(Collection<C> collectionProperty) {
        return join(collectionProperty, QueryJoinType.INNER, QueryJoinModifier.NONE);
    }

    /**
     * {@inheritDoc}
     */
    public <C> C joinFetch(Collection<C> collectionProperty) {
        return join(collectionProperty, QueryJoinType.INNER, QueryJoinModifier.FETCH);
    }

    /**
     * {@inheritDoc}
     */
    public <C> C leftJoin(Collection<C> collectionProperty) {
        return join(collectionProperty, QueryJoinType.LEFT, QueryJoinModifier.NONE);
    }

    /**
     * {@inheritDoc}
     */
    public <C> C rightJoin(Collection<C> collectionProperty) {
        return join(collectionProperty, QueryJoinType.RIGHT, QueryJoinModifier.NONE);
    }

    @SuppressWarnings("unchecked")
    private <J> J join(
            StrictQueryValue<J> targetQueryValue,
            QueryJoinType joinType,
            QueryJoinModifier joinModifier) {

        if (!(targetQueryValue instanceof ProxyPathExpressionImpl)) {
            RecordingSessions.get().clear();
            throw new IllegalArgumentException("Invalid Join Argument, expected: "
                    + ProxyPathExpressionImpl.class.getSimpleName());
        }
        ProxyPathExpressionImpl targetProperty = (ProxyPathExpressionImpl) targetQueryValue;

        J joinProxy = (J) MethodCallUtils.proxy(
                targetProperty.getCall().getElementType());

        QueryJoin join = new PathJoinImpl(
                joinType,
                joinModifier,
                joinProxy,
                targetProperty.getCall());

        joins.add(join);

        RecordingSessions.get().clear();

        return joinProxy;
    }

    /**
     * {@inheritDoc}
     */
    public <J> J join(J targetProperty) {
        return join(get(targetProperty), QueryJoinType.INNER, QueryJoinModifier.NONE);
    }

    /**
     * {@inheritDoc}
     */
    public <J> J joinFetch(J targetProperty) {
        return join(get(targetProperty), QueryJoinType.INNER, QueryJoinModifier.FETCH);
    }

    /**
     * {@inheritDoc}
     */
    public <J> J leftJoin(J targetProperty) {
        return join(get(targetProperty), QueryJoinType.LEFT, QueryJoinModifier.NONE);
    }

    /**
     * {@inheritDoc}
     */
    public <J> J rightJoin(J targetProperty) {
        return join(get(targetProperty), QueryJoinType.RIGHT, QueryJoinModifier.NONE);
    }

    /**
     * {@inheritDoc}
     */
    public <J> J join(StrictQueryValue<J> targetProperty) {
        return join(targetProperty, QueryJoinType.INNER, QueryJoinModifier.NONE);
    }

    /**
     * {@inheritDoc}
     */
    public <J> J joinFetch(StrictQueryValue<J> targetProperty) {
        return join(targetProperty, QueryJoinType.INNER, QueryJoinModifier.FETCH);
    }

    /**
     * {@inheritDoc}
     */
    public <J> J leftJoin(StrictQueryValue<J> targetProperty) {
        return join(targetProperty, QueryJoinType.LEFT, QueryJoinModifier.NONE);
    }

    /**
     * {@inheritDoc}
     */
    public <J> J rightJoin(StrictQueryValue<J> targetProperty) {
        return join(targetProperty, QueryJoinType.RIGHT, QueryJoinModifier.NONE);
    }

    /**
     * {@inheritDoc}
     */
    public <J> J thetaJoin(Class<J> joinClass) {
        if (joinClass == null) {
            RecordingSessions.get().clear();
            throw new IllegalArgumentException("joinClass was not specified");
        }

        J joinProxy = MethodCallUtils.proxy(joinClass);

        ThetaJoinImpl join = new ThetaJoinImpl(joinClass, joinProxy);

        joins.add(join);

        RecordingSessions.get().clear();

        return joinProxy;
    }

    /**
     * {@inheritDoc}
     */
    public void processConditional(Conditional<?> newConditional) {
        whereExpressions.add(newConditional);
    }
}
