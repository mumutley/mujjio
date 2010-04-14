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

import java.util.Date;

import org.cementframework.querybyproxy.shared.api.model.conditionals.Conditional;
import org.cementframework.querybyproxy.shared.api.model.sorts.QuerySort;
import org.cementframework.querybyproxy.shared.api.model.values.QueryValue;
import org.cementframework.querybyproxy.shared.api.model.values.StrictQueryValue;
import org.cementframework.querybyproxy.shared.api.model.values.Subquery;
import org.cementframework.querybyproxy.shared.api.model.values.TrimOption;

/**
 * Represent query builder that allows only strictly-typed grammar as parameters
 * for its methods.
 *
 * @author allenparslow
 */
public interface StrictQueryBuilder {

    /**
     * Gets a query-value expression using the last recorded proxy method call.
     *
     * @param <T>
     *            type-inference.
     * @param proxyMethodCall
     *            a recorded proxy method call.
     * @return a query-value expression.
     */
    <T> StrictQueryValue<T> get(T proxyMethodCall);

    /**
     * Gets a query-value expression with an overridden type using the last
     * recorded proxy method call.
     *
     * <p>
     * Commonly used when dealing with aliased properties as the alias type
     * might not be of the same type as the recorded method call's type
     * </p>
     *
     * @param <T>
     *            type-inference using the specified returnType.
     * @param proxyMethodCall
     *            a recorded proxy method call.
     * @param returnType
     *            The overridden return type.
     * @return a query-value expression.
     */
    <T> StrictQueryValue<T> get(Object proxyMethodCall, Class<T> returnType);

    /**
     * Produces a literal (non-parameter) expression using the passed object.
     *
     * <p>
     * SQL Injection Warning: Do NOT use with end-user specified text as it is
     * treated as a literal string during query compilation
     * </p>
     *
     * @param <T>
     *            type-inference.
     * @param object
     *            the target object to be used as a literal.
     * @return a query-value expression.
     */
    <T> StrictQueryValue<T> literal(T object);

    /**
     * Produces a jpql bindable for the target object (produces ":paramX" syntax
     * during query compilation).
     *
     * @param <T>
     *            type-inference.
     * @param target
     *            the target object to be used a bindable query-parameter.
     * @return a query-value expression.
     */
    <T> StrictQueryValue<T> param(T target);

    /**
     * Produces a jpql bindable for the target object.
     *
     * @param <T>
     *            type-inference.
     * @param name
     *            the name for the parameter (numbered parameters, i.e. ?1
     *            are <b>NOT</b> supported)
     * @param returnType
     *            the type of parameter.
     *
     * @return a query-value expression.
     */
    <T> StrictQueryValue<T> param(String name, Class<T> returnType);

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
     * Creates a query descending sort using the specified selection.
     *
     * @param selection
     *            a selection.
     * @return a descending sort
     */
    @SuppressWarnings("unchecked")
    QuerySort desc(StrictQueryValue selection);

    /**
     * Create a "count" aggregate using the specified selection.
     *
     * @param selection
     *            the expression to aggregate.
     * @return a query-value expression.
     */
    StrictQueryValue<Long> count(StrictQueryValue<?> selection);

    /**
     * Create a "min" aggregate using the specified selection.
     *
     * @param <T>
     *            type-inference.
     * @param selection
     *            the expression to aggregate.
     * @return a query-value expression.
     */
    <T> StrictQueryValue<T> min(StrictQueryValue<T> selection);

    /**
     * Create a "max" aggregate using the specified selection.
     *
     * @param <T>
     *            type-inference.
     * @param selection
     *            the expression to aggregate.
     * @return a query-value expression.
     */
    <T> StrictQueryValue<T> max(StrictQueryValue<T> selection);

    /**
     * Create a "sum" aggregate using the specified selection.
     *
     * @param <T>
     *            type-inference.
     * @param selection
     *            the expression to aggregate.
     * @return a query-value expression.
     */
    <T> StrictQueryValue<T> sum(StrictQueryValue<T> selection);

    /**
     * Create a "sum" aggregate using the specified selection.
     *
     * @param <T>
     *            parameter-type.
     * @param <X> return-type
     * @param selection
     *            the expression to aggregate.
     * @param returnType the type of value to return
     * @return a query-value expression.
     */
    <T, X> StrictQueryValue<X> sum(StrictQueryValue<T> selection, Class<X> returnType);

    /**
     * Create a "count" aggregate using the specified recorded proxy method
     * call.
     *
     * @param proxyMethodCall
     *            a recorded proxy method call. a recorded proxy method call.
     * @return a query-value expression.
     */
    StrictQueryValue<Long> count(Object proxyMethodCall);

    /**
     * Create a "avg" aggregate using the specified selection.
     *
     * @param selection
     *            the expression to aggregate.
     * @return a query-value expression.
     */
    StrictQueryValue<Double> avg(StrictQueryValue<? extends Number> selection);

    /**
     * Create a "size" modifier using the specified selection.
     *
     * @param selection
     *            the expression to aggregate.
     * @return a query-value expression.
     */
    @SuppressWarnings("unchecked")
    StrictQueryValue<Long> size(StrictQueryValue selection);

