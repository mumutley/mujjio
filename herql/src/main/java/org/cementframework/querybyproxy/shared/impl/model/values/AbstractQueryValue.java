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

import static org.cementframework.querybyproxy.shared.impl.StaticProxyQueryBuilder.*;

import java.util.ArrayList;
import java.util.List;

import org.cementframework.querybyproxy.shared.api.model.values.QueryValue;
import org.cementframework.querybyproxy.shared.api.model.values.StrictQueryValue;
import org.cementframework.querybyproxy.shared.impl.model.conditionals.BetweenConditionalImpl;
import org.cementframework.querybyproxy.shared.impl.model.conditionals.BinaryConditionalImpl;
import org.cementframework.querybyproxy.shared.impl.model.conditionals.ComparisonOperator;

/**
 * Abstract representation a query-value.
 *
 * @author allenparslow
 * @param <T>
 *            the type of value
 */
public abstract class AbstractQueryValue<T> extends AbstractStrictQueryValue<T>
        implements QueryValue<T> {

    private static final long serialVersionUID = 5840321054960827035L;

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> notIn(T... values) {
        List<QueryLiteral<T>> params = new ArrayList<QueryLiteral<T>>(values.length);
        for (T value : values) {
            params.add(new QueryLiteral<T>(value));
        }

        return inOperator(params, ComparisonOperator.NOT_IN);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> in(T... values) {
        List<QueryLiteral<T>> params = new ArrayList<QueryLiteral<T>>(values.length);
        for (T value : values) {
            params.add(new QueryLiteral<T>(value));
        }

        return inOperator(params, ComparisonOperator.IN);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> notLike(T value) {
        return binaryOperation(createQueryValue(value),
                ComparisonOperator.NOT_LIKE);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> notLike(T value, Character escape) {
        return likeOperation(createQueryValue(value), ComparisonOperator.NOT_LIKE, escape);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> like(T value) {
        return binaryOperation(createQueryValue(value), ComparisonOperator.LIKE);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> like(T value, Character escape) {
        return likeOperation(createQueryValue(value), ComparisonOperator.LIKE, escape);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> equalTo(T value) {
        return binaryOperation(createQueryValue(value),
                ComparisonOperator.EQUALS);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> notEqualTo(T value) {
        return binaryOperation(createQueryValue(value),
                ComparisonOperator.NOT_EQUALS);
    }

    /**
     * {@inheritDoc}
     */
    public BetweenConditionalImpl<T> between(T lowerBound, T upperBound) {
        return betweenOperation(
                createQueryValue(lowerBound),
                createQueryValue(upperBound),
                ComparisonOperator.BETWEEN);
    }

    /**
     * {@inheritDoc}
     */
    public BetweenConditionalImpl<T> notBetween(T lowerBound, T upperBound) {
        return betweenOperation(
                createQueryValue(lowerBound),
                createQueryValue(upperBound),
                ComparisonOperator.NOT_BETWEEN);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> greaterThan(T value) {
        return binaryOperation(createQueryValue(value),
                ComparisonOperator.GREATER_THAN);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> greaterThanOrEqualTo(T value) {
        return binaryOperation(createQueryValue(value),
                ComparisonOperator.GREATER_THAN_OR_EQUAL_TO);

    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> lessThan(T value) {
        return binaryOperation(createQueryValue(value),
                ComparisonOperator.LESS_THAN);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> lessThanOrEqualTo(T value) {
        return binaryOperation(createQueryValue(value),
                ComparisonOperator.LESS_THAN_OR_EQUAL_TO);
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> times(StrictQueryValue<T> value) {
        return new ArithmeticValueImpl<T>(
                this,
                ArithmeticOperation.MULTIPLICATION,
                value);
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> dividedBy(StrictQueryValue<T> value) {
        return new ArithmeticValueImpl<T>(
                this,
                ArithmeticOperation.DIVISION,
                value);
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> add(StrictQueryValue<T> value) {
        return new ArithmeticValueImpl<T>(
                this,
                ArithmeticOperation.ADDITION,
                value);
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> subtract(StrictQueryValue<T> value) {
        return new ArithmeticValueImpl<T>(
                this,
                ArithmeticOperation.SUBTRACTION,
                value);
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> times(T value) {
        return new ArithmeticValueImpl<T>(
                this,
                ArithmeticOperation.MULTIPLICATION,
                createQueryValue(value));
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> dividedBy(T value) {
        return new ArithmeticValueImpl<T>(
                this,
                ArithmeticOperation.DIVISION,
                createQueryValue(value));
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> add(T value) {
        return new ArithmeticValueImpl<T>(
                this,
                ArithmeticOperation.ADDITION,
                createQueryValue(value));
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> subtract(T value) {
        return new ArithmeticValueImpl<T>(
                this,
                ArithmeticOperation.SUBTRACTION,
                createQueryValue(value));
    }
}
