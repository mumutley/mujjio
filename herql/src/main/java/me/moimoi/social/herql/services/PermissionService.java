/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.services;

/**
 *
 * @author Suhail
 */
public interface PermissionService {

    /**
     *
     * @param principle
     * @param role
     */
    void getPermission(String principle, String role);
    
}
