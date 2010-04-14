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
package org.cementframework.querybyproxy.shared.impl.model.conditionals;

/**
 * Represent a query operator (=,>,<,in,empty ...).
 *
 * @author allenparslow
 */
public enum ComparisonOperator {
    EXISTS,
    NOT_EXISTS,
    IS_EMPTY,
    IS_NOT_EMPTY,
    MEMBER_OF,
    NOT_MEMBER_OF,
    IS_NULL,
    IS_NOT_NULL,
    NOT_IN,
    IN,
    NOT_LIKE,
    LIKE,
    BETWEEN,
    NOT_BETWEEN,
    EQUALS,
    NOT_EQUALS,
    GREATER_THAN,
    GREATER_THAN_OR_EQUAL_TO,
    LESS_THAN,
    LESS_THAN_OR_EQUAL_TO;
}
