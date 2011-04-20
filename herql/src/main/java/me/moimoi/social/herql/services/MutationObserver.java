/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.services;

import me.moimoi.social.herql.domain.SocialPerson;

/**
 *
 * @author Suhail
 */
public interface MutationObserver {
    
    public void register(SocialPerson person);
}
