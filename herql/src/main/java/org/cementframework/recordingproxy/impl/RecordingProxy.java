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
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.cementframework.recordingproxy.api.ClassMetaData;
import org.cementframework.recordingproxy.api.RecordedMethodCall;
import org.cementframework.recordingproxy.api.RecordingSession;
import org.cementframework.recordingproxy.api.RecordingSessions;

/**
 * A method interceptor that is used to record method calls on the target proxy.
 *
 * @author allenparslow
 */
class RecordingProxy implements MethodInterceptor {

    private final Class<?> clazz;

    private final Object proxy;
    private final String toStringText;

    private RecordedMethodCall parentCall;

    private ClassMetaData metadata;
    private Map<Method, RecordingProxy> nestedCache = new HashMap<Method, RecordingProxy>();

    /**
     * Create a new instance.
     *
     * @param clazz
     *            the target class for the proxy.
     * @param parent
     *            the parent method call (for a nested path expression).
     */
    RecordingProxy(Class<?> clazz) {
        this.clazz = clazz;
        this.proxy = create();
        this.toStringText = "ProxyFor(" + clazz.getSimpleName() + ")@"
                + Integer.toHexString(hashCode()) + "-" + Long.toHexString(new Date().getTime());
    }

    final Object create() {
        metadata = ClassMetaDataUtils.getMetaData(clazz);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        Object proxyInstance = enhancer.create();

        return proxyInstance;
    }

    Object getProxy() {
        return proxy;
    }

    void setParentCall(RecordedMethodCall parentCall) {
        this.parentCall = parentCall;
    }

    /**
     * {@inheritDoc}
     */
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) {

        if ("hashCode".equals((method.getName()))) {
            return super.hashCode();
        }
        if ("toString".equals((method.getName()))) {
            return toStringText;
        }
        if ("equals".equals((method.getName())) && args.length == 1) {
            return toStringText.equals(args[0].toString());
        }

        if (args.length != 0) {
            return null;
        }

        String name = metadata.getName(method);
        if (name == null) {
            return null;
        }

        Class<?> elementType = null;
        boolean nestedCallFlag = false;
        Object nestedProxyObj = null;
        RecordingProxy nestedProxy = null;

        if (metadata.isSingular(method)) {
            elementType = method.getReturnType();
        } else if (metadata.isCollection(method)) {
            nestedCallFlag = true;
            ParameterizedType type = (ParameterizedType)
                    method.getGenericReturnType();
            elementType = (Class<?>) type.getActualTypeArguments()[0];
        } else {
            // by definition must be a nested property
            nestedCallFlag = true;
            elementType = method.getReturnType();

            nestedProxy = nestedCache.get(method);
            if (nestedProxy == null) {
                nestedProxy = new RecordingProxy(method.getReturnType());
                nestedCache.put(method, nestedProxy);
            }
            nestedProxyObj = nestedProxy.getProxy();
        }

        RecordingSession recordingSession = RecordingSessions.get();

        RecordedMethodCall call = new RecordedMethodCallImpl(
                proxy,
                name,
                method,
                elementType,
                parentCall,
                nestedProxyObj);

        if (!nestedCallFlag) {
            recordingSession.addCall(call);
        } else {
            recordingSession.addNestedCall(call);
            if (nestedProxy != null) {
                nestedProxy.setParentCall(call);
            }
        }

        return nestedProxyObj;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return clazz.getSimpleName();
    }
}