    /**
     * Creates a "exists" subquery modifier around a subquery.
     *
     * @param subquery
     *            the subquery to wrap.
     * @return a modifier-subquery-wrapper conditional-expression.
     * @param <T>
     *            type-inference
     */
    <T> Conditional<T> exists(Subquery<T> subquery);

    /**
     * Creates a "not exists" subquery modifier around a subquery.
     *
     * @param subquery
     *            the subquery to wrap.
     * @return a modifier-subquery-wrapper conditional-expression.
     * @param <T>
     *            type-inference
     */
    <T> Conditional<T> notExists(Subquery<T> subquery);

    /**
     * Creates a "all" subquery modifier around a subquery.
     *
     * @param <T>
     *            type-inference.
     * @param subquery
     *            the subquery to wrap.
     * @return a modifier-subquery-wrapper.
     */
    <T> Subquery<T> all(Subquery<T> subquery);

    /**
     * Creates a "any" subquery modifier around a subquery.
     *
     * @param <T>
     *            type-inference.
     * @param subquery
     *            the subquery to wrap.
     * @return a modifier-subquery-wrapper.
     */
    <T> Subquery<T> any(Subquery<T> subquery);

    /**
     * Creates a "some" subquery modifier around a subquery.
     *
     * @param <T>
     *            type-inference.
     * @param subquery
     *            the subquery to wrap.
     * @return a modifier-subquery-wrapper.
     */
    <T> Subquery<T> some(Subquery<T> subquery);

    /**
     * Creates a query value expression used either a recorded method call, a
     * literal value or query parameter.
     *
     * @param <T>
     *            type-inference.
     * @param target
     *            the target value or recorded method call.
     * @return the query value expression.
     */
    <T> StrictQueryValue<T> createQueryValue(T target);

    /**
     * Creates a query value expression used either a recorded method call, a
     * literal value or query parameter.
     *
     * @param <T>
     *            type-inference.
     * @param target
     *            the target value or recorded method call.
     * @param useFirstCall
     *            True, if the first call on the recording stack should be used.
     * @return the query value expression.
     */
    <T> StrictQueryValue<T> createQueryValue(T target, boolean useFirstCall);

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
    <T> StrictQueryValue<T> abs(StrictQueryValue<T> operand);

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
    <T> StrictQueryValue<T> concat(
            StrictQueryValue<String> string1,
            StrictQueryValue<String> string2);

    /**
     * Creates a "currentDate" function expression.
     *
     * @return the function expression.
     */
    StrictQueryValue<Date> currentDate();

    /**
     * Creates a "currentTime" function expression.
     *
     * @return the function expression.
     */
    StrictQueryValue<Date> currentTime();

    /**
     * Creates a "currentTimestamp" function expression.
     *
     * @return the function expression.
     */
    StrictQueryValue<Date> currentTimestamp();

    /**
     * Creates a "length" function expression.
     *
     * @param string
     *            a string to examine.
     *
     * @return the function expression.
     */
    StrictQueryValue<Integer> length(StrictQueryValue<String> string);

    /**
     * Creates a "trim" function expression.
     *
     * @param string
     *            a string to examine.
     *
     * @return the function expression.
     */
    StrictQueryValue<String> trim(StrictQueryValue<String> string);

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
    StrictQueryValue<String> trim(TrimOption option, StrictQueryValue<String> string);

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
    StrictQueryValue<String> trim(
            TrimOption option,
            StrictQueryValue<Character> character,
            StrictQueryValue<String> string);

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
    StrictQueryValue<Integer> locate(
            StrictQueryValue<String> targetString,
            StrictQueryValue<String> searchString);

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
    StrictQueryValue<Integer> locate(
            StrictQueryValue<String> targetString,
            StrictQueryValue<String> searchString,
            int start);

    /**
     * Creates a "lower" function expression.
     *
     * @param string
     *            the string to make lower case.
     * @return the function expression.
     */
    StrictQueryValue<String> lower(StrictQueryValue<String> string);

    /**
     * Creates a "upper" function expression.
     *
     * @param string
     *            the string to make upper case.
     * @return the function expression.
     */
    StrictQueryValue<String> upper(StrictQueryValue<String> string);

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
    StrictQueryValue<String> substring(
            StrictQueryValue<String> string,
            int start,
            int end);

    /**
     * Creates a "sqrt" function expression.
     *
     * @param number
     *            the value to operate on.
     * @return the function expression.
     */
    StrictQueryValue<Double> sqrt(StrictQueryValue<? extends Number> number);

    /**
     * Creates a "mod" function expression.
     *
     * @param leftOperand
     *            the left-value for the operation.
     * @param rightOperand
     *            the right-value for the operation.
     * @return the function expression.
     */
    StrictQueryValue<Integer> mod(
            StrictQueryValue<? extends Number> leftOperand,
            StrictQueryValue<? extends Number> rightOperand);

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
    <T> QueryValue<T> multiply(
            StrictQueryValue<T> leftOperand,
            StrictQueryValue<T> rightOperand);

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
    <T> QueryValue<T> divide(
            StrictQueryValue<T> leftOperand,
            StrictQueryValue<T> rightOperand);

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
    <T> QueryValue<T> add(
            StrictQueryValue<T> leftOperand,
            StrictQueryValue<T> rightOperand);

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
    <T> QueryValue<T> subtract(
            StrictQueryValue<T> leftOperand,
            StrictQueryValue<T> rightOperand);
}
