/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.services;

import me.moimoi.social.herql.domain.Registration;
import org.apache.shindig.social.opensocial.model.Account;
import org.apache.shindig.social.opensocial.model.Person;

/**
 *
 * @author Suhail
 */
public interface AccountService {
       
    
    /**
     * <code>find</code> allows the client of the service to find a user by the
     * userId and the domain name.
     * 
     * @param userId is the user id of the principle
     * @param domain is the name of the domain to which this account belongs to.
     * @return An account instance if found
     */
    Account find(String userId, String domain);

     /**
     * This operation allows the client to register a new user account.
     * @param account 
     */
    Person register(Person person);
}
