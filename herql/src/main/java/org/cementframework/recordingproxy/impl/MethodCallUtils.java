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
package org.cementframework.recordingproxy.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.cglib.proxy.Enhancer;

import org.cementframework.recordingproxy.api.PropertyMetaData;
import org.cementframework.recordingproxy.api.RecordedMethodCall;
import org.cementframework.recordingproxy.api.RecordingSessions;

/**
 * Utilities for obtaining information about properties.
 *
 * @author allenparslow
 */
public class MethodCallUtils {

    /**
     * Returns information about all of the readable properties in the specified
     * class.
     *
     * @param clazz
     *            the class to inspect.
     *
     * @return a list of information about readable properties.
     */
    public static List<PropertyMetaData> getReadableProperties(Class<?> clazz) {
        try {
            BeanInfo info = Introspector.getBeanInfo(clazz);

            Map<String, Field> fieldMap = ReflectionUtilities.getFieldMap(clazz);

            List<PropertyMetaData> readableProperties = new ArrayList<PropertyMetaData>();
            for (PropertyDescriptor propertyDescriptor : info.getPropertyDescriptors()) {
                if (propertyDescriptor.getReadMethod() != null
                        && !ReflectionUtilities.isObjectMethod(
                        propertyDescriptor.getReadMethod())) {

                    Field field = fieldMap.get(propertyDescriptor.getName());

                    PropertyMetaData propertyMetaData = new PropertyMetaDataImpl(
                            propertyDescriptor.getName(),
                            propertyDescriptor.getReadMethod(),
                            field);

                    readableProperties.add(propertyMetaData);
                }
            }

            return Collections.unmodifiableList(readableProperties);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Gets the name of a invoked method.
     *
     * @param recordedMethodClass
     *            a recorded method call.
     *
     * @return the name of the method invoked
     */
    public static String methodName(Object recordedMethodClass) {
        return RecordingSessions.get().getSafeLastCall().getName();
    }

    /**
     * Gets the last invoked method.
     *
     * @param recordedMethodClass
     *            a recorded method call.
     *
     * @return the method invoked.
     */
    public static Method method(Object recordedMethodClass) {
        return RecordingSessions.get().getSafeLastCall().getMethod();
    }

    /**
     * Create a new recording-proxy instance of the specified type.
     *
     * @param <T>
     *            type-inference.
     * @param clazz
     *            the type of proxy to create.
     *
     * @return the created recording proxy.
     */
    @SuppressWarnings("unchecked")
    public static <T> T proxy(Class<T> clazz) {
        RecordingProxy proxy = new RecordingProxy(clazz);

        return (T) proxy.getProxy();
    }

    /**
     * Determines if specified instance is a proxy.
     *
     * @param instance
     *            the instance to examine.
     * @return true, if the instance was a proxy.
     */
    public static boolean isProxy(Object instance) {
        return instance != null && Enhancer.isEnhanced(instance.getClass());
    }

    /**
     * Find the root proxy for the specified method-call.
     *
     * @param call
     *            the method-call to examine.
     * @return the root proxy.
     */
    public static Object findRoot(RecordedMethodCall call) {
        List<RecordedMethodCall> calls = listCalls(call);
        return calls.get(0).getInvokingProxy();
    }

    /**
     * Lists the call path for the specified call.
     *
     * <p>
     * Note: Most recent call will be last
     * </p>
     *
     * @param call
     *            the call to examine.
     * @return the call path (with element zero being the root call).
     */
    public static List<RecordedMethodCall> listCalls(RecordedMethodCall call) {
        List<RecordedMethodCall> calls = listParentCalls(call);
        calls.add(call);

        return calls;
    }

    /**
     * Lists the call path for the specified call.
     *
     * <p>
     * Note: excludes the current call
     * </p>
     * <p>
     * Note: Most recent call will be last
     * </p>
     *
     * @param call
     *            the call to examine.
     * @return the call path (with element zero being the root call).
     */
    public static List<RecordedMethodCall> listParentCalls(RecordedMethodCall call) {
        LinkedList<RecordedMethodCall> calls = new LinkedList<RecordedMethodCall>();
        RecordedMethodCall parent = call.getParent();
        while (parent != null) {
            calls.addFirst(parent);

            parent = parent.getParent();
        }

        return calls;
    }

    /**
     * Creates a nested path string for a list of method calls.
     *
     * @param calls
     *            the calls to use.
     * @return the nested path for the calls.
     */
    public static String nestedPath(List<RecordedMethodCall> calls) {
        StringBuilder path = new StringBuilder();

        for (RecordedMethodCall call : calls) {
            path.append(call.getPathName());
        }

        return path.toString();
    }

    /**
     * Gets the nested path for a method call.
     *
     * @param call
     *            the call to examine.
     * @return the nested path for the call.
     */
    public static String nestedPath(RecordedMethodCall call) {
        StringBuilder path = new StringBuilder();
        RecordedMethodCall parent = call.getParent();
        while (parent != null) {
            path.insert(0, parent.getPathName());

            parent = parent.getParent();
        }

        return path.toString();
    }

    /**
     * Gets the path for the method call.
     *
     * @param methodCall
     *            the call to examine.
     * @return the path text.
     */
    public static String path(RecordedMethodCall methodCall) {
        return path(listCalls(methodCall));
    }

    /**
     * Gets the path for a list of method calls.
     *
     * @param calls
     *            the calls to examine.
     * @return the path text.
     */
    public static String path(List<RecordedMethodCall> calls) {
        StringBuilder path = new StringBuilder();

        boolean first = true;
        for (RecordedMethodCall call : calls) {
            String pathText;
            if (first) {
                pathText = call.getName();
            } else {
                pathText = "." + call.getName();
            }
            first = false;

            path.append(pathText);
        }

        return path.toString();
    }

    /**
     * Validates that the specified instance is a proxy.
     *
     * @param instance
     *            the instance to examine.
     * @param baseMessage
     *            the base-message if there was a problem.
     */
    public static void validateIsProxy(Object instance, String baseMessage) {
        if (instance == null) {
            throw new IllegalArgumentException(baseMessage + " is required");
        }
        if (!Enhancer.isEnhanced(instance.getClass())) {
            throw new IllegalStateException(baseMessage + " is not a proxy instance");
        }
    }

}
