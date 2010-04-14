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

import org.cementframework.querybyproxy.shared.api.model.SelectClause;
import org.cementframework.querybyproxy.shared.api.model.selections.Selection;

/**
 * Represents a "select" clause.
 *
 * @author allenparslow
 */
public class SelectClauseImpl implements SelectClause {

    private static final long serialVersionUID = 8223444731352829064L;
    private final List<Selection> selections;
    private final boolean distinct;

    /**
     * Create a new <code>SelectImpl</code> instance.
     *
     * @param distinct
     *            true, if the select should be distinct.
     */
    public SelectClauseImpl(boolean distinct) {
        this.selections = Collections.unmodifiableList(new ArrayList<Selection>(0));
        this.distinct = distinct;
    }

    /**
     * Create a new <code>SelectImpl</code> instance.
     *
     * <p>
     * <code>SelectImpl</code> is immutable, this constructor is used to clone a
     * select, modifying the distinct property
     * </p>
     *
     * @param select
     *            the base select
     * @param distinct
     *            true, if the select should be distinct.
     */
    public SelectClauseImpl(SelectClause select, boolean distinct) {
        this.selections = Collections.unmodifiableList(select.getSelections());
        this.distinct = distinct;
    }

    /**
     * Create a new <code>SelectImpl</code> instance.
     *
     * @param selectionList
     *            the list of selections.
     * @param distinct
     *            true, if the select should be distinct.
     */
    public SelectClauseImpl(List<Selection> selectionList, boolean distinct) {
        this.selections = Collections.unmodifiableList(selectionList);
        this.distinct = distinct;
    }

    /**
     * {@inheritDoc}
     */
    public List<Selection> getSelections() {
        return selections;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isDistinct() {
        return distinct;
    }
}
