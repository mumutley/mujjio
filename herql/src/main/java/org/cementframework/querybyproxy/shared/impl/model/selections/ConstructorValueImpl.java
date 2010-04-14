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

import java.util.List;

import org.cementframework.querybyproxy.shared.api.model.values.StrictQueryValue;
import org.cementframework.querybyproxy.shared.impl.model.values.AbstractQueryValue;

/**
 * A representation of a jpql constuctor [new] syntax.
 *
 * <p>
 * Example: select new com.example.SomeDto(a.id, a.name)
 * </p>
 *
 * @author allenparslow
 * @param <T>
 *            type-inference
 */
public class ConstructorValueImpl<T> extends AbstractQueryValue<T> {

    private static final long serialVersionUID = 9139242785242671851L;
    private final Class<T> constructorClass;
    @SuppressWarnings("unchecked")
    private final List<StrictQueryValue> arguments;

    /**
     * Create a new <code>ConstructorValue</code> instance.
     *
     * @param constructorClass
     *            The type of data-transfer-object that will be produced.
     * @param arguments
     *            Constructor arguments (must match a constructor signature for
     *            the constructor-class).
     */
    @SuppressWarnings("unchecked")
    public ConstructorValueImpl(Class<T> constructorClass, List<StrictQueryValue> arguments) {
        this.constructorClass = constructorClass;
        this.arguments = arguments;
    }

    /**
     * Gets the class that will be used to populate results.
     *
     * @return the class to construct.
     */
    public Class<T> getConstructorClass() {
        return constructorClass;
    }

    /**
     * Gets the arguments to pass to the constructor.
     *
     * @return the constructor arguments.
     */
    @SuppressWarnings("unchecked")
    public List<StrictQueryValue> getArguments() {
        return arguments;
    }

}
