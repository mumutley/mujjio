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
package me.moimoi.social.herqlweb.forms;

/**
 *
 * @author suhail
 */
public class JoinForm {
    
    private String email;
    private String password;
    private String givenName;
    private String familyName;
    private String gender;
    private String language;
    private String dd;
    private String mm;
    private String yyyy;
    private String noage;
    
    public JoinForm(){}

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the givenName
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * @param givenName the givenName to set
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the dd
     */
    public String getDd() {
        return dd;
    }

    /**
     * @param dd the dd to set
     */
    public void setDd(String dd) {
        this.dd = dd;
    }

    /**
     * @return the mm
     */
    public String getMm() {
        return mm;
    }

    /**
     * @param mm the mm to set
     */
    public void setMm(String mm) {
        this.mm = mm;
    }

    /**
     * @return the noage
     */
    public String getNoage() {
        return noage;
    }

    /**
     * @param noage the noage to set
     */
    public void setNoage(String noage) {
        this.noage = noage;
    }

    /**
     * @return the familyName
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * @param familyName the familyName to set
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * @return the yyyy
     */
    public String getYyyy() {
        return yyyy;
    }

    /**
     * @param yyyy the yyyy to set
     */
    public void setYyyy(String yyyy) {
        this.yyyy = yyyy;
    }
    
}
