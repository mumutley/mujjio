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

import java.util.Collections;
import java.util.List;

import org.cementframework.querybyproxy.shared.api.model.values.StrictQueryValue;

/**
 * Represents of list of values (e.g. for an "in" expression).
 *
 * @author allenparslow
 * @param <T>
 *            type-inference.
 */
public class ValuesListImpl<T> extends AbstractQueryValue<T> {
    private static final long serialVersionUID = 4543180568218769649L;
    private final List<? extends StrictQueryValue<T>> values;

    /**
     * Create a new <code>ValuesList</code> instance.
     *
     * @param list
     *            the list of <code>QueryValue</code> for the expression.
     */
    public ValuesListImpl(List<? extends StrictQueryValue<T>> list) {
        this.values = Collections.unmodifiableList(list);
    }

    /**
     * Gets the list of query-values for the expression.
     *
     * @return the list of query-values
     */
    public List<? extends StrictQueryValue<T>> getValues() {
        return values;
    }
}
