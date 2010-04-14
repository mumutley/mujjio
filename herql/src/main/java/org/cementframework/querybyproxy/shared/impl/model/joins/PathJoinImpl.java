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
package org.cementframework.querybyproxy.shared.impl.model.joins;

import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoinModifier;
import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoinType;
import org.cementframework.recordingproxy.api.RecordedMethodCall;

/**
 * Represent a path expression in a join.
 *
 * @author allenparslow
 */
public class PathJoinImpl extends AbstractJoin {
    private static final long serialVersionUID = 1452188122898381187L;

    private final RecordedMethodCall call;
    private final Object proxy;

    /**
     * Create a new <code>ProxyJoin</code> instance.
     *
     * @param joinType
     *            the join-type.
     * @param joinModifier
     *            the fetch-join modifier.
     * @param call
     *            the target call for the join.
     * @param proxy
     *            the target proxy (may be different from the call).
     */
    public PathJoinImpl(
            QueryJoinType joinType,
            QueryJoinModifier joinModifier,
            Object proxy,
            RecordedMethodCall call) {
        super(joinType, joinModifier);
        this.call = call;
        this.proxy = proxy;
    }

    /**
     * Gets the call for join (contains the path).
     *
     * @return the call for the join.
     */
    public RecordedMethodCall getCall() {
        return call;
    }

    /**
     * Get the target proxy for the join.
     *
     * @return the target proxy.
     */
    public Object getProxy() {
        return proxy;
    }
}
