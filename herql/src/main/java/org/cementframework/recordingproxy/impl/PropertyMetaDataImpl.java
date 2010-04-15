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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.cementframework.recordingproxy.api.PropertyMetaData;

/**
 * Information about a JPA entity property.
 *
 * @author allenparslow
 */
class PropertyMetaDataImpl implements PropertyMetaData {

    public static final Object[] EMPTY_ARGS = new Object[0];

    private final String name;
    private final Method readMethod;
    private final Field field;

    PropertyMetaDataImpl(
            String name,
            Method readMethod,
            Field field) {
        this.name = name;
        this.readMethod = readMethod;
        this.field = field;
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
    public Method getReadMethod() {
        return readMethod;
    }

    /**
     * {@inheritDoc}
     */
    public int getModifiers() {
        return readMethod.getModifiers();
    }

    /**
     * {@inheritDoc}
     */
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        T annotation = readMethod.getAnnotation(annotationClass);

        if (annotation == null && field != null) {
            annotation = field.getAnnotation(annotationClass);
        }

        return annotation;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        boolean found = readMethod.isAnnotationPresent(annotationClass);

        if (!found && field != null) {
            found = field.isAnnotationPresent(annotationClass);
        }

        return found;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name;
    }

}
