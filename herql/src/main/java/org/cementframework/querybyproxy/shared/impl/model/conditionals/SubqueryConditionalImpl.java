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
package org.cementframework.querybyproxy.shared.impl.model.conditionals;

import org.cementframework.querybyproxy.shared.api.model.conditionals.LogicGate;
import org.cementframework.querybyproxy.shared.api.model.values.Subquery;

/**
 * Represents a subquery used in a conditional expression.
 *
 * @param <T>
 *            the type of conditional
 *
 * @author allenparslow
 */
public class SubqueryConditionalImpl<T> extends AbstractConditional<T> {

    private static final long serialVersionUID = 5418095623529534926L;
    @SuppressWarnings("unchecked")
    private final Subquery subquery;
    private final ComparisonOperator operation;

    /**
     * Create a new <code>SubqueryConditionalImpl</code> instance.
     *
     * @param logicGate
     *            the logic gate (conjunction).
     * @param operation
     *            the operation (e.g. =,>,<).
     * @param subquery
     *            the subquery
     */
    @SuppressWarnings("unchecked")
    public SubqueryConditionalImpl(
            LogicGate logicGate,
            ComparisonOperator operation,
            Subquery subquery) {
        super(logicGate);
        this.operation = operation;
        this.subquery = subquery;
    }

    /**
     * Gets the subquery for the conditional.
     *
     * @return the subquery.
     */
    @SuppressWarnings("unchecked")
    public Subquery getSubquery() {
        return subquery;
    }

    /**
     * Gets the operation for the expression.
     *
     * @return the operation.
     */
    public ComparisonOperator getOperation() {
        return operation;
    }
}
