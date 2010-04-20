/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mutuality.herql;

import com.google.inject.Provides;
import org.bushe.swing.event.EventService;

import org.bushe.swing.event.EventServiceLocator;


/**
 *
 * @author manzoors
 */
public class EventBus implements EventServiceLookup {

    public EventBus() {
    }
    
    @Provides @Override
    public EventService getEventServiceBus() {
        return EventServiceLocator.getEventService("EventServiceBus");
    }
}
