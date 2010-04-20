/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mutuality.herql;

import com.google.inject.Injector;
import org.bushe.swing.event.EventService;



/**
 *
 * @author manzoors
 */
public class MessagePublisher implements Runnable {
    private EventService es;
    private GutTests i;
    public MessagePublisher(EventService es, GutTests i){
        this.es = es;
        this.i = i;
    }
    
    @Override public void run() {
        i.callback();
        es.publish(new String("Hello World"));        
    }
}
