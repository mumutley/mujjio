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

import java.util.LinkedList;

import org.cementframework.recordingproxy.api.RecordedMethodCall;
import org.cementframework.recordingproxy.api.RecordingSession;

/**
 * A session allowing for the recording of method calls using a proxy instance.
 *
 * @author allenparslow
 */
public class RecorderSessionImpl implements RecordingSession {

    private final LinkedList<RecordedMethodCall> callStack = new LinkedList<RecordedMethodCall>();
    private final LinkedList<RecordedMethodCall> nestedCallStack =
            new LinkedList<RecordedMethodCall>();

    /**
     * {@inheritDoc}
     */
    public void clear() {
        callStack.clear();
        nestedCallStack.clear();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        if (!callStack.isEmpty()) {
            return false;
        }
        if (!nestedCallStack.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void addNestedCall(RecordedMethodCall call) {
        nestedCallStack.add(call);
    }

    /**
     * {@inheritDoc}
     */
    public void addCall(RecordedMethodCall call) {
        callStack.add(call);
    }

    /**
     * {@inheritDoc}
     */
    public RecordedMethodCall getFirstCall() {
        RecordedMethodCall call = callStack.poll();

        if (call != null) {
            nestedCallStack.clear();
        } else {
            call = nestedCallStack.poll();
            nestedCallStack.clear();
        }

        return call;
    }

    /**
     * {@inheritDoc}
     */
    public RecordedMethodCall getSafeFirstCall() {
        RecordedMethodCall call = getFirstCall();

        if (call == null) {
            throw new IllegalStateException("No proxy calls were recorded");
        }
        return call;
    }

    /**
     * {@inheritDoc}
     */
    public RecordedMethodCall getLastCall() {
        RecordedMethodCall call = null;

        if (callStack.size() != 0) {
            call = callStack.removeLast();
        }

        if (call != null) {
            nestedCallStack.clear();
        } else if (nestedCallStack.size() != 0) {
            call = nestedCallStack.removeLast();
            nestedCallStack.clear();
        }

        return call;
    }

    /**
     * {@inheritDoc}
     */
    public RecordedMethodCall getSafeLastCall() {
        RecordedMethodCall call = getLastCall();
        if (call == null) {
            throw new IllegalStateException("No proxy calls were recorded");
        }
        return call;
    }
}
