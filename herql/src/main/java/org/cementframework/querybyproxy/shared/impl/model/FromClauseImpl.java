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
package org.cementframework.querybyproxy.shared.impl.model;

import java.util.Collections;
import java.util.List;

import org.cementframework.querybyproxy.shared.api.model.FromClause;
import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoin;
import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoinModifier;
import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoinType;
import org.cementframework.querybyproxy.shared.impl.model.joins.AbstractJoin;

/**
 * Represents a "from" clause.
 *
 * @author allenparslow
 */
public class FromClauseImpl extends AbstractJoin implements FromClause {

    private static final long serialVersionUID = 2580020229869522488L;

    private final List<QueryJoin> joins;

    /**
     * Create a new instance.
     *
     * @param joins
     *            the list of joins for the from clause.
     */
    public FromClauseImpl(List<QueryJoin> joins) {
        super(QueryJoinType.INNER, QueryJoinModifier.NONE);
        this.joins = Collections.unmodifiableList(joins);
    }

    /**
     * {@inheritDoc}
     */
    public List<QueryJoin> getJoins() {
        return joins;
    }
}
