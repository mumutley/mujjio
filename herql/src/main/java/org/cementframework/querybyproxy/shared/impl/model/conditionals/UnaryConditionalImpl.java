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
import org.cementframework.querybyproxy.shared.api.model.values.StrictQueryValue;

/**
 * Represents a unary expression used in where and having clauses.
 *
 * <p>
 * General form: [left-path or value] [operator]
 * </p>
 * <p>
 * Example: a.id is null
 * </p>
 *
 * @author allenparslow
 *
 * @param <T>
 *            the type of conditional
 */
public class UnaryConditionalImpl<T> extends AbstractConditional<T> {
    private static final long serialVersionUID = -9081395780135811974L;
    private final ComparisonOperator operation;
    private final StrictQueryValue<T> leftValue;

    /**
     * Create a new <code>UnaryExpressionImpl</code> instance.
     *
     * @param logicGate
     *            the logic gate (conjunction).
     * @param leftValue
     *            the left-value.
     * @param operator
     *            the operator (e.g. =,>,<).
     */
    public UnaryConditionalImpl(
            LogicGate logicGate,
            StrictQueryValue<T> leftValue,
            ComparisonOperator operator) {
        super(logicGate);
        this.operation = operator;
        this.leftValue = leftValue;
    }

    /**
     * Gets the left-value for the expression.
     *
     * @return the left-value.
     */
    public StrictQueryValue<T> getLeftValue() {
        return leftValue;
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
