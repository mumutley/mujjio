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

import java.util.List;

import org.cementframework.querybyproxy.shared.api.model.conditionals.Conditional;

/**
 * Represents a value or path expression used in a query.
 *
 * <p>
 * Contains the possible operation that can be emitted from the value.
 * </p>
 *
 * @author allenparslow
 *
 * @param <T>
 *            the type of value.
 */
public interface QueryValue<T> extends StrictQueryValue<T> {

    /**
     * Creates a "=" binary expression.
     *
     * @param value
     *            a value or recorded method call
     * @return the binary expression.
     */
    Conditional<T> equalTo(T value);

    /**
     * Creates a "!=" binary expression.
     *
     * @param value
     *            a value or recorded method call
     * @return the binary expression.
     */
    Conditional<T> notEqualTo(T value);

    /**
     * Creates a "between" ternary expression.
     *
     * @param lowerBound
     *            the lower bound for the expression (value or recorded method
     *            call)
     * @param upperBound
     *            the upper bound for the expression (value or recorded method
     *            call)
     * @return the binary expression.
     */
    Conditional<T> between(T lowerBound, T upperBound);

    /**
     * Creates a "not between" ternary expression.
     *
     * @param lowerBound
     *            the lower bound for the expression
     * @param upperBound
     *            the upper bound for the expression
     * @return the binary expression.
     */
    Conditional<T> notBetween(T lowerBound, T upperBound);

    /**
     * Creates a ">" binary expression.
     *
     * @param value
     *            a value or recorded method call
     * @return the binary expression.
     */
    Conditional<T> greaterThan(T value);

    /**
     * Creates a ">=" binary expression.
     *
     * @param value
     *            a value or recorded method call
     * @return the binary expression.
     */
    Conditional<T> greaterThanOrEqualTo(T value);

/**
     * Creates a "<" binary expression.
     *
     * @param value
     *            a value or recorded method call
     * @return the binary expression.
     */
    Conditional<T> lessThan(T value);

    /**
     * Creates a "<=" binary expression.
     *
     * @param value
     *            a value or recorded method call
     * @return the binary expression.
     */
    Conditional<T> lessThanOrEqualTo(T value);

    /**
     * Creates a "like" binary expression.
     *
     * @param value
     *            a value or recorded method call
     * @return the binary expression.
     */
    Conditional<T> like(T value);

    /**
     * Creates a "like" binary expression with an escape option.
     *
     * @param value
     *            a value or recorded method call
     * @param escape
     *            the escape character to use
     * @return the binary expression.
     */
    Conditional<T> like(T value, Character escape);

    /**
     * Creates a "like" binary expression.
     *
     * @param value
     *            a <code>QueryValue</code>.
     * @return the binary expression.
     */
    Conditional<T> like(StrictQueryValue<T> value);

    /**
     * Creates a "like" binary expression with an escape option.
     *
     * @param value
     *            a query value.
     * @param escape
     *            the escape character to use
     * @return the binary expression.
     */
    Conditional<T> like(StrictQueryValue<T> value, Character escape);

    /**
     * Creates a "not like" binary expression.
     *
     * @param value
     *            a value or recorded method call
     * @return the binary expression.
     */
    Conditional<T> notLike(T value);

    /**
     * Creates a "not like" binary expression with an escape option.
     *
     * @param value
     *            a value or recorded method call
     * @param escape
     *            the escape character to use
     * @return the binary expression.
     */
    Conditional<T> notLike(T value, Character escape);

    /**
     * Creates a "not like" binary expression.
     *
     * @param value
     *            a <code>QueryValue</code>.
     * @return the binary expression.
     */
    Conditional<T> notLike(StrictQueryValue<T> value);

    /**
     * Creates a "not like" binary expression with an escape option.
     *
     * @param value
     *            a query value.
     * @param escape
     *            the escape character to use
     * @return the binary expression.
     */
    Conditional<T> notLike(StrictQueryValue<T> value, Character escape);

    /**
     * Creates a "in" binary expression.
     *
     * @param values
     *            var-arg of values or recorded method calls
     * @return the binary expression.
     */
    Conditional<T> in(T... values);

    /**
     * Creates a "in" binary expression.
     *
     * @param values
     *            a list of <code>QueryValue</code>.
     * @return the binary expression.
     */
    Conditional<T> in(List<? extends StrictQueryValue<T>> values);

    /**
     * Creates a "not in" binary expression.
     *
     * @param values
     *            var-arg of values or recorded method calls
     * @return the binary expression.
     */
    Conditional<T> notIn(T... values);

    /**
     * Creates a "not in" binary expression.
     *
     * @param values
     *            a list of <code>QueryValue</code>.
     * @return the binary expression.
     */
    Conditional<T> notIn(List<? extends StrictQueryValue<T>> values);

    /**
     * Creates a "member of" binary expression.
     *
     * @param value
     *            a qb.get(recorded method call) of a list property.
     * @return the binary expression.
     */
    Conditional<T> memberOf(StrictQueryValue<List<T>> value);

    /**
     * Creates a "not member of" binary expression.
     *
     * @param value
     *            a qb.get(recorded method call) of a list property.
     * @return the binary expression.
     */
    Conditional<T> notMemberOf(StrictQueryValue<List<T>> value);

    /**
     * Create a new multiplication expression.
     *
     * @param value
     *            the value to multiply by.
     * @return the new arithmetic expression
     */
    QueryValue<T> times(StrictQueryValue<T> value);

    /**
     * Create a new division expression.
     *
     * @param value
     *            the value to divide by.
     * @return the new arithmetic expression
     */
    QueryValue<T> dividedBy(StrictQueryValue<T> value);

    /**
     * Create a new addition expression.
     *
     * @param value
     *            the value to divide by.
     * @return the new arithmetic expression
     */
    QueryValue<T> add(StrictQueryValue<T> value);

    /**
     * Create a new subtraction expression.
     *
     * @param value
     *            the value to divide by.
     * @return the new arithmetic expression
     */
    QueryValue<T> subtract(StrictQueryValue<T> value);

    /**
     * Create a new multiplication expression.
     *
     * @param value
     *            the value to multiply by.
     * @return the new arithmetic expression
     */
    QueryValue<T> times(T value);

    /**
     * Create a new division expression.
     *
     * @param value
     *            the value to divide by.
     * @return the new arithmetic expression
     */
    QueryValue<T> dividedBy(T value);

    /**
     * Create a new addition expression.
     *
     * @param value
     *            the value to divide by.
     * @return the new arithmetic expression
     */
    QueryValue<T> add(T value);

    /**
     * Create a new subtraction expression.
     *
     * @param value
     *            the value to divide by.
     * @return the new arithmetic expression
     */
    QueryValue<T> subtract(T value);
}
