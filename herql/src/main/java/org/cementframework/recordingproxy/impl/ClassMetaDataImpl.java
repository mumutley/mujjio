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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.cementframework.recordingproxy.api.ClassMetaData;
import org.cementframework.recordingproxy.api.PropertyMetaData;

/**
 * Contains information about a class that is useful for query-by-proxy.
 *
 * @author allenparslow
 */
public class ClassMetaDataImpl implements ClassMetaData {
    private final Class<?> clazz;

    private final Map<Method, PropertyMetaData> properties;
    private final Map<Method, PropertyMetaData> singularProperties;
    private final Map<Method, PropertyMetaData> nestedProperties;
    private final Map<Method, PropertyMetaData> collectionProperties;
    private final Map<Method, String> aliasedPropertyMap;

    /**
     * Create a new <code>ClassMetaData instance</code>.
     *
     * @param clazz
     *            the target-class for the meta-data.
     * @param properties
     *            all properties.
     * @param singularProperties
     *            singular properties.
     * @param nestedProperties
     *            nested properties.
     * @param collectionProperties
     *            collection properties.
     * @param aliasedPropertyMap
     *            property alias map.
     */
    public ClassMetaDataImpl(
            Class<?> clazz,
            Map<Method, PropertyMetaData> properties,
            Map<Method, PropertyMetaData> singularProperties,
            Map<Method, PropertyMetaData> nestedProperties,
            Map<Method, PropertyMetaData> collectionProperties,
            Map<Method, String> aliasedPropertyMap) {
        this.clazz = clazz;
        this.properties = Collections.unmodifiableMap(properties);
        this.singularProperties = Collections.unmodifiableMap(singularProperties);
        this.nestedProperties = Collections.unmodifiableMap(nestedProperties);
        this.collectionProperties = Collections.unmodifiableMap(collectionProperties);
        this.aliasedPropertyMap = Collections.unmodifiableMap(aliasedPropertyMap);
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getTargetClass() {
        return clazz;
    }

    /**
     * {@inheritDoc}
     */
    public List<PropertyMetaData> getSingularProperties() {
        return new ArrayList<PropertyMetaData>(singularProperties.values());
    }

    /**
     * {@inheritDoc}
     */
    public PropertyMetaData getSingularProperty(Method method) {
        return singularProperties.get(method);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSingular(Method method) {
        return getSingularProperty(method) != null;
    }

    /**
     * {@inheritDoc}
     */
    public List<PropertyMetaData> getProperties() {
        return new ArrayList<PropertyMetaData>(properties.values());
    }

    /**
     * {@inheritDoc}
     */
    public Map<Method, String> getAliasedPropertyMap() {
        return aliasedPropertyMap;
    }

    /**
     * {@inheritDoc}
     */
    public String getName(Method method) {
        String name = null;

        PropertyMetaData property = getProperty(method);
        if (property != null) {
            name = property.getName();
        }

        String alias = aliasedPropertyMap.get(method);
        if (alias != null) {
            name = alias;
        }

        return name;
    }

    /**
     * {@inheritDoc}
     */
    public PropertyMetaData getProperty(Method method) {
        return properties.get(method);
    }

    /**
     * {@inheritDoc}
     */
    public List<PropertyMetaData> getNestedProperties() {
        return new ArrayList<PropertyMetaData>(nestedProperties.values());
    }

    /**
     * {@inheritDoc}
     */
    public PropertyMetaData getNestedProperty(Method method) {
        return nestedProperties.get(method);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isNested(Method method) {
        return getNestedProperty(method) != null;
    }

    /**
     * {@inheritDoc}
     */
    public List<PropertyMetaData> getCollectionProperties() {
        return new ArrayList<PropertyMetaData>(collectionProperties.values());
    }

    /**
     * {@inheritDoc}
     */
    public PropertyMetaData getCollectionProperty(Method method) {
        return collectionProperties.get(method);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isCollection(Method method) {
        return getCollectionProperty(method) != null;
    }
}
