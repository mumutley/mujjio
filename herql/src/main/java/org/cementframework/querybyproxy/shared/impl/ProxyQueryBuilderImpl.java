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
package org.cementframework.querybyproxy.shared.impl;

import java.util.Arrays;
import java.util.Date;

import org.cementframework.querybyproxy.shared.api.ProxyQueryBuilder;
import org.cementframework.querybyproxy.shared.api.ProxyQuerySession;
import org.cementframework.querybyproxy.shared.api.ProxyQuerySessions;
import org.cementframework.querybyproxy.shared.api.model.conditionals.Conditional;
import org.cementframework.querybyproxy.shared.api.model.conditionals.LogicGate;
import org.cementframework.querybyproxy.shared.api.model.sorts.QuerySortOperator;
import org.cementframework.querybyproxy.shared.api.model.values.QueryValue;
import org.cementframework.querybyproxy.shared.api.model.values.StrictQueryValue;
import org.cementframework.querybyproxy.shared.api.model.values.Subquery;
import org.cementframework.querybyproxy.shared.api.model.values.TrimOption;
import org.cementframework.querybyproxy.shared.impl.model.QueryStaticText;
import org.cementframework.querybyproxy.shared.impl.model.conditionals.ComparisonOperator;
import org.cementframework.querybyproxy.shared.impl.model.conditionals.GroupConditionalImpl;
import org.cementframework.querybyproxy.shared.impl.model.conditionals.SubqueryConditionalImpl;
import org.cementframework.querybyproxy.shared.impl.model.selections.AggregateSelectionImpl;
import org.cementframework.querybyproxy.shared.impl.model.selections.QueryAggregate;
import org.cementframework.querybyproxy.shared.impl.model.sorts.DirectionalQuerySortImpl;
import org.cementframework.querybyproxy.shared.impl.model.values.ArithmeticOperation;
import org.cementframework.querybyproxy.shared.impl.model.values.ArithmeticValueImpl;
import org.cementframework.querybyproxy.shared.impl.model.values.ProxyPathExpressionImpl;
import org.cementframework.querybyproxy.shared.impl.model.values.ProxySelectExpressionImpl;
import org.cementframework.querybyproxy.shared.impl.model.values.QueryFunction;
import org.cementframework.querybyproxy.shared.impl.model.values.QueryFunctionValueImpl;
import org.cementframework.querybyproxy.shared.impl.model.values.QueryLiteral;
import org.cementframework.querybyproxy.shared.impl.model.values.QueryParameter;
import org.cementframework.querybyproxy.shared.impl.model.values.SubqueryDecoratorImpl;
import org.cementframework.querybyproxy.shared.impl.model.values.SubqueryOperator;
import org.cementframework.recordingproxy.api.RecordedMethodCall;
import org.cementframework.recordingproxy.api.RecordingSession;
import org.cementframework.recordingproxy.api.RecordingSessions;
import org.cementframework.recordingproxy.impl.MethodCallUtils;

/**
 * Retrieves proxy-method calls and creates query-model fragments.
 *
 * @author allenparslow
 */
class ProxyQueryBuilderImpl implements ProxyQueryBuilder {

