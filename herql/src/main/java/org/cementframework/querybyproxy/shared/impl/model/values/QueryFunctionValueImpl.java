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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.cementframework.querybyproxy.shared.api.model.QueryFragment;

/**
 * Represents a query expression function.
 *
 * @author allenparslow
 * @param <T>
 *            the function's return type
 */
public class QueryFunctionValueImpl<T> extends AbstractQueryValue<T> {

    private static final long serialVersionUID = -6548635640025939526L;
    private final QueryFunction function;
    private final List<QueryFragment> arguments;
    private final boolean commaSeparated;

    /**
     * Create a new <code>QueryFunctionValue</code> instance.
     *
     * @param function
     *            the function.
     * @param commaSeparate
     *            true, if the function args are comma separated.
     * @param arguments
     *            function arguments.
     */
    public QueryFunctionValueImpl(QueryFunction function,
            boolean commaSeparate,
            QueryFragment... arguments) {
        this.function = function;
        this.commaSeparated = commaSeparate;
        this.arguments = Collections.unmodifiableList(
                new ArrayList<QueryFragment>(Arrays.asList(arguments)));

    }

    /**
     * Gets the function.
     *
     * @return the function.
     */
    public QueryFunction getFunction() {
        return function;
    }

    /**
     * Gets the function arguments.
     *
     * @return function arguments.
     */
    public List<QueryFragment> getArguments() {
        return arguments;
    }

    /**
     * Gets the flag indicating if function arguments should be comma separated.
     *
     * @return true, if the function args are comma separated.
     */
    public boolean isCommaSeparated() {
        return commaSeparated;
    }
}
