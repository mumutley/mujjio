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
package org.cementframework.querybyproxy.shared.api.model;

import java.util.List;

import org.cementframework.querybyproxy.shared.api.model.selections.Selection;

/**
 * Represents a "select" clause.
 *
 * @author allenparslow
 */
public interface SelectClause extends QueryFragment {

    /**
     * Gets the selections for the select.
     *
     * @return the selections.
     */
    List<Selection> getSelections();

    /**
     * Gets the distinct flag for the select.
     *
     * @return True, if the select should be "distinct"
     */
    boolean isDistinct();
}
