/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.services;

import org.apache.shindig.social.opensocial.model.MutablePerson;

/**
 *
 * @author Suhail
 */
public interface MutationObserver {
    
    public void register(MutablePerson person);
}
