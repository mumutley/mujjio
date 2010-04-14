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
 * Represent proxy itself used as value (as opposed to the invocation of proxy's
 * property).
 *
 * @author allenparslow
 * @param <T>
 *            type-inference
 */
public class ProxySelectExpressionImpl<T> extends AbstractQueryValue<T>
        implements ProxyExpressionImpl {

    private static final long serialVersionUID = -9060920016886323214L;
    private final T proxy;

    /**
     * Create a new <code>ProxyValue</code> instance.
     *
     * @param proxy
     *            the target proxy
     */
    public ProxySelectExpressionImpl(T proxy) {
        this.proxy = proxy;
    }

    /**
     * Gets the target proxy.
     *
     * @return the target proxy.
     */
    public T getProxy() {
        return proxy;
    }
}
