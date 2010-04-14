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
 * Represents a binary expression used in where and having clauses.
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
public class BinaryConditionalImpl<T> extends UnaryConditionalImpl<T> {

    private static final long serialVersionUID = -336297849930081050L;
    private final StrictQueryValue<T> rightValue;

    /**
     * Create a new <code>BinaryExpressionImpl</code> instance.
     *
     * @param logicGate
     *            the logic gate (conjunction).
     * @param leftValue
     *            the left-value.
     * @param operator
     *            the operator (e.g. =,>,<).
     * @param rightvalue
     *            the right-value.
     */
    public BinaryConditionalImpl(
            LogicGate logicGate,
            StrictQueryValue<T> leftValue,
            ComparisonOperator operator,
            StrictQueryValue<T> rightvalue) {
        super(logicGate, leftValue, operator);
        this.rightValue = rightvalue;
    }

    /**
     * Gets the right-value for the expression.
     *
     * @return the right-value.
     */
    public StrictQueryValue<T> getRightValue() {
        return rightValue;
    }
}
