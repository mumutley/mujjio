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

import java.util.Collection;

import org.cementframework.querybyproxy.shared.api.ProxyQuery;
import org.cementframework.querybyproxy.shared.api.ProxyQueryBuilder;
import org.cementframework.querybyproxy.shared.api.ProxyQueryFactory;
import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoin;
import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoinModifier;
import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoinType;
import org.cementframework.querybyproxy.shared.impl.model.joins.PathJoinImpl;
import org.cementframework.recordingproxy.api.RecordedMethodCall;
import org.cementframework.recordingproxy.api.RecordingSessions;
import org.cementframework.recordingproxy.impl.MethodCallUtils;

/**
 * Creates new <code>ProxyQuery</code> and <code>ProxyQueryBuilder</code>
 * instances.
 *
 * @author allenparslow
 */
public abstract class AbstractProxyQueryFactory implements ProxyQueryFactory {
    private ProxyQueryBuilder queryBuilder = new ProxyQueryBuilderImpl();

    /**
     * {@inheritDoc}
     */
    public ProxyQueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public final <T> ProxyQuery<T> correlatedSubquery(Collection<T> methodCall) {

        RecordedMethodCall call = RecordingSessions.get().getSafeLastCall();

        T joinProxy = (T) MethodCallUtils.proxy(call.getElementType());

        QueryJoin join = new PathJoinImpl(
                QueryJoinType.ROOT,
                QueryJoinModifier.NONE,
                joinProxy,
                call);

        ProxyQuery<T> subquery = createCorrelated(joinProxy, join);

        RecordingSessions.get().clear();

        return subquery;
    }

    protected abstract <T> ProxyQuery<T> createCorrelated(
            T proxy,
            QueryJoin join);

}
