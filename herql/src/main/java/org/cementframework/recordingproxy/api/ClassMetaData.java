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
import java.util.List;
import java.util.Map;

/**
 * Contains information about a class that is useful for query-by-proxy.
 *
 * @author allenparslow
 */
public interface ClassMetaData {
    /**
     * Gets the target class.
     *
     * @return the target class.
     */
    Class<?> getTargetClass();

    /**
     * Gets non-nested, non-collection properties for the target-class.
     *
     * @return the simple properties.
     */
    List<PropertyMetaData> getSingularProperties();

    /**
     * Gets information about a non-nested, non-collection property for the
     * target-class.
     *
     * @param method
     *            The method to obtain information about.
     * @return information about the property.
     */
    PropertyMetaData getSingularProperty(Method method);

    /**
     * Determines if the method represents a singular (non-collection,
     * non-"nestable" property).
     *
     * @param method
     *            the method to inspect.
     *
     * @return true, if the method is for a singular property.
     */
    boolean isSingular(Method method);

    /**
     * Gets all properties [not-ignored]properties for the target-class.
     *
     * @return all properties.
     */
    List<PropertyMetaData> getProperties();

    /**
     * Gets of aliased methods for the target-class.
     *
     * @return the map of aliased methods.
     */
    Map<Method, String> getAliasedPropertyMap();

    /**
     * Gets a name for a method (NOTE: may be aliased).
     *
     * @param method
     *            the method to inspect.
     *
     * @return the name for the method.
     */
    String getName(Method method);

    /**
     * Gets information about a property for the target-class.
     *
     * @param method
     *            The method to obtain information about.
     * @return information about the property.
     */
    PropertyMetaData getProperty(Method method);

    /**
     * Gets nested properties for the target-class.
     *
     * @return the nested properties.
     */
    List<PropertyMetaData> getNestedProperties();

    /**
     * Gets information about a nested property for the target-class.
     *
     * @param method
     *            The method to obtain information about.
     * @return information about the property.
     */
    PropertyMetaData getNestedProperty(Method method);

    /**
     * Determines if the method represents "nestable" property.
     *
     * @param method
     *            the method to inspect.
     * @return true, if the method is for a singular property.
     */
    boolean isNested(Method method);

    /**
     * Gets collection properties for the target-class.
     *
     * @return the simple properties.
     */
    List<PropertyMetaData> getCollectionProperties();

    /**
     * Gets information about a collection property for the target-class.
     *
     * @param method
     *            The method to obtain information about.
     * @return information about the property.
     */
    PropertyMetaData getCollectionProperty(Method method);

    /**
     * Determines if the method represents "collection" property.
     *
     * @param method
     *            the method to inspect.
     * @return true, if the method is for a singular property.
     */
    boolean isCollection(Method method);
}
