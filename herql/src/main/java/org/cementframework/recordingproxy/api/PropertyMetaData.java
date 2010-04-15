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
/**
 *
 */
package org.cementframework.recordingproxy.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Allows manipulate of a READABLE property.
 *
 * <p>
 * In particular this interface provides a more unified view than
 * <code>Member</code> (for treating annotations on fields or methods)
 * </p>
 *
 * @author allenparslow
 */
public interface PropertyMetaData {

    /**
     * Gets the name of the property (NOTE: may be aliased).
     *
     * @return the property's name.
     */
    String getName();

    /**
     * Returns the Java language modifiers for the method represented by this
     * <code>PropertyMetaData</code> object, as an integer. The
     * <code>Modifier</code> class should be used to decode the modifiers.
     *
     * @see java.lang.reflect.Modifier
     *
     * @return the property's modifiers.
     */
    int getModifiers();

    /**
     * Gets the read method for the property.
     *
     * @return the read method (cannot be null)
     */
    Method getReadMethod();

    /**
     * Returns this element's annotation for the specified type if such an
     * annotation is present, else null.
     *
     * <p>
     * throws NullPointerException if annotationType is null.
     * </p>
     *
     * @param annotationClass
     *            the Class object corresponding to the annotation type
     * @return this element's annotation for the specified annotation type if
     *         present on this element, else null
     */
    boolean isAnnotationPresent(Class<? extends Annotation> annotationClass);

    /**
     * Returns true if an annotation for the specified type is present on this
     * element, else false. This method is designed primarily for convenient
     * access to marker annotations.
     *
     * <p>
     * throws NullPointerException if annotationType is null.
     * </p>
     *
     * @param annotationClass
     *            the Class object corresponding to the annotation type
     * @return true if an annotation for the specified annotation type is
     *         present on this element, else false
     * @param <A>
     *            type-inference
     */
    <A extends Annotation> A getAnnotation(Class<A> annotationClass);
}