    /**
     * {@inheritDoc}
     */
    @Override
    public DirectionalQuerySortImpl desc(Object instance) {

        return new DirectionalQuerySortImpl(
                createQueryValue(instance),
                QuerySortOperator.DESC);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public QueryValue<Long> count(Object selection) {
        return (QueryValue) createAggregateSelection(selection, QueryAggregate.COUNT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> min(T selection) {
        return createAggregateSelection(selection, QueryAggregate.MIN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> max(T selection) {
        return createAggregateSelection(selection, QueryAggregate.MAX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> sum(T selection) {
        return createAggregateSelection(selection, QueryAggregate.SUM);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public QueryValue<Long> sum(Integer selection) {
        return (QueryValue) createAggregateSelection(selection, QueryAggregate.SUM);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public QueryValue<Double> avg(Number selection) {
        return (QueryValue) createAggregateSelection(selection, QueryAggregate.AVG);
    }

    private <T> AggregateSelectionImpl<T> createAggregateSelection(
            T selection,
            QueryAggregate aggregate) {
        return new AggregateSelectionImpl<T>(
                aggregate,
                createQueryValue(selection, false));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public QueryValue<Long> count(StrictQueryValue<?> selection) {
        QueryValue<Long> value = (QueryValue<Long>) createAggregateSelectionSrict(
                selection, QueryAggregate.COUNT);
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public QueryValue<Long> size(StrictQueryValue selection) {
        QueryValue<Long> aggValue = (QueryValue<Long>) selection;
        return createAggregateSelectionSrict(aggValue, QueryAggregate.SIZE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> min(StrictQueryValue<T> selection) {
        return createAggregateSelectionSrict(selection, QueryAggregate.MIN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> max(StrictQueryValue<T> selection) {
        return createAggregateSelectionSrict(selection, QueryAggregate.MAX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> sum(StrictQueryValue<T> selection) {
        return createAggregateSelectionSrict(selection, QueryAggregate.SUM);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T, X> QueryValue<X> sum(StrictQueryValue<T> selection, Class<X> returnType) {
        return (QueryValue) createAggregateSelectionSrict(selection, QueryAggregate.SUM);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public QueryValue<Double> avg(StrictQueryValue<? extends Number> selection) {
        return (QueryValue) createAggregateSelectionSrict(selection, QueryAggregate.AVG);
    }

    private <T> AggregateSelectionImpl<T> createAggregateSelectionSrict(
            StrictQueryValue<T> selection,
            QueryAggregate aggregate) {
        return new AggregateSelectionImpl<T>(aggregate, selection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Conditional<?> or() {

        getSession().setNextLogicGate(LogicGate.OR);

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Conditional exists(Subquery subquery) {
        LogicGate logicGate = getSession().getNextLogicGate();

        return new SubqueryConditionalImpl(logicGate, ComparisonOperator.EXISTS, subquery);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Conditional notExists(Subquery subquery) {
        LogicGate logicGate = getSession().getNextLogicGate();

        return new SubqueryConditionalImpl(logicGate, ComparisonOperator.NOT_EXISTS, subquery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Subquery<T> all(Subquery<T> subquery) {

        return new SubqueryDecoratorImpl<T>(SubqueryOperator.ALL, subquery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Subquery<T> any(Subquery<T> subquery) {

        return new SubqueryDecoratorImpl<T>(SubqueryOperator.ANY, subquery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Subquery<T> some(Subquery<T> subquery) {

        return new SubqueryDecoratorImpl<T>(SubqueryOperator.SOME, subquery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Conditional<?> group(Conditional<?>... conditionalExpressions) {

        LogicGate logicGate = getSession().getNextLogicGate();

        GroupConditionalImpl group = new GroupConditionalImpl(logicGate,
                Arrays.asList(conditionalExpressions));

        RecordingSessions.get().clear();

        return group;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> QueryValue<T> get(Object instance, Class<T> returnType) {

        QueryValue<T> column;
        if (MethodCallUtils.isProxy(instance)) {
            RecordedMethodCall call = getRecordingSession().getLastCall();

            if (call != null) {
                column = new ProxyPathExpressionImpl(
                        call);
            } else {
                column = new ProxySelectExpressionImpl(instance);
            }
        } else {
            RecordedMethodCall call = getRecordingSession().getSafeLastCall();

            column = new ProxyPathExpressionImpl(
                    call);
        }

        return column;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> get(T instance) {

        return get(instance, null);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public DirectionalQuerySortImpl desc(StrictQueryValue selection) {
        return new DirectionalQuerySortImpl(selection, QuerySortOperator.DESC);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryLiteral<T> literal(T target) {
        return new QueryLiteral<T>(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> createQueryValue(T target) {
        return createQueryValue(target, true);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> QueryValue<T> createQueryValue(T target, boolean getFirst) {
        if (target != null) {
            if (MethodCallUtils.isProxy(target)) {
                throw new IllegalArgumentException(
                        "createQueryValue cannot be called on a proxy, "
                        + "use get instead");
            } else if (target instanceof QueryValue) {
                return (QueryValue) target;
            } else {
                return new QueryLiteral<T>(target);
            }
        } else {
            return createProperty(getFirst);
        }
    }

    @SuppressWarnings("unchecked")
    private ProxyPathExpressionImpl createProperty(boolean getFirst) {
        RecordedMethodCall call;
        RecordingSession recordingSession = RecordingSessions.get();
        if (getFirst) {
            call = recordingSession.getSafeFirstCall();
        } else {
            call = recordingSession.getSafeLastCall();
        }

        ProxyPathExpressionImpl selection = new ProxyPathExpressionImpl(call);

        return selection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryParameter<T> param(T target) {
        return new QueryParameter<T>(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryParameter<T> param(String name, Class<T> returnType) {
        return new QueryParameter<T>(null, name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> multiply(T leftOperand, T rightOperand) {
        return new ArithmeticValueImpl<T>(
                createQueryValue(leftOperand),
                ArithmeticOperation.MULTIPLICATION,
                createQueryValue(rightOperand));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> divide(T leftOperand, T rightOperand) {
        return new ArithmeticValueImpl<T>(
                createQueryValue(leftOperand),
                ArithmeticOperation.DIVISION,
                createQueryValue(rightOperand));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> add(T leftOperand, T rightOperand) {
        return new ArithmeticValueImpl<T>(
                createQueryValue(leftOperand),
                ArithmeticOperation.ADDITION,
                createQueryValue(rightOperand));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> subtract(T leftOperand, T rightOperand) {
        return new ArithmeticValueImpl<T>(
                createQueryValue(leftOperand),
                ArithmeticOperation.SUBTRACTION,
                createQueryValue(rightOperand));
    }

    //

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> multiply(
            StrictQueryValue<T> leftOperand,
            StrictQueryValue<T> rightOperand) {
        return new ArithmeticValueImpl<T>(
                leftOperand,
                ArithmeticOperation.MULTIPLICATION,
                rightOperand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> divide(
            StrictQueryValue<T> leftOperand,
            StrictQueryValue<T> rightOperand) {
        return new ArithmeticValueImpl<T>(
                leftOperand,
                ArithmeticOperation.DIVISION,
                rightOperand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> add(
            StrictQueryValue<T> leftOperand,
            StrictQueryValue<T> rightOperand) {
        return new ArithmeticValueImpl<T>(
                leftOperand,
                ArithmeticOperation.ADDITION,
                rightOperand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> subtract(
            StrictQueryValue<T> leftOperand,
            StrictQueryValue<T> rightOperand) {
        return new ArithmeticValueImpl<T>(
                leftOperand,
                ArithmeticOperation.SUBTRACTION,
                rightOperand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> abs(StrictQueryValue<T> operand) {
        return new QueryFunctionValueImpl<T>(QueryFunction.ABS, true, operand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> concat(
            StrictQueryValue<String> string1,
            StrictQueryValue<String> string2) {
        return new QueryFunctionValueImpl<T>(QueryFunction.CONCAT, true, string1, string2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<Date> currentDate() {
        return new QueryFunctionValueImpl<Date>(QueryFunction.CURRENT_DATE, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<Date> currentTime() {
        return new QueryFunctionValueImpl<Date>(QueryFunction.CURRENT_TIME, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<Date> currentTimestamp() {
        return new QueryFunctionValueImpl<Date>(QueryFunction.CURRENT_TIMESTAMP, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<Integer> length(StrictQueryValue<String> string) {
        return new QueryFunctionValueImpl<Integer>(QueryFunction.LENGTH, false, string);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<String> trim(StrictQueryValue<String> string) {
        return new QueryFunctionValueImpl<String>(QueryFunction.TRIM, false, string);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<String> trim(TrimOption option, StrictQueryValue<String> string) {
        return new QueryFunctionValueImpl<String>(QueryFunction.TRIM, false, option, string);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<String> trim(
            TrimOption option,
            StrictQueryValue<Character> character,
            StrictQueryValue<String> string) {
        return new QueryFunctionValueImpl<String>(
                QueryFunction.TRIM, false,
                option,
                character,
                new QueryStaticText(" FROM"),
                string);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<Integer> locate(
            StrictQueryValue<String> targetString,
            StrictQueryValue<String> searchString) {
        return new QueryFunctionValueImpl<Integer>(
                QueryFunction.LOCATE, true,
                targetString,
                searchString);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<Integer> locate(
            StrictQueryValue<String> targetString,
            StrictQueryValue<String> searchString,
            int start) {
        return new QueryFunctionValueImpl<Integer>(
                QueryFunction.LOCATE, true,
                targetString,
                searchString,
                new QueryLiteral<Integer>(start));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<String> lower(StrictQueryValue<String> string) {
        return new QueryFunctionValueImpl<String>(
                QueryFunction.LOWER, true, string);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<Integer> mod(
            StrictQueryValue<? extends Number> leftOperand,
            StrictQueryValue<? extends Number> rightOperand) {
        return new QueryFunctionValueImpl<Integer>(
                QueryFunction.MOD, true, leftOperand, rightOperand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<Double> sqrt(StrictQueryValue<? extends Number> number) {
        return new QueryFunctionValueImpl<Double>(
                QueryFunction.SQRT, true, number);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<String> substring(
            StrictQueryValue<String> string,
            int start,
            int end) {
        return new QueryFunctionValueImpl<String>(
                QueryFunction.SUBSTRING, true,
                string,
                new QueryLiteral<Integer>(start),
                new QueryLiteral<Integer>(end));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<String> upper(StrictQueryValue<String> string) {
        return new QueryFunctionValueImpl<String>(
                QueryFunction.UPPER, true, string);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> abs(T operand) {
        return new QueryFunctionValueImpl<T>(QueryFunction.ABS, true,
                createQueryValue(operand));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> QueryValue<T> concat(String string1, String string2) {
        return new QueryFunctionValueImpl<T>(QueryFunction.CONCAT, true,
                createQueryValue(string1),
                createQueryValue(string2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<Integer> length(String string) {
        return new QueryFunctionValueImpl<Integer>(QueryFunction.LENGTH, false,
                createQueryValue(string));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<String> trim(String string) {
        return new QueryFunctionValueImpl<String>(QueryFunction.TRIM, false,
                createQueryValue(string));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<String> trim(TrimOption option, String string) {
        return new QueryFunctionValueImpl<String>(
                QueryFunction.TRIM, false,
                option,
                createQueryValue(string));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<String> trim(
            TrimOption option,
            Character character,
            String string) {
        return new QueryFunctionValueImpl<String>(
                QueryFunction.TRIM, false,
                option,
                createQueryValue(character),
                new QueryStaticText(" FROM"),
                createQueryValue(string));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<Integer> locate(String targetString, String searchString) {
        return new QueryFunctionValueImpl<Integer>(
                QueryFunction.LOCATE, true,
                createQueryValue(targetString),
                createQueryValue(searchString));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<Integer> locate(
            String targetString,
            String searchString,
            int start) {
        return new QueryFunctionValueImpl<Integer>(
                QueryFunction.LOCATE, true,
                createQueryValue(targetString),
                createQueryValue(searchString),
                new QueryLiteral<Integer>(start));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<String> lower(String string) {
        return new QueryFunctionValueImpl<String>(
                QueryFunction.LOWER, true, createQueryValue(string));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<Integer> mod(Number leftOperand, Number rightOperand) {
        return new QueryFunctionValueImpl<Integer>(
                QueryFunction.MOD, true,
                createQueryValue(leftOperand),
                createQueryValue(rightOperand));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<Double> sqrt(Number number) {
        return new QueryFunctionValueImpl<Double>(
                QueryFunction.SQRT, true, createQueryValue(number));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<String> substring(
            String string,
            int start,
            int end) {
        return new QueryFunctionValueImpl<String>(
                QueryFunction.SUBSTRING, true,
                createQueryValue(string),
                new QueryLiteral<Integer>(start),
                new QueryLiteral<Integer>(end));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryValue<String> upper(String string) {
        return new QueryFunctionValueImpl<String>(
                QueryFunction.UPPER, true, createQueryValue(string));
    }

    private RecordingSession getRecordingSession() {
        return RecordingSessions.get();
    }

    private ProxyQuerySession getSession() {
        return ProxyQuerySessions.get();
    }

}
