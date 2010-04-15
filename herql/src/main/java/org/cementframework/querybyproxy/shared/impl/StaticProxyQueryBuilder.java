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

import org.cementframework.querybyproxy.shared.api.ProxyQueryBuilder;
import org.cementframework.querybyproxy.shared.api.model.conditionals.Conditional;
import org.cementframework.querybyproxy.shared.api.model.values.QueryValue;

/**
 * Static access to a query-builder.
 *
 * <p>
 * Used for very terse query construction.
 * </p>
 *
 * @author allenparslow
 */
public class StaticProxyQueryBuilder {
    private static final ProxyQueryBuilder INSTANCE = new ProxyQueryBuilderImpl();

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
    public static <T> QueryValue<T> get(Object proxyMethodCall, Class<T> returnType) {
        return INSTANCE.get(proxyMethodCall, returnType);
    }

    /**
     * Gets a query-value expression using the last recorded proxy method call.
     *
     * @param <T>
     *            type-inference.
     * @param proxyMethodCall
     *            a recorded proxy method call.
     * @return a query-value expression.
     */
    public static <T> QueryValue<T> get(T proxyMethodCall) {
        return INSTANCE.get(proxyMethodCall);
    }

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
    public static <T> QueryValue<T> createQueryValue(T target) {
        return INSTANCE.createQueryValue(target);
    }

    /**
     * Creates a group "(...)" wrapper around the specified conditional
     * expressions.
     *
     * @param conditionalExpressions
     *            the conditional-expressions to group.
     * @return the group wrapper.
     */
    public static Conditional<?> group(Conditional<?>... conditionalExpressions) {
        return INSTANCE.group(conditionalExpressions);
    }

    /**
     * Used to specify that the next conditional-expression should be created as
     * an or-expression, overriding the default conjunction/logic-gate (which is
     * <b>and</b>).
     *
     * @return a conditional-expression placeholder.
     */
    public static Conditional<?> or() {
        return INSTANCE.or();
    }

    /**
     * Produces a jpql bindable for the target object (produces ":paramX" syntax
     * during query compilation).
     *
     * @param <T>
     *            type-inference.
     * @param recordedMethodCall
     *            the recorded method call to be used a bindable
     *            query-parameter.
     * @return a query-value expression.
     */
    public static <T> QueryValue<T> param(T recordedMethodCall) {
        return INSTANCE.param(recordedMethodCall);
    }

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
    public static <T> QueryValue<T> param(String name, Class<T> returnType) {
        return INSTANCE.param(name, null);
    }

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
    public static <T> QueryValue<T> literal(T object) {
        return INSTANCE.literal(object);
    }
}
