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
package org.cementframework.querybyproxy.shared.api;

import org.cementframework.querybyproxy.shared.api.model.conditionals.Conditional;
import org.cementframework.querybyproxy.shared.api.model.sorts.QuerySort;
import org.cementframework.querybyproxy.shared.api.model.values.QueryValue;
import org.cementframework.querybyproxy.shared.api.model.values.StrictQueryValue;
import org.cementframework.querybyproxy.shared.api.model.values.TrimOption;

/**
 * Retrieves proxy-method calls and creates query-model fragments.
 *
 * @author allenparslow
 */
public interface ProxyQueryBuilder extends StrictQueryBuilder {

    /**
     * {@inheritDoc}
     */
    <T> QueryValue<T> get(T proxyMethodCall);

    /**
     * {@inheritDoc}
     */
    <T> QueryValue<T> get(Object proxyMethodCall, Class<T> returnType);

    /**
     * {@inheritDoc}
     */
    <T> QueryValue<T> literal(T object);


    /**
     * {@inheritDoc}
     */
    <T> QueryValue<T> param(T target);

    /**
     * {@inheritDoc}
     */
    <T> QueryValue<T> param(String name, Class<T> returnType);

    /**
     * Used to specify that the next conditional-expression should be created as
     * an or-expression, overriding the default conjunction/logic-gate (which is
     * <b>and</b>).
     *
     * @return a conditional-expression placeholder.
     */
    Conditional<?> or();

    /**
     * Creates a group "(...)" wrapper around the specified conditional
     * expressions.
     *
     * @param conditionalExpressions
     *            the conditional-expressions to group.
     * @return the group wrapper.
     */
    Conditional<?> group(Conditional<?>... conditionalExpressions);

    /**
     * Creates a query descending sort using the specified recorded proxy method
     * call.
     *
     * @param proxyMethodCall
     *            a recorded proxy method call.
     * @return a descending sort
     */
    QuerySort desc(Object proxyMethodCall);

    /**
     * Create a "count" aggregate using the specified selection.
     *
     * @param selection
     *            the expression to aggregate.
     * @return a query-value expression.
     */
    QueryValue<Long> count(StrictQueryValue<?> selection);

    /**
     * Create a "min" aggregate using the specified selection.
     *
     * @param <T>
     *            type-inference.
     * @param selection
     *            the expression to aggregate.
     * @return a query-value expression.
     */
    <T> QueryValue<T> min(StrictQueryValue<T> selection);

    /**
     * Create a "max" aggregate using the specified selection.
     *
     * @param <T>
     *            type-inference.
     * @param selection
     *            the expression to aggregate.
     * @return a query-value expression.
     */
    <T> QueryValue<T> max(StrictQueryValue<T> selection);

    /**
     * Create a "sum" aggregate using the specified selection.
     *
     * @param <T>
     *            type-inference.
     * @param selection
     *            the expression to aggregate.
     * @return a query-value expression.
     */
    <T> QueryValue<T> sum(StrictQueryValue<T> selection);


    /**
     * {@inheritDoc}
     */
    <T, X> QueryValue<X> sum(StrictQueryValue<T> selection, Class<X> returnType);

    /**
     * Create a "count" aggregate using the specified recorded proxy method
     * call.
     *
     * @param proxyMethodCall
     *            a recorded proxy method call. a recorded proxy method call.
     * @return a query-value expression.
     */
    QueryValue<Long> count(Object proxyMethodCall);

    /**
     * Creates a "min" aggregate using the specified recorded proxy method call.
     *
     * @param <T>
     *            type-inference.
     * @param proxyMethodCall
     *            a recorded proxy method call.
     * @return a query-value expression.
     */
    <T> QueryValue<T> min(T proxyMethodCall);

    /**
     * Creates a "max" aggregate using the specified recorded proxy method call.
     *
     * @param <T>
     *            type-inference.
     * @param proxyMethodCall
     *            a recorded proxy method call.
     * @return a query-value expression.
     */
    <T> QueryValue<T> max(T proxyMethodCall);

    /**
     * Creates a "sum" aggregate using the specified recorded proxy method call.
     *
     * @param <T>
     *            type-inference.
     * @param proxyMethodCall
     *            a recorded proxy method call.
     * @return a query-value expression.
     */
    <T> QueryValue<T> sum(T proxyMethodCall);

    /**
     * Creates a "sum" aggregate using the specified recorded proxy method call.
     *
     * @param proxyMethodCall
     *            a recorded proxy method call.
     * @return a query-value expression.
     */
    QueryValue<Long> sum(Integer proxyMethodCall);

    /**
     * Creates a avg aggregate using the specified recorded proxy method call.
     *
     * @param proxyMethodCall
     *            a recorded proxy method call.
     * @return a query-value expression.
     */
    QueryValue<Double> avg(Number proxyMethodCall);

    /**
     * Create a "avg" aggregate using the specified selection.
     *
     * @param selection
     *            the expression to aggregate.
     * @return a query-value expression.
     */
    QueryValue<Double> avg(StrictQueryValue<? extends Number> selection);

