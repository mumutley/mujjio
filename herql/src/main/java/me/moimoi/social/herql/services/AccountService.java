/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.services;

import org.apache.shindig.social.opensocial.model.Account;

/**
 *
 * @author Suhail
 */
public interface AccountService {
    
    /**
     * This operation allows the client to register a new user account.
     * @param account 
     */
    public void register(Account account);
    
    /**
     * <code>find</code> allows the client of the service to find a user by the
     * userId and the domain name.
     * 
     * @param userId is the user id of the principle
     * @param domain is the name of the domain to which this account belongs to.
     * @return An account instance if found
     */
    public Account find(String userId, String domain);
}
