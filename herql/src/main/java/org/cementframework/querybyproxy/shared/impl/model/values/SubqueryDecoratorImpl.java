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
 * Represents a subquery wrapper allowing for the application of any/all/some
 * modifiers.
 *
 * @author allenparslow
 * @param <T>
 *            type-inference
 */
public class SubqueryDecoratorImpl<T> extends AbstractQueryValue<T> implements Subquery<T> {

    private static final long serialVersionUID = -972773825224392604L;
    private final Subquery<T> subquery;
    private final SubqueryOperator subqueryOperator;

    /**
     * Create a new <code>SubqueryWrapperImpl</code> instance.
     *
     * @param subqueryOperator
     *            the any/all/some modifier.
     * @param subquery
     *            the subquery
     */
    public SubqueryDecoratorImpl(
            SubqueryOperator subqueryOperator,
            Subquery<T> subquery) {
        this.subquery = subquery;
        this.subqueryOperator = subqueryOperator;
    }

    /**
     * {@inheritDoc}
     */
    public FromClause getFrom() {
        return subquery.getFrom();
    }

    /**
     * {@inheritDoc}
     */
    public GroupByClause getGroupBy() {
        return subquery.getGroupBy();
    }

    /**
     * {@inheritDoc}
     */
    public HavingClause getHaving() {
        return subquery.getHaving();
    }

    /**
     * {@inheritDoc}
     */
    public OrderByClause getOrderBy() {
        return subquery.getOrderBy();
    }

    /**
     * {@inheritDoc}
     */
    public SelectClause getSelect() {
        return subquery.getSelect();
    }

    /**
     * {@inheritDoc}
     */
    public WhereClause getWhere() {
        return subquery.getWhere();
    }

    /**
     * Gets the subquery that is being decorated.
     *
     * @return the associated subquery
     */
    public Subquery<T> getSubquery() {
        return subquery;
    }

    /**
     * Gets the operation that is decorating the subquery.
     *
     * @return the subquery operator.
     */
    public SubqueryOperator getSubqueryOperator() {
        return subqueryOperator;
    }
}
