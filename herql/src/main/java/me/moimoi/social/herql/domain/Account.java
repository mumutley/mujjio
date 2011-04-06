/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.domain;

import com.google.code.morphia.annotations.Embedded;

/**
 *
 * @author Suhail
 */
@Embedded
public class Account {

    private String userId;
    private String username;
    private String domain;
    private String password;

    public Account() {
      //NOOP  
    }
    
    public static Account create(String userId, String userName, String domain) {
        return new Account(userId, userName, domain);
    }
    
    public Account(String userId, String userName, String domain) {
        setUserId(userId);
        setDomain(domain);
        setUsername(userName);
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public final void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public final void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the domain to set
     */
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
        if (obj instanceof Account) {
            Account that = (Account) obj;
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
