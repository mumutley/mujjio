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

import java.util.List;

import org.cementframework.querybyproxy.shared.api.model.conditionals.Conditional;
import org.cementframework.querybyproxy.shared.api.model.values.QueryValue;
import org.cementframework.querybyproxy.shared.api.model.values.StrictQueryValue;
import org.cementframework.querybyproxy.shared.api.model.values.Subquery;

/**
 * A query-value that re-directs its methods to a callback.
 *
 * @author allenparslow
 * @param <T>
 *            type-inference
 */
public class RedirectingQueryValue<T> implements QueryValue<T>,
        ConditionalExpressionCallback {
    private static final long serialVersionUID = 1L;

    private final QueryValue<T> delegate;
    private final ConditionalExpressionCallback callback;

    /**
     * Create a new <code>QueryValueDelegate</code> instance.
     *
     * @param delegate
     *            the query-value to wrap
     * @param callback
     *            the callback to redirect method call results to.
     */
    public RedirectingQueryValue(QueryValue<T> delegate, ConditionalExpressionCallback callback) {
        this.delegate = delegate;
        this.callback = callback;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> equalTo(T value) {
        Conditional<T> result = delegate.equalTo(value);

        callback.processConditional(result);
        return result;

    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> equalTo(StrictQueryValue<T> value) {
        Conditional<T> result = delegate.equalTo(value);

        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> greaterThan(T value) {
        Conditional<T> result = delegate.greaterThan(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> greaterThan(StrictQueryValue<T> value) {
        Conditional<T> result = delegate.greaterThan(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> greaterThanOrEqualTo(T value) {
        Conditional<T> result = delegate.greaterThanOrEqualTo(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> greaterThanOrEqualTo(StrictQueryValue<T> value) {
        Conditional<T> result = delegate.greaterThanOrEqualTo(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> in(T... values) {
        Conditional<T> result = delegate.in(values);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> in(List<? extends StrictQueryValue<T>> values) {
        Conditional<T> result = delegate.in(values);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> isEmpty() {
        Conditional<T> result = delegate.isEmpty();
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> isNotEmpty() {
        Conditional<T> result = delegate.isNotEmpty();
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> isNotNull() {
        Conditional<T> result = delegate.isNotNull();
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> isNull() {
        Conditional<T> result = delegate.isNull();
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> lessThan(T value) {
        Conditional<T> result = delegate.lessThan(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> lessThan(StrictQueryValue<T> value) {
        Conditional<T> result = delegate.lessThan(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> lessThanOrEqualTo(T value) {
        Conditional<T> result = delegate.lessThanOrEqualTo(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> lessThanOrEqualTo(StrictQueryValue<T> value) {
        Conditional<T> result = delegate.lessThanOrEqualTo(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> like(T value) {
        Conditional<T> result = delegate.like(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> like(StrictQueryValue<T> value) {
        Conditional<T> result = delegate.like(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> memberOf(StrictQueryValue<List<T>> value) {
        Conditional<T> result = delegate.memberOf(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> notEqualTo(T value) {
        Conditional<T> result = delegate.notEqualTo(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> notEqualTo(StrictQueryValue<T> value) {
        Conditional<T> result = delegate.notEqualTo(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> notIn(T... values) {
        Conditional<T> result = delegate.notIn(values);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> notIn(List<? extends StrictQueryValue<T>> values) {
        Conditional<T> result = delegate.notIn(values);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> notLike(T value) {
        Conditional<T> result = delegate.notLike(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> notLike(StrictQueryValue<T> value) {
        Conditional<T> result = delegate.notLike(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> notMemberOf(StrictQueryValue<List<T>> value) {
        Conditional<T> result = delegate.notMemberOf(value);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> add(StrictQueryValue<T> value) {
        ArithmeticValueImpl<T> math = new ArithmeticValueImpl<T>(
                delegate,
                ArithmeticOperation.ADDITION,
                value);

        return new RedirectingQueryValue<T>(math, this);
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> add(T value) {
        ArithmeticValueImpl<T> math = new ArithmeticValueImpl<T>(
                delegate,
                ArithmeticOperation.ADDITION,
                createQueryValue(value));

        return new RedirectingQueryValue<T>(math, this);
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> between(T lowerBound, T upperBound) {
        Conditional<T> result = delegate.between(lowerBound, upperBound);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> dividedBy(StrictQueryValue<T> value) {
        ArithmeticValueImpl<T> math = new ArithmeticValueImpl<T>(
                delegate,
                ArithmeticOperation.DIVISION,
                value);

        return new RedirectingQueryValue<T>(math, this);
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> dividedBy(T value) {
        ArithmeticValueImpl<T> math = new ArithmeticValueImpl<T>(
                delegate,
                ArithmeticOperation.DIVISION,
                createQueryValue(value));

        return new RedirectingQueryValue<T>(math, this);
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> like(T value, Character escape) {
        Conditional<T> result = delegate.like(value, escape);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> like(StrictQueryValue<T> value, Character escape) {
        Conditional<T> result = delegate.like(value, escape);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> notBetween(T lowerBound, T upperBound) {
        Conditional<T> result = delegate.notBetween(lowerBound, upperBound);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> notLike(T value, Character escape) {
        Conditional<T> result = delegate.notLike(value, escape);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> notLike(StrictQueryValue<T> value, Character escape) {
        Conditional<T> result = delegate.notLike(value, escape);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> subtract(StrictQueryValue<T> value) {
        ArithmeticValueImpl<T> math = new ArithmeticValueImpl<T>(
                delegate,
                ArithmeticOperation.SUBTRACTION,
                value);

        return new RedirectingQueryValue<T>(math, this);
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> subtract(T value) {
        ArithmeticValueImpl<T> math = new ArithmeticValueImpl<T>(
                delegate,
                ArithmeticOperation.SUBTRACTION,
                createQueryValue(value));

        return new RedirectingQueryValue<T>(math, this);
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> times(StrictQueryValue<T> value) {

        ArithmeticValueImpl<T> math = new ArithmeticValueImpl<T>(
                delegate,
                ArithmeticOperation.MULTIPLICATION,
                value);

        return new RedirectingQueryValue<T>(math, this);
    }

    /**
     * {@inheritDoc}
     */
    public QueryValue<T> times(T value) {
        ArithmeticValueImpl<T> math = new ArithmeticValueImpl<T>(
                delegate,
                ArithmeticOperation.MULTIPLICATION,
                createQueryValue(value));

        return new RedirectingQueryValue<T>(math, this);
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> between(StrictQueryValue<T> lowerBound,
            StrictQueryValue<T> upperBound) {
        Conditional<T> result = delegate.between(lowerBound, upperBound);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> in(Subquery<T> subquery) {
        Conditional<T> result = delegate.in(subquery);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> notBetween(StrictQueryValue<T> lowerBound,
            StrictQueryValue<T> upperBound) {
        Conditional<T> result = delegate.notBetween(lowerBound, upperBound);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Conditional<T> notIn(Subquery<T> subquery) {
        Conditional<T> result = delegate.notIn(subquery);
        callback.processConditional(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public void processConditional(Conditional<?> conditional) {
        callback.processConditional(conditional);
    }
}
