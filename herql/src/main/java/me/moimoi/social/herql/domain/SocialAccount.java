/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.domain;

import com.google.code.morphia.annotations.Embedded;
import org.apache.shindig.social.opensocial.model.Account;

/**
 *
 * @author Suhail
 */
@Embedded
public class SocialAccount implements Account {

    private String userId;
    private String username;
    private String domain;
    private String password;

    public SocialAccount() {
      //NOOP for mongo
    }
    
    public static SocialAccount create(String userId, String userName, String domain, String password) {
        return new SocialAccount(userId, userName, domain, password);
    }
    
    private SocialAccount(String userId, String userName, String domain, String password) {
        setUserId(userId);
        setDomain(domain);
        setUsername(userName);
    }

    /**
     * @return the userId
     */
    @Override
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    @Override
    public final void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    @Override
    public final void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the domain
     */
    @Override
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the domain to set
     */
    @Override
    public final void setDomain(String domain) {
        this.domain = domain;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SocialAccount) {
            SocialAccount that = (SocialAccount) obj;
            return this.getDomain().equals(that.getDomain())
                    && this.getUserId().equals(that.getUserId())
                    && this.getUsername().equals(that.getUsername());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = domain != null ? domain.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
