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
package org.cementframework.querybyproxy.shared.impl.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.cementframework.querybyproxy.shared.api.model.OrderByClause;
import org.cementframework.querybyproxy.shared.api.model.sorts.QuerySort;

/**
 * Represents a "order by" clause.
 *
 * @author allenparslow
 */
public class OrderByClauseImpl implements OrderByClause {

    private static final long serialVersionUID = 1824682795038233604L;
    private final List<QuerySort> sorts;

    /**
     * Create a new <code>OrderByImpl</code> instance.
     */
    public OrderByClauseImpl() {
        this.sorts = Collections.unmodifiableList(new ArrayList<QuerySort>());
    }

    /**
     * Create a new <code>OrderByImpl</code> instance.
     *
     * @param sorts
     *            the sorts for the order-by-clause.
     */
    public OrderByClauseImpl(List<QuerySort> sorts) {
        this.sorts = Collections.unmodifiableList(sorts);
    }

    /**
     * {@inheritDoc}
     */
    public List<QuerySort> getSorts() {
        return sorts;
    }
}
