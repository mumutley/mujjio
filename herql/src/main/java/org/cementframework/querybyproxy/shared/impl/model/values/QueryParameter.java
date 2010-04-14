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

import org.cementframework.querybyproxy.shared.api.model.QueryBindable;

/**
 * Gets a bindable query-parameter.
 *
 * @author allenparslow
 * @param <T>
 *            type-inference
 */
public class QueryParameter<T> extends QueryLiteral<T> implements QueryBindable {

    private static final long serialVersionUID = 5987996831129882677L;

    private final String name;

    /**
     * Create a new <code>QueryParameter</code> instance.
     *
     * @param target
     *            the target object.
     */
    public QueryParameter(Object target) {
        super(target);
        this.name = null;
    }

    /**
     * Create a new <code>QueryParameter</code> instance.
     *
     * @param target
     *            the target object.
     * @param name
     *            the name for the parameter
     */
    public QueryParameter(Object target, String name) {
        super(target);
        this.name = name;
    }

    /**
     * Gets the name for the parameter.
     *
     * <p>NOTE: null indicates that the system should assign a name.</p>
     *
     * @return the name for the parameter.
     */
    public String getName() {
        return name;
    }
 }