    /**
     * Create a "size" modifier using the specified selection.
     *
     * @param selection
     *            the expression to aggregate.
     * @return a query-value expression.
     */
    @SuppressWarnings("unchecked")
    QueryValue<Long> size(StrictQueryValue selection);

    /**
     * Create a new multiplication expression.
     *
     * @param leftOperand
     *            the left-operand for the expression
     * @param rightOperand
     *            the right-operand for the expression
     *
     * @return the new arithmetic expression.
     *
     * @param <T>
     *            type-inference
     */
    <T> QueryValue<T> multiply(T leftOperand, T rightOperand);

    /**
     * Create a new division expression.
     *
     * @param leftOperand
     *            the left-operand for the expression
     * @param rightOperand
     *            the right-operand for the expression
     *
     * @return the new arithmetic expression.
     *
     * @param <T>
     *            type-inference
     */
    <T> QueryValue<T> divide(T leftOperand, T rightOperand);

    /**
     * Create a new addition expression.
     *
     * @param leftOperand
     *            the left-operand for the expression
     * @param rightOperand
     *            the right-operand for the expression
     *
     * @return the new arithmetic expression.
     *
     * @param <T>
     *            type-inference
     */
    <T> QueryValue<T> add(T leftOperand, T rightOperand);

    /**
     * Create a new subtraction expression.
     *
     * @param leftOperand
     *            the left-operand for the expression
     * @param rightOperand
     *            the right-operand for the expression
     *
     * @return the new arithmetic expression.
     *
     * @param <T>
     *            type-inference
     */
    <T> QueryValue<T> subtract(T leftOperand, T rightOperand);

    /**
     * {@inheritDoc}
     */
    <T> QueryValue<T> createQueryValue(T target);

    /**
     * {@inheritDoc}
     */
    <T> QueryValue<T> createQueryValue(T target, boolean getFirst);

    /**
     * Creates a "abs" function expression.
     *
     * @param operand
     *            the value to operate on.
     *
     * @return the function expression.
     * @param <T>
     *            type-inference
     */
    <T> QueryValue<T> abs(T operand);

    /**
     * Creates a "concat" function expression.
     *
     * @param string1
     *            a string to concatenate.
     * @param string2
     *            a string to concatenate.
     *
     * @return the function expression.
     * @param <T>
     *            type-inference
     */
    <T> QueryValue<T> concat(String string1, String string2);

    /**
     * Creates a "length" function expression.
     *
     * @param string
     *            a string to examine.
     *
     * @return the function expression.
     */
    QueryValue<Integer> length(String string);

    /**
     * Creates a "trim" function expression.
     *
     * @param string
     *            a string to examine.
     *
     * @return the function expression.
     */
    QueryValue<String> trim(String string);

    /**
     * Creates a "trim" function expression.
     *
     * @param option
     *            the trim option to use (LEADING|TRAILING|BOTH)
     * @param string
     *            a string to examine.
     *
     * @return the function expression.
     */
    QueryValue<String> trim(TrimOption option, String string);

    /**
     * Creates a "trim" function expression.
     *
     * @param option
     *            the trim option to use (LEADING|TRAILING|BOTH)
     * @param character
     *            the character to trim.
     * @param string
     *            a string to examine.
     *
     * @return the function expression.
     */
    QueryValue<String> trim(
            TrimOption option,
            Character character,
            String string);

    /**
     * Creates a "locate" function expression.
     *
     * @param targetString
     *            the string operate on.
     * @param searchString
     *            the string to find.
     *
     * @return the function expression.
     */
    QueryValue<Integer> locate(String targetString, String searchString);

    /**
     * Creates a "locate" function expression.
     *
     * @param targetString
     *            the string operate on.
     * @param searchString
     *            the string to find.
     * @param start
     *            the start position to search from.
     *
     * @return the function expression.
     */
    QueryValue<Integer> locate(
            String targetString,
            String searchString,
            int start);

    /**
     * Creates a "lower" function expression.
     *
     * @param string
     *            the string to make lower case.
     * @return the function expression.
     */
    QueryValue<String> lower(String string);

    /**
     * Creates a "upper" function expression.
     *
     * @param string
     *            the string to make upper case.
     * @return the function expression.
     */
    QueryValue<String> upper(String string);

    /**
     * Creates a "substring" function expression.
     *
     * @param string
     *            the string to substring.
     * @param start
     *            the start position.
     * @param end
     *            the end position.
     *
     * @return the function expression.
     */
    QueryValue<String> substring(String string, int start, int end);

    /**
     * Creates a "sqrt" function expression.
     *
     * @param number
     *            the value to operate on.
     * @return the function expression.
     */
    QueryValue<Double> sqrt(Number number);

    /**
     * Creates a "mod" function expression.
     *
     * @param leftOperand
     *            the left-value for the operation.
     * @param rightOperand
     *            the right-value for the operation.
     * @return the function expression.
     */
    QueryValue<Integer> mod(Number leftOperand, Number rightOperand);

}
