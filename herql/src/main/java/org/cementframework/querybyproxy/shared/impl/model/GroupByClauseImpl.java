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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.cementframework.querybyproxy.shared.api.model.GroupByClause;
import org.cementframework.querybyproxy.shared.api.model.selections.Selection;

/**
 * Represents a "group by" clause.
 *
 * @author allenparslow
 */
public class GroupByClauseImpl implements GroupByClause {

    private static final long serialVersionUID = 3277883003334266917L;
    private final List<Selection> selections;

    /**
     * Create a new <code>GroupByImpl</code> instance.
     */
    public GroupByClauseImpl() {
        this.selections = Collections.unmodifiableList(new ArrayList<Selection>());
    }

    /**
     * Create a new <code>GroupByImpl</code> instance.
     *
     * @param selections
     *            the selections to group-by-clause.
     */
    public GroupByClauseImpl(List<Selection> selections) {
        this.selections = Collections.unmodifiableList(selections);
    }

    /**
     * {@inheritDoc}
     */
    public List<Selection> getSelections() {
        return selections;
    }
}
