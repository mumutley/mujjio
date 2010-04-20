/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mutuality.herql;

import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventSubscriber;

/**
 *
 * @author manzoors
 */

public class Consumer {

    public Consumer(){AnnotationProcessor.process(this);}
    
    @EventSubscriber(eventClass=String.class,eventServiceName="EventServiceBus")
    public void arrive(final String event) {
        //System.out.println("event is " + event);
    }
}
