/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.services;

import com.google.code.morphia.Datastore;

/**
 *
 * @author Suhail
 */
public interface SimpleDatasource {
    
    public Datastore getDataSource();
}
