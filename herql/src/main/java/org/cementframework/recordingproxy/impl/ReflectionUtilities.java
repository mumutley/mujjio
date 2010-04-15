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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * General use reflection tools.
 *
 * @author allenparslow
 */
public class ReflectionUtilities {

    private static final Map<Class<?>, Boolean> IMMUTABLE_CLASSES =
            new HashMap<Class<?>, Boolean>();

    private static final Map<String, Method> OBJECT_METHODS;
    static {
        Map<String, Method> methods = new HashMap<String, Method>(Object.class.getMethods().length);
        for (Method method : Object.class.getMethods()) {
            methods.put(method.getName(), method);
        }
        OBJECT_METHODS = Collections.unmodifiableMap(methods);
    }
    static {
        IMMUTABLE_CLASSES.put(boolean.class, true);
        IMMUTABLE_CLASSES.put(byte.class, true);
        IMMUTABLE_CLASSES.put(short.class, true);
        IMMUTABLE_CLASSES.put(int.class, true);
        IMMUTABLE_CLASSES.put(long.class, true);
        IMMUTABLE_CLASSES.put(long.class, true);
        IMMUTABLE_CLASSES.put(double.class, true);
        IMMUTABLE_CLASSES.put(float.class, true);
        IMMUTABLE_CLASSES.put(char.class, true);

        IMMUTABLE_CLASSES.put(Boolean.class, true);
        IMMUTABLE_CLASSES.put(Byte.class, true);
        IMMUTABLE_CLASSES.put(Short.class, true);
        IMMUTABLE_CLASSES.put(Integer.class, true);
        IMMUTABLE_CLASSES.put(Long.class, true);
        IMMUTABLE_CLASSES.put(Double.class, true);
        IMMUTABLE_CLASSES.put(Character.class, true);

        IMMUTABLE_CLASSES.put(String.class, true);

        IMMUTABLE_CLASSES.put(BigDecimal.class, true);
        IMMUTABLE_CLASSES.put(BigInteger.class, true);

        IMMUTABLE_CLASSES.put(java.util.Date.class, true);
        IMMUTABLE_CLASSES.put(Calendar.class, true);
        IMMUTABLE_CLASSES.put(java.sql.Date.class, true);
        IMMUTABLE_CLASSES.put(java.sql.Timestamp.class, true);
        IMMUTABLE_CLASSES.put(java.sql.Time.class, true);

        IMMUTABLE_CLASSES.put(Class.class, true);
    }

    /**
     * Determines if a class is immutable (i.e. only has final fields)
     *
     * @param type
     *            the class to examine.
     * @return True, if the class is immutable.
     */
    public static boolean isImmutable(Class<?> type) {
        // TODO: this method ignores several types of
        // immutability, namely no setter
        Boolean result = IMMUTABLE_CLASSES.get(type);
        if (result == null) {
            result = isImmutableCheck(type);
            IMMUTABLE_CLASSES.put(type, result);
        }
        return result;
    }

    static boolean isImmutableCheck(Class<?> clazz) {

        // check constructor / setters instead?
        for (Field field : clazz.getDeclaredFields()) {
            if (!Modifier.isFinal(field.getModifiers())) {
                return false;
            }
        }
        Class<?> superType = clazz.getSuperclass();
        if (superType != null && !Object.class.equals(superType)) {
            boolean immutable = isImmutableCheck(clazz.getSuperclass());
            if (!immutable) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets a map of fields for the specified class and its super-classes.
     *
     * @param clazz
     *            the class to inspect.
     *
     * @return a field map.
     */
    public static Map<String, Field> getFieldMap(Class<?> clazz) {
        Map<String, Field> fieldMap = new HashMap<String, Field>();

        if (clazz != null && !Object.class.equals(clazz)) {
            populateFieldMap(clazz, fieldMap);
        }

        return fieldMap;
    }

    protected static void populateFieldMap(Class<?> clazz, Map<String, Field> fieldMap) {
        for (Field field : clazz.getDeclaredFields()) {
            fieldMap.put(field.getName(), field);
        }
        if (clazz.getSuperclass() != null
                && !Object.class.equals(clazz.getSuperclass())) {
            populateFieldMap(clazz.getSuperclass(), fieldMap);
        }
    }

    /**
     * Determines if the specified method is present on
     * <code>Object.class</code>.
     *
     * @param method
     *            the method to examine.
     *
     * @return True, if the method is an object method.
     */
    public static boolean isObjectMethod(Method method) {
        if (method == null) {
            return false;
        }
        return OBJECT_METHODS.containsKey(method.getName());
    }

}
