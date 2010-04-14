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
 * Represents a between expression used in where and having clauses.
 *
 * <p>
 * General form: [left-path/value] [operator] [right-path/value]
 * </p>
 * <p>
 * Example: a.id = ?
 * </p>
 *
 * @author allenparslow
 *
 * @param <T>
 *            the type of conditional
 */
public class BetweenConditionalImpl<T> extends UnaryConditionalImpl<T> {

    private static final long serialVersionUID = -4240573421497940499L;
    private final StrictQueryValue<T> lowerBound;
    private final StrictQueryValue<T> upperBound;

    /**
     * Create a new <code>BinaryExpressionImpl</code> instance.
     *
     * @param logicGate
     *            the logic gate (conjunction).
     * @param leftValue
     *            the left-value.
     * @param operator
     *            the operator (e.g. =,>,<).
     * @param lowerBound
     *            the lower bound for the expression.
     * @param upperBound
     *            the upper bound for the expression.
     */
    public BetweenConditionalImpl(
            LogicGate logicGate,
            StrictQueryValue<T> leftValue,
            ComparisonOperator operator,
            StrictQueryValue<T> lowerBound,
            StrictQueryValue<T> upperBound) {
        super(logicGate, leftValue, operator);
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * Gets the lower bound for the expression.
     *
     * @return the lower bound for the expression.
     */
    public StrictQueryValue<T> getLowerBound() {
        return lowerBound;
    }

    /**
     * Gets the upper bound for the expression.
     *
     * @return the upper bound for the expression.
     */
    public StrictQueryValue<T> getUpperBound() {
        return upperBound;
    }
}
