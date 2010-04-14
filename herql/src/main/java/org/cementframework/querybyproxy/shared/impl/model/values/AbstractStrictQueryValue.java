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

import java.util.List;

import org.cementframework.querybyproxy.shared.api.ProxyQuerySessions;
import org.cementframework.querybyproxy.shared.api.model.conditionals.LogicGate;
import org.cementframework.querybyproxy.shared.api.model.values.QueryValue;
import org.cementframework.querybyproxy.shared.api.model.values.StrictQueryValue;
import org.cementframework.querybyproxy.shared.api.model.values.Subquery;
import org.cementframework.querybyproxy.shared.impl.model.conditionals.BetweenConditionalImpl;
import org.cementframework.querybyproxy.shared.impl.model.conditionals.BinaryConditionalImpl;
import org.cementframework.querybyproxy.shared.impl.model.conditionals.ComparisonOperator;
import org.cementframework.querybyproxy.shared.impl.model.conditionals.LikeBinaryConditional;
import org.cementframework.querybyproxy.shared.impl.model.conditionals.UnaryConditionalImpl;

/**
 * An abstract representation a value or path expression used in a query.
 *
 * <p>
 * Contains a subset of possible operation (all very strictly typed) that can be
 * emitted from the value.
 * </p>
 *
 * @author allenparslow
 * @param <T>
 */
public abstract class AbstractStrictQueryValue<T> implements StrictQueryValue<T> {

    private static final long serialVersionUID = -3199208177315500161L;

    /**
     * {@inheritDoc}
     */
    public UnaryConditionalImpl<T> isNotEmpty() {
        return unaryOperation(ComparisonOperator.IS_NOT_EMPTY);
    }

    /**
     * {@inheritDoc}
     */
    public UnaryConditionalImpl<T> isEmpty() {
        return unaryOperation(ComparisonOperator.IS_EMPTY);
    }

    /**
     * {@inheritDoc}
     */
    public UnaryConditionalImpl<T> isNull() {
        return unaryOperation(ComparisonOperator.IS_NULL);
    }

