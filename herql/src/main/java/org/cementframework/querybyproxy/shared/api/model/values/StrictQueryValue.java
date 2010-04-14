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
import org.cementframework.querybyproxy.shared.api.model.selections.Selection;
import org.cementframework.querybyproxy.shared.api.model.sorts.QuerySort;

/**
 * Represents a value or path expression used in a query.
 *
 * <p>
 * Contains a subset of possible operation (all very strictly typed) that can be
 * emitted from the value.
 * </p>
 *
 * @author allenparslow
 * @param <T>
 */
public interface StrictQueryValue<T> extends Selection, QuerySort {

    /**
     * Creates a "is empty" unary expression.
     *
     * @return the unary expression.
     */
    Conditional<T> isEmpty();

    /**
     * Creates a "is not empty" unary expression.
     *
     * @return the unary expression.
     */
    Conditional<T> isNotEmpty();

    /**
     * Creates a "is null" unary expression.
     *
     * @return the unary expression.
     */
    Conditional<T> isNull();

    /**
     * Creates a "is not null" unary expression.
     *
     * @return the unary expression.
     */
    Conditional<T> isNotNull();

    /**
     * Creates a "=" binary expression.
     *
     * @param value
     *            a query value.
     * @return the binary expression.
     */
    Conditional<T> equalTo(StrictQueryValue<T> value);

    /**
     * Creates a "between" ternary expression.
     *
     * @param lowerBound
     *            the lower bound for the expression
     * @param upperBound
     *            the upper bound for the expression
     * @return the binary expression.
     */
    Conditional<T> between(StrictQueryValue<T> lowerBound, StrictQueryValue<T> upperBound);

    /**
     * Creates a "not between" ternary expression.
     *
     * @param lowerBound
     *            the lower bound for the expression
     * @param upperBound
     *            the upper bound for the expression
     * @return the binary expression.
     */
    Conditional<T> notBetween(
            StrictQueryValue<T> lowerBound,
            StrictQueryValue<T> upperBound);

    /**
     * Creates a "!=" binary expression.
     *
     * @param value
     *            a query value.
     * @return the binary expression.
     */
    Conditional<T> notEqualTo(StrictQueryValue<T> value);

    /**
     * Creates a ">" binary expression.
     *
     * @param value
     *            a query value.
     * @return the binary expression.
     */
    Conditional<T> greaterThan(StrictQueryValue<T> value);

    /**
     * Creates a ">=" binary expression.
     *
     * @param value
     *            a query value.
     * @return the binary expression.
     */
    Conditional<T> greaterThanOrEqualTo(StrictQueryValue<T> value);

/**
     * Creates a "<" binary expression.
     *
     * @param value
     *            a query value.
     * @return the binary expression.
     */
    Conditional<T> lessThan(StrictQueryValue<T> value);

    /**
     * Creates a "<=" binary expression.
     *
     * @param value
     *            a query value.
     * @return the binary expression.
     */
    Conditional<T> lessThanOrEqualTo(StrictQueryValue<T> value);

    /**
     * Creates a "in" binary expression.
     *
     * @param values
     *            a list of values to use in the expression.
     * @return the binary expression.
     */
    Conditional<T> in(List<? extends StrictQueryValue<T>> values);

    /**
     * Creates a "in" subquery modifier around a subquery.
     *
     * @param subquery
     *            the subquery to wrap.
     * @return a modifier-subquery-wrapper.
     */
    Conditional<T> in(Subquery<T> subquery);

    /**
     * Creates a "in" subquery modifier around a subquery.
     *
     * @param subquery
     *            the subquery to wrap.
     * @return a modifier-subquery-wrapper.
     */
    Conditional<T> notIn(Subquery<T> subquery);

    /**
     * Creates a "not in" binary expression.
     *
     * @param values
     *            a list of values to use in the expression.
     * @return the binary expression.
     */
    Conditional<T> notIn(List<? extends StrictQueryValue<T>> values);

    /**
     * Creates a "member of" binary expression.
     *
     * @param value
     *            A query value representing a list of values.
     * @return the binary expression.
     */
    Conditional<T> memberOf(StrictQueryValue<List<T>> value);

    /**
     * Creates a "not member of" binary expression.
     *
     * @param value
     *            A query value representing a list of values.
     * @return the binary expression.
     */
    Conditional<T> notMemberOf(StrictQueryValue<List<T>> value);

    /**
     * Creates a "like" binary expression.
     *
     * @param value
     *            a query value.
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
     *            a query value.
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
     * Create a new multiplication expression.
     *
     * @param value
     *            the value to multiply by.
     * @return the new arithmetic expression
     */
    StrictQueryValue<T> times(StrictQueryValue<T> value);

    /**
     * Create a new division expression.
     *
     * @param value
     *            the value to divide by.
     * @return the new arithmetic expression
     */
    StrictQueryValue<T> dividedBy(StrictQueryValue<T> value);

    /**
     * Create a new addition expression.
     *
     * @param value
     *            the value to divide by.
     * @return the new arithmetic expression
     */
    StrictQueryValue<T> add(StrictQueryValue<T> value);

    /**
     * Create a new subtraction expression.
     *
     * @param value
     *            the value to divide by.
     * @return the new arithmetic expression
     */
    StrictQueryValue<T> subtract(StrictQueryValue<T> value);
}
