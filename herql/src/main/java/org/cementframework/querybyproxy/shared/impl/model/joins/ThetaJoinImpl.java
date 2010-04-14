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

/**
 * Represents a theta (non-relationship) for a from-clause.
 *
 * <p>
 * Example: "from Entity a1, Entity a2 where a1.id = a2.id"
 * </p>
 *
 * @author allenparslow
 */
public class ThetaJoinImpl extends AbstractJoin {

    private static final long serialVersionUID = 3442993482153875589L;

    private final Object joinProxy;
    private final Class<?> joinClass;

    /**
     * Create a new instance.
     *
     * @param joinClass
     *            the class for the join (used for naming).
     * @param joinProxy
     *            the proxy for the join.
     */
    public ThetaJoinImpl(Class<?> joinClass, Object joinProxy) {
        super(QueryJoinType.THETA, QueryJoinModifier.NONE);
        this.joinProxy = joinProxy;
        this.joinClass = joinClass;
    }

    /**
     * Gets the proxy for the join.
     *
     * @return the proxy for the join.
     */
    public Object getJoinProxy() {
        return joinProxy;
    }

    /**
     * Gets the the class for the join (used for naming).
     *
     * @return the target class.
     */
    public Class<?> getJoinClass() {
        return joinClass;
    }
}
