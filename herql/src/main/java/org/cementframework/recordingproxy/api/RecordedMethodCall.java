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
package org.cementframework.recordingproxy.api;

import java.lang.reflect.Method;

/**
 * Represents a recorded method call invoked by a proxy.
 *
 * @author allenparslow
 */
public interface RecordedMethodCall {

    /**
     * Gets the proxy for the method call.
     *
     * @return the target proxy.
     */
    Object getInvokingProxy();

    /**
     * Gets the nested parent for the target proxy.
     *
     * @return the nested parent proxy (or null if none).
     */
    RecordedMethodCall getParent();

    /**
     * Get the method that was invoked.
     *
     * @return the invoked method.
     */
    Method getMethod();

    /**
     * Gets the name of the property invoked (NOTE: may be aliased).
     *
     * @return the property's name.
     */
    String getName();

    /**
     * Gets the path name of the property invoked (NOTE: may be aliased).
     *
     * @return the property's name.
     */
    String getPathName();

    /**
     * Gets the element type for the call.
     *
     * <p>
     * if a list was returned, the element-type is the list's element-type
     * </p>
     *
     * <p>
     * Example if method is "List<Foo> getList()", then element type is "Foo"
     * </p>
     *
     * @return the element-type.
     */
    Class<?> getElementType();

    /**
     * Get the proxy returned by the method call.
     *
     * @return the proxy returned by the method call.
     */
    Object getResultingProxy();
}
