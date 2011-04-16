/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.config;
import com.google.inject.AbstractModule;
import net.guts.event.Events;

/**
 *
 * @author Suhail
 */
public class MutatorModule extends AbstractModule {

    @Override
    protected void configure() {        
        Events.bindChannel(binder(), Integer.class);                        
    }
    
    
}
