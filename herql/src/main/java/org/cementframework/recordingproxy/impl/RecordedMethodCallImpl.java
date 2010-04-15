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
package org.cementframework.recordingproxy.impl;

import java.lang.reflect.Method;

import org.cementframework.recordingproxy.api.RecordedMethodCall;

/**
 * Represents a recorded method call.
 *
 * @author allenparslow
 */
class RecordedMethodCallImpl implements RecordedMethodCall {
    private final Object invokingProxy;
    private final String name;
    private final String pathName;
    private final Method method;
    private final Class<?> elementType;
    private final RecordedMethodCall parent;
    private final Object resultingProxy;

    RecordedMethodCallImpl(
            Object invokingProxy,
            String name,
            Method method,
            Class<?> elementType,
            RecordedMethodCall parent,
            Object resultingProxy) {
        this.invokingProxy = invokingProxy;
        this.name = name;
        this.pathName = name + ".";
        this.method = method;
        this.elementType = elementType;
        this.parent = parent;
        this.resultingProxy = resultingProxy;
    }

    /**
     * {@inheritDoc}
     */
    public Object getInvokingProxy() {
        return invokingProxy;
    }

    /**
     * {@inheritDoc}
     */
    public RecordedMethodCall getParent() {
        return parent;
    }

    /**
     * {@inheritDoc}
     */
    public Method getMethod() {
        return method;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getElementType() {
        return elementType;
    }

    /**
     * {@inheritDoc}
     */
    public String getPathName() {
        return pathName;
    }

    public Object getResultingProxy() {
        return resultingProxy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return method.toString();
    }
}
