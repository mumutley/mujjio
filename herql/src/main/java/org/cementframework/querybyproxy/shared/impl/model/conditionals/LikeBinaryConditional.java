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
 * Represents a binary "like" expression using in where and having clauses.
 *
 * <p>
 * General form: [left-path/value] [like|not like] [right-path/value] [escape]
 * </p>
 * <p>
 * Example: a.id like ? escape '\'
 * </p>
 *
 * @author allenparslow
 *
 * @param <T>
 *            the type of conditional
 */
public class LikeBinaryConditional<T> extends BinaryConditionalImpl<T> {

    private static final long serialVersionUID = -823733230940981754L;
    private final Character escape;

    /**
     * Create a new <code>LikeBinaryConditional</code> instance.
     *
     * @param logicGate
     *            the logic gate (conjunction).
     * @param leftValue
     *            the left-value.
     * @param operator
     *            the operator (e.g. =,>,<).
     * @param rightvalue
     *            the right-value.
     * @param escape
     *            the escape character to use.
     *
     */
    @SuppressWarnings("unchecked")
    public LikeBinaryConditional(
            LogicGate logicGate,
            StrictQueryValue leftValue,
            ComparisonOperator operator,
            StrictQueryValue rightvalue,
            Character escape) {
        super(logicGate, leftValue, operator, rightvalue);
        this.escape = escape;
    }

    /**
     * Gets the escape character to use.
     *
     * @return the escape character to use.
     */
    public Character getEscape() {
        return escape;
    }
}
