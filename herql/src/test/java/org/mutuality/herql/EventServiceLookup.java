/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mutuality.herql;

import com.google.inject.Provides;
import org.bushe.swing.event.EventService;



/**
 *
 * @author manzoors
 */
public interface EventServiceLookup {

    @Provides
    EventService getEventServiceBus();

}
