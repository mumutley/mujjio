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
package org.cementframework.querybyproxy.shared.impl.model.selections;

import org.cementframework.querybyproxy.shared.api.model.values.StrictQueryValue;
import org.cementframework.querybyproxy.shared.impl.model.values.AbstractQueryValue;

/**
 * Represents an aggregate (count, min, max, sum, avg) selection.
 *
 * @author allenparslow
 * @param <T>
 *            the type of selection
 */
public class AggregateSelectionImpl<T> extends AbstractQueryValue<T> {

    private static final long serialVersionUID = 7645374447181361628L;
    private final StrictQueryValue<T> target;
    private final QueryAggregate aggregate;

    /**
     * Create a new <code>AggregateSelection</code> instance.
     *
     * @param aggregate
     *            the aggregate type (count, min, max, sum, avg)
     * @param target
     *            the target value
     */
    public AggregateSelectionImpl(
            QueryAggregate aggregate,
            StrictQueryValue<T> target) {
        this.target = target;
        this.aggregate = aggregate;
    }

    /**
     * Gets the target expression for the aggregate.
     *
     * @return the target expression.
     */
    public StrictQueryValue<T> getTarget() {
        return target;
    }

    /**
     * Gets the aggregate function.
     *
     * @return the aggregate function.
     */
    public QueryAggregate getAggregate() {
        return aggregate;
    }
}
