/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.services;

import com.google.code.morphia.Key;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import me.moimoi.social.herql.domain.SocialPerson;
import org.apache.shindig.social.opensocial.model.Account;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.spi.PersonService;

/**
 *
 * @author Suhail
 */
public interface ProfileService extends PersonService {
    
    
    public Person create();
    
    /**
     * This operation allows the client to register a new user account.
     * @param account to be saved in the system
     * @return the key of the account just saved.
     */
    public Key<Person> register(Person account);
    
    
    /**
     * 
     * @param account 
     */
    public void update(Person account);
    
    /**
     * <code>find</code> allows the client of the service to find a user by the
     * userId and the domain name.
     * 
     * @param userId is the user id of the principle
     * @param domain is the name of the domain to which this account belongs to.
     * @return An account instance if found
     */
    public Account find(String userId, String domain);
    
    /**
     * 
     * @param _id
     * @return 
     */
    public Person find(String _id);

    /**
     * 
     * @return 
     */
    public UpdateOperations<SocialPerson> getUpdateOperation();        
        
}
