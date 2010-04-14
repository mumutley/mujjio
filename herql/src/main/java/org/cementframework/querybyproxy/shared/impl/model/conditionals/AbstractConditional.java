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
package org.cementframework.querybyproxy.shared.impl.model.conditionals;

import org.cementframework.querybyproxy.shared.api.model.conditionals.Conditional;
import org.cementframework.querybyproxy.shared.api.model.conditionals.LogicGate;

/**
 * An abstract implementation of a conditional-expression.
 *
 * @param <T>
 *            the type of conditional.
 *
 * @author allenparslow
 */
public abstract class AbstractConditional<T> implements Conditional<T> {
    private static final long serialVersionUID = 119252890442352505L;
    private final LogicGate logicGate;

    protected AbstractConditional(LogicGate logicGate) {
        this.logicGate = logicGate;
    }

    /**
     * {@inheritDoc}
     */
    public LogicGate getLogicGate() {
        return logicGate;
    }
}
