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
import java.util.List;

import org.cementframework.querybyproxy.shared.api.model.HavingClause;
import org.cementframework.querybyproxy.shared.api.model.conditionals.Conditional;
import org.cementframework.querybyproxy.shared.api.model.conditionals.LogicGate;
import org.cementframework.querybyproxy.shared.impl.model.conditionals.GroupConditionalImpl;

/**
 * Represents a "having" clause.
 *
 * @author allenparslow
 */
public class HavingClauseImpl extends GroupConditionalImpl implements HavingClause {

    private static final long serialVersionUID = 8900709124672295762L;

    /**
     * Create a new instance.
     */
    public HavingClauseImpl() {
        super(LogicGate.AND, new ArrayList<Conditional<?>>());
    }

    /**
     * Create a new <code>HavingImpl</code> instance.
     *
     * @param conditionals
     *            the conditional-expressions for the having-clause.
     */
    public HavingClauseImpl(List<Conditional<?>> conditionals) {
        super(LogicGate.AND, conditionals);
    }
}
