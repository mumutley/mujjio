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

import org.cementframework.recordingproxy.api.RecordedMethodCall;

/**
 * Represent of property of an entity-proxy.
 *
 * @author allenparslow
 * @param <T>
 *            type-inference
 */
public class ProxyPathExpressionImpl<T> extends AbstractQueryValue<T>
        implements ProxyExpressionImpl {

    private static final long serialVersionUID = 8919711552192750045L;
    private final RecordedMethodCall call;

    /**
     * Create a new <code>ProxyProperty</code> instance.
     *
     * @param call
     *            The recorded method call of a entity-proxy.
     */
    public ProxyPathExpressionImpl(RecordedMethodCall call) {
        this.call = call;
    }

    /**
     * Gets the method call recorded for the target entity-proxy.
     *
     * @return the method call recorded.
     */
    public RecordedMethodCall getCall() {
        return call;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return call.toString();
    }

    /**
     * Gets the type of expression.
     *
     * @return the type of expression.
     */
    public Class<?> getType() {
        return call.getElementType();
    }
}
