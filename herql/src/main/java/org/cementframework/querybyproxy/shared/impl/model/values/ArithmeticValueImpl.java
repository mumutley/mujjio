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

import org.cementframework.querybyproxy.shared.api.model.values.StrictQueryValue;

/**
 * Representation of a arithmetic expression value.
 *
 * @author allenparslow
 * @param <T>
 *            the type of value.
 */
public class ArithmeticValueImpl<T> extends AbstractQueryValue<T> {

    private static final long serialVersionUID = 7533564112355082081L;
    private final StrictQueryValue<T> leftOperand;
    private final ArithmeticOperation operation;
    private final StrictQueryValue<T> rightOperand;

    /**
     * Create a new <code>ArithmeticValue</code> instance.
     *
     * @param leftOperand
     *            the left operand for the expression.
     * @param operation
     *            the arithmetic operation for the expression.
     * @param rightOperand
     *            the right operand for the expression.
     */
    public ArithmeticValueImpl(
            StrictQueryValue<T> leftOperand,
            ArithmeticOperation operation,
            StrictQueryValue<T> rightOperand) {
        this.leftOperand = leftOperand;
        this.operation = operation;
        this.rightOperand = rightOperand;
    }

    /**
     * Gets the left operand for the expression.
     *
     * @return the left operand for the expression.
     */
    public StrictQueryValue<T> getLeftOperand() {
        return leftOperand;
    }

    /**
     * Gets the arithmetic operation for the expression.
     *
     * @return the arithmetic operation for the expression.
     */
    public ArithmeticOperation getOperation() {
        return operation;
    }

    /**
     * Gets the right operand for the expression.
     *
     * @return the right operand for the expression.
     */
    public StrictQueryValue<T> getRightOperand() {
        return rightOperand;
    }
}