    /**
     * {@inheritDoc}
     */
    public UnaryConditionalImpl<T> isNotNull() {
        return unaryOperation(ComparisonOperator.IS_NOT_NULL);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> notLike(StrictQueryValue<T> value) {
        return binaryOperation(value, ComparisonOperator.NOT_LIKE);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> notLike(StrictQueryValue<T> value, Character escape) {
        return likeOperation(value, ComparisonOperator.NOT_LIKE, escape);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> like(StrictQueryValue<T> value) {
        return binaryOperation(value, ComparisonOperator.LIKE);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> like(StrictQueryValue<T> value, Character escape) {
        return likeOperation(value, ComparisonOperator.LIKE, escape);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> notIn(List<? extends StrictQueryValue<T>> values) {

        return inOperator(values, ComparisonOperator.NOT_IN);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> in(List<? extends StrictQueryValue<T>> values) {

        return inOperator(values, ComparisonOperator.IN);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> in(Subquery<T> subquery) {
        return binaryOperation(subquery, ComparisonOperator.IN);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> notIn(Subquery<T> subquery) {
        return binaryOperation(subquery, ComparisonOperator.NOT_IN);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public BinaryConditionalImpl<T> memberOf(StrictQueryValue<List<T>> value) {
        QueryValue<T> expValue = (QueryValue<T>) value;
        return binaryOperation(expValue, ComparisonOperator.MEMBER_OF);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public BinaryConditionalImpl<T> notMemberOf(StrictQueryValue<List<T>> value) {
        QueryValue<T> expValue = (QueryValue<T>) value;
        return binaryOperation(expValue, ComparisonOperator.NOT_MEMBER_OF);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> equalTo(StrictQueryValue<T> value) {
        return binaryOperation(value, ComparisonOperator.EQUALS);
    }

    /**
     * {@inheritDoc}
     */
    public BetweenConditionalImpl<T> between(
            StrictQueryValue<T> lowerBound,
            StrictQueryValue<T> upperBound) {
        return betweenOperation(lowerBound, upperBound, ComparisonOperator.BETWEEN);
    }

    /**
     * {@inheritDoc}
     */
    public BetweenConditionalImpl<T> notBetween(
            StrictQueryValue<T> lowerBound,
            StrictQueryValue<T> upperBound) {
        return betweenOperation(lowerBound, upperBound, ComparisonOperator.NOT_BETWEEN);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> notEqualTo(StrictQueryValue<T> value) {
        return binaryOperation(value, ComparisonOperator.NOT_EQUALS);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> greaterThan(StrictQueryValue<T> value) {
        return binaryOperation(value, ComparisonOperator.GREATER_THAN);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> greaterThanOrEqualTo(StrictQueryValue<T> value) {
        return binaryOperation(value, ComparisonOperator.GREATER_THAN_OR_EQUAL_TO);

    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> lessThan(StrictQueryValue<T> value) {
        return binaryOperation(value, ComparisonOperator.LESS_THAN);
    }

    /**
     * {@inheritDoc}
     */
    public BinaryConditionalImpl<T> lessThanOrEqualTo(StrictQueryValue<T> value) {
        return binaryOperation(value, ComparisonOperator.LESS_THAN_OR_EQUAL_TO);
    }

    protected UnaryConditionalImpl<T> unaryOperation(ComparisonOperator expression) {
        LogicGate logicGate = ProxyQuerySessions.get().getNextLogicGate();

        UnaryConditionalImpl<T> conditional = new UnaryConditionalImpl<T>(
                logicGate, this, expression);
        return conditional;
    }

    protected BinaryConditionalImpl<T> likeOperation(
            StrictQueryValue<T> value,
            ComparisonOperator expression,
            Character escape) {

        LogicGate logicGate = ProxyQuerySessions.get().getNextLogicGate();

        if (value == null) {
            throw new IllegalArgumentException(
                    "Binary expression query-value must not be null: "
                    + " check that querybuilder.get() was called"
                    + " OR unary isNull/isNotNull was intended");
        }
        BinaryConditionalImpl<T> conditional = new LikeBinaryConditional<T>(
                logicGate, this, expression, value, escape);

        return conditional;
    }

    protected BetweenConditionalImpl<T> betweenOperation(
            StrictQueryValue<T> lowerBound,
            StrictQueryValue<T> upperBound,
            ComparisonOperator expression) {

        LogicGate logicGate = ProxyQuerySessions.get().getNextLogicGate();

        if (lowerBound == null) {
            throw new IllegalArgumentException(
                    "Between expression lower-bound must not be null: "
                    + " check that querybuilder.get() was called"
                    + " OR unary isNull/isNotNull was intended");
        }
        if (upperBound == null) {
            throw new IllegalArgumentException(
                    "Between expression upper-bound must not be null: "
                    + " check that querybuilder.get() was called"
                    + " OR unary isNull/isNotNull was intended");
        }

        BetweenConditionalImpl<T> conditional = new BetweenConditionalImpl<T>(
                logicGate, this, expression, lowerBound, upperBound);

        return conditional;
    }

    protected BinaryConditionalImpl<T> binaryOperation(
            StrictQueryValue<T> value,
            ComparisonOperator expression) {

        LogicGate logicGate = ProxyQuerySessions.get().getNextLogicGate();

        if (value == null) {
            throw new IllegalArgumentException(
                    "Binary expression query-value must not be null: "
                    + " check that querybuilder.get() was called"
                    + " OR unary isNull/isNotNull was intended");
        }
        BinaryConditionalImpl<T> conditional = new BinaryConditionalImpl<T>(
                logicGate, this, expression, value);

        return conditional;
    }

    protected BinaryConditionalImpl<T> inOperator(List<? extends StrictQueryValue<T>> params,
            ComparisonOperator expression) {

        return binaryOperation(
                new ValuesListImpl<T>(params),
                expression);
    }
}
