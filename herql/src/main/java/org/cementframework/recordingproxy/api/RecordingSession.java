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

/**
 * A session allowing for the recording of method calls using a proxy instance.
 *
 * @author allenparslow
 */
public interface RecordingSession {

    /**
     * Removes and returns the first call on the call-stack.
     *
     * @return the first call (or null if none).
     */
    RecordedMethodCall getFirstCall();

    /**
     * Clears all calls in the session.
     */
    void clear();

    /**
     * Gets the flag that indicates if there is any calls in the session.
     *
     * @return true, if there is no calls in the session.
     */
    boolean isEmpty();

    /**
     * Removes and returns the first call on the call-stack.
     *
     * <p>
     * throws IllegalStateException if no calls on the call-stack.
     * </p>
     *
     * @return the first call (never null).
     */
    RecordedMethodCall getSafeFirstCall();

    /**
     * Removes and returns the last call on the call-stack.
     *
     * @return the last call (or null if none).
     */
    RecordedMethodCall getLastCall();

    /**
     * Removes and returns the last call on the call-stack.
     *
     * <p>
     * throws IllegalStateException if no calls on the call-stack.
     * </p>
     *
     * @return the last call (never null).
     */
    RecordedMethodCall getSafeLastCall();

    /**
     * Adds a method call to the recording session.
     *
     * @param call
     *            the call to add.
     */
    void addCall(RecordedMethodCall call);

    /**
     * Adds a nested method call to the recording session.
     *
     * @param call
     *            the call to add.
     */
    void addNestedCall(RecordedMethodCall call);
}
