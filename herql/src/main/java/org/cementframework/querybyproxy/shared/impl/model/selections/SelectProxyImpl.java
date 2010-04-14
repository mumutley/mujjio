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
package org.cementframework.querybyproxy.shared.impl.model.selections;

import org.cementframework.querybyproxy.shared.api.model.selections.Selection;

/**
 * A selection of a proxy/entity.
 *
 * <p>
 * Example: "select a"
 * </p>
 *
 * @author allenparslow
 */
public class SelectProxyImpl implements Selection {
    private static final long serialVersionUID = 5917679956258540901L;
    private final Object proxy;

    /**
     * Create a new <code>SelectProxy</code> instance.
     *
     * @param proxy
     *            the target proxy
     */
    public SelectProxyImpl(Object proxy) {
        this.proxy = proxy;
    }

    /**
     * Gets the proxy for the selection.
     *
     * @return the target proxy
     */
    public Object getProxy() {
        return proxy;
    }
}
