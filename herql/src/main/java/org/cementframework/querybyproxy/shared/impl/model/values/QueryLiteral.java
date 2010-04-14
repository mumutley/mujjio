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

/**
 * Represents a query literal (non-parameter) value.
 *
 * <p>
 * SQL Injection Warning: Do NOT use with end-user specified text as it is
 * treated as a literal string during query compilation
 * </p>
 *
 * @author allenparslow
 * @param <T>
 *            type-inference
 */
public class QueryLiteral<T> extends AbstractQueryValue<T> {

    private static final long serialVersionUID = -8766467384674166754L;
    private final Object target;

    /**
     * Create a new <code>QueryLiteral</code> instance.
     *
     * @param target
     *            target object.
     */
    public QueryLiteral(Object target) {
        this.target = target;
    }

    /**
     * Gets the target object.
     *
     * @return the target object.
     */
    public Object getTarget() {
        return target;
    }
}
