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
package org.cementframework.querybyproxy.shared.impl.model;

import org.cementframework.querybyproxy.shared.api.model.QueryStaticTextOption;

/**
 * Represent a query-fragment of static text.
 *
 * @author allenparslow
 */
public class QueryStaticText implements QueryStaticTextOption {

    private static final long serialVersionUID = 3930692046584393564L;
    private final String text;

    /**
     * Create a new <code>QueryStaticText</code> instance.
     *
     * @param text
     *            the static text to render.
     */
    public QueryStaticText(String text) {
        this.text = text;
    }

    /**
     * The text to render.
     *
     * @return text.
     */
    public String name() {
        return text;
    }
}
