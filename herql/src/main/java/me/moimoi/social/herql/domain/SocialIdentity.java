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

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ManzoorS
 */
@Entity("identity")
public class SocialIdentity {
  
    
    @Id private String loginName;
    private Date joined;
    private Boolean active;
    private Date lastLogin;    
    private String password;
    private Boolean verified;
    
    @Reference
    private List<SocialPerson> profiles = new LinkedList<SocialPerson>();  
    
    public SocialIdentity() {        
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return the joined
     */
    public Date getJoined() {
        return joined;
    }

    /**
     * @param joined the joined to set
     */
    public void setJoined(Date joined) {
        this.joined = joined;
    }

    /**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * @return the lastLogin
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * @param lastLogin the lastLogin to set
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
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
     * @return the profiles
     */
    public List<SocialPerson> getProfiles() {
        return profiles;
    }

    /**
     * @param profiles the profiles to set
     */
    public void setProfiles(List<SocialPerson> profiles) {
        this.profiles = profiles;
    }

    /**
     * @return the verified
     */
    public Boolean getVerified() {
        return verified;
    }

    /**
     * @param verified the verified to set
     */
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

}
