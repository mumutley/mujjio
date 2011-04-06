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
package me.moimoi.social.herql.domain;

import com.google.code.morphia.annotations.Embedded;
import org.apache.shindig.social.opensocial.model.ListField;

/**
 *
 * @author Suhail
 */
@Embedded
public class PluralField implements ListField {
    
    private String value;
    private String type;
    private Boolean primary;
    
    public PluralField() {}
    
    public PluralField(String value, String type, Boolean primary ) {
        this.value = value;
        this.type = type;
        this.primary = primary;
    }        

    /**
     * @return the value
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the primary
     */
    @Override
    public Boolean getPrimary() {
        return primary;
    }

    /**
     * @param primary the primary to set
     */
    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }
}
