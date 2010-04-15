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
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.cementframework.recordingproxy.api.AliasedProperty;
import org.cementframework.recordingproxy.api.ClassMetaData;
import org.cementframework.recordingproxy.api.PropertyMetaData;

/**
 * Provides property information about a class.
 *
 * @author allenparslow
 */
public class ClassMetaDataUtils {

    private static final Map<Class<?>, ClassMetaData> META_DATA_MAP =
            new HashMap<Class<?>, ClassMetaData>();

    /**
     * Gets property information about the specified class.
     *
     * @param clazz
     *            the class to examine.
     * @return property information.
     */
    public static ClassMetaData getMetaData(Class<?> clazz) {
        ClassMetaData info = META_DATA_MAP.get(clazz);

        if (info == null) {
            info = createClassMetaData(clazz);
            META_DATA_MAP.put(clazz, info);
        }

        return info;
    }

    protected static ClassMetaData createClassMetaData(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Cannot create class metadata: no class specified");
        }

        Map<Method, PropertyMetaData> allProperties = new LinkedHashMap<Method, PropertyMetaData>();
        Map<Method, PropertyMetaData> singularProperties =
                new LinkedHashMap<Method, PropertyMetaData>();
        Map<Method, PropertyMetaData> nestedProperties =
                new LinkedHashMap<Method, PropertyMetaData>();
        Map<Method, PropertyMetaData> collectionProperties =
                new LinkedHashMap<Method, PropertyMetaData>();
        Map<Method, String> aliasedProperties = new HashMap<Method, String>();

        List<PropertyMetaData> readableProperties =
                MethodCallUtils.getReadableProperties(clazz);

        for (PropertyMetaData param : readableProperties) {

            allProperties.put(param.getReadMethod(), param);
            if (isCollection(param)) {
                collectionProperties.put(param.getReadMethod(), param);
            } else if (isNested(param)) {
                nestedProperties.put(param.getReadMethod(), param);
            } else {
                singularProperties.put(param.getReadMethod(), param);
            }

            AliasedProperty alias = param.getAnnotation(AliasedProperty.class);
            if (alias != null) {
                aliasedProperties.put(param.getReadMethod(), alias.value());
            }
        }

        return new ClassMetaDataImpl(
                clazz,
                allProperties,
                singularProperties,
                nestedProperties,
                collectionProperties,
                aliasedProperties);
    }

    protected static boolean isCollection(PropertyMetaData desc) {
        Class<?> type = desc.getReadMethod().getReturnType();
        return Collection.class.isAssignableFrom(type);
    }

    protected static boolean isNested(PropertyMetaData desc) {
        Class<?> type = desc.getReadMethod().getReturnType();
        return !ReflectionUtilities.isImmutable(type);
    }
}
