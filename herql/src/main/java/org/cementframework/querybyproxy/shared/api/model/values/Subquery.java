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
package org.cementframework.querybyproxy.shared.api.model.values;

import org.cementframework.querybyproxy.shared.api.model.FromClause;
import org.cementframework.querybyproxy.shared.api.model.GroupByClause;
import org.cementframework.querybyproxy.shared.api.model.HavingClause;
import org.cementframework.querybyproxy.shared.api.model.OrderByClause;
import org.cementframework.querybyproxy.shared.api.model.SelectClause;
import org.cementframework.querybyproxy.shared.api.model.WhereClause;

/**
 * Represents a subquery with a singular select.
 *
 * @author allenparslow
 *
 * @param <T>
 */
public interface Subquery<T> extends QueryValue<T> {

    /**
     * Gets the select-clause for the subquery.
     *
     * @return the select-clause.
     */
    SelectClause getSelect();

    /**
     * Gets the from-clause for the subquery.
     *
     * @return the from-clause.
     */
    FromClause getFrom();

    /**
     * Gets the where-clause for the subquery.
     *
     * @return the where-clause.
     */
    WhereClause getWhere();

    /**
     * Gets the group-by-clause for the subquery.
     *
     * @return the group-by-clause.
     */
    GroupByClause getGroupBy();

    /**
     * Gets the having-clause for the subquery.
     *
     * @return the having-clause.
     */
    HavingClause getHaving();

    /**
     * Gets the order-by-clause for the subquery.
     *
     * @return the order-by-clause.
     */
    OrderByClause getOrderBy();
}
