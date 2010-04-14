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
package org.cementframework.querybyproxy.shared.impl.model.sorts;

import org.cementframework.querybyproxy.shared.api.model.selections.Selection;
import org.cementframework.querybyproxy.shared.api.model.sorts.QuerySort;
import org.cementframework.querybyproxy.shared.api.model.sorts.QuerySortOperator;

/**
 * Represents a query-sort for an order-by clause.
 *
 * @author allenparslow
 */
public class DirectionalQuerySortImpl implements QuerySort {
    private static final long serialVersionUID = 835899206539917324L;
    private final QuerySortOperator sortOperator;
    private final Selection selection;

    /**
     * Create a new <code>DirectionalQuerySort</code> instance.
     *
     * @param selection
     *            the target selection (e.g. a.id)
     * @param sortOperator
     *            the sort operator (asc/desc)
     */
    public DirectionalQuerySortImpl(Selection selection, QuerySortOperator sortOperator) {
        this.selection = selection;
        this.sortOperator = sortOperator;
    }

    /**
     * Gets the sort operator (asc/desc).
     *
     * @return the sort operator.
     */
    public QuerySortOperator getSortOperator() {
        return sortOperator;
    }

    /**
     * Gets the target selection.
     *
     * @return the target selection.
     */
    public Selection getSelection() {
        return selection;
    }

}
