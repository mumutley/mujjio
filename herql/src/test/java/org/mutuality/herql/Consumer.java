/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mutuality.herql;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.guts.event.Consumes;

/**
 *
 * @author Suhail
 */
public class Consumer {

    static private final Logger log = Logger.getLogger(Consumer.class.getCanonicalName());

    Consumer() {
        log.info("consumer init");
    }

    @Consumes
    public void accept(Integer event) {
        log.log(Level.INFO, "accepted '{''}'{0}", event);
    }
}
