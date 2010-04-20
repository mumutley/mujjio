/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mutuality.herql;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.sun.grizzly.util.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Random;
import junit.framework.TestCase;
import org.bushe.swing.event.EventService;
import org.bushe.swing.event.EventServiceExistsException;
import org.bushe.swing.event.EventServiceLocator;
import org.bushe.swing.event.ThreadSafeEventService;

/**
 *
 * @author manzoors
 */
public class GutTests extends TestCase {

    public GutTests(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testPush() throws EventServiceExistsException {
        EventServiceLocator.setEventService("EventServiceBus", new ThreadSafeEventService());
        Consumer consumer = new Consumer();
        Random r = new Random(System.currentTimeMillis());
        Injector injector = Guice.createInjector(Stage.DEVELOPMENT, new AbstractModule() {

            @Override
            protected void configure() {
                this.bind(EventServiceLookup.class).to(EventBus.class);
            }
        });
        EventService es = injector.getInstance(EventServiceLookup.class).getEventServiceBus();
        ArrayList<MessagePublisher> threads = new ArrayList<MessagePublisher>();
        for (int i = 0; i < 100; i++) {
            MessagePublisher mp = new MessagePublisher(es, this);            
            threads.add(mp);
        }

         for (int i = 0; i < 100; i++) {
             int key = randInRangeInc(r,Integer.valueOf(0),Integer.valueOf(100));
             MessagePublisher m = threads.get(key);
             Thread t = new Thread(m);            
             t.start();            
         }

    }
    private int j = 0;

    public void callback() {
        System.out.println(j++);
    }

    public static int randInRangeInc(Random r, int min, int max) {
        return min + (int) (r.nextDouble() * (max - min));
    }

    public int nextInt(Random r, int lower, int higher) {
        int ran = r.nextInt();
        double x = (double) ran / Integer.MAX_VALUE * (higher - lower);
        return (int) x + lower;
    }
}
