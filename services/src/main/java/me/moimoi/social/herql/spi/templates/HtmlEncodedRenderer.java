/*
 * Copyright 2011 The Apache Software Foundation.
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
package me.moimoi.social.herql.spi.templates;

import java.util.Locale;
import org.stringtemplate.v4.AttributeRenderer;

/**
 *
 * @author suhail
 */
public class HtmlEncodedRenderer implements AttributeRenderer {

    @Override
    public String toString(Object o, String string, Locale locale) {
        return HtmlUtils.htmlEscape(o.toString());
    }
}
