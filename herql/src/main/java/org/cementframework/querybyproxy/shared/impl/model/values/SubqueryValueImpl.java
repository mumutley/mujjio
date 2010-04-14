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
package org.cementframework.querybyproxy.shared.impl.model.values;

import org.cementframework.querybyproxy.shared.api.model.FromClause;
import org.cementframework.querybyproxy.shared.api.model.GroupByClause;
import org.cementframework.querybyproxy.shared.api.model.HavingClause;
import org.cementframework.querybyproxy.shared.api.model.OrderByClause;
import org.cementframework.querybyproxy.shared.api.model.SelectClause;
import org.cementframework.querybyproxy.shared.api.model.WhereClause;
import org.cementframework.querybyproxy.shared.api.model.values.Subquery;

/**
 * Represents a subquery.
 *
 * @author allenparslow
 * @param <T>
 *            type-inference
 */
public class SubqueryValueImpl<T> extends AbstractQueryValue<T> implements Subquery<T> {

    private static final long serialVersionUID = 6514797163134678339L;
    private final SelectClause select;
    private final FromClause from;
    private final WhereClause where;
    private final GroupByClause groupBy;
    private final HavingClause having;
    private final OrderByClause orderBy;

    /**
     * Create a new <code>SubqueryImpl</code> instance.
     *
     * @param select
     *            the select clause.
     * @param from
     *            the from clause.
     * @param where
     *            the where clause.
     * @param groupBy
     *            the groupBy clause.
     * @param having
     *            the having clause.
     * @param orderBy
     *            the orderBy clause.
     */
    public SubqueryValueImpl(SelectClause select, FromClause from, WhereClause where,
            GroupByClause groupBy, HavingClause having, OrderByClause orderBy) {
        this.select = select;
        this.from = from;
        this.where = where;
        this.groupBy = groupBy;
        this.having = having;
        this.orderBy = orderBy;
    }

    /**
     * {@inheritDoc}
     */
    public SelectClause getSelect() {
        return select;
    }

    /**
     * {@inheritDoc}
     */
    public FromClause getFrom() {
        return from;
    }

    /**
     * {@inheritDoc}
     */
    public WhereClause getWhere() {
        return where;
    }

    /**
     * {@inheritDoc}
     */
    public HavingClause getHaving() {
        return having;
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
    public GroupByClause getGroupBy() {
        return groupBy;
    }
}
