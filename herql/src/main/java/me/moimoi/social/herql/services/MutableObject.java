/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.services;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.UpdateOperations;
import org.apache.shindig.social.opensocial.model.Person;

/**
 *
 * @author Suhail
 */
public interface MutableObject {
    
    /**
     * 
     * @param delegate 
     */
    public void setDelegate(Person delegate);    
    
    /**
     * 
     */
    public void setUpdateOperations(UpdateOperations update);
    
    /**
     * 
     * @return 
     */
    public UpdateOperations getUpdateOperation();
}
