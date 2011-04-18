/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.service;

import com.google.inject.Inject;
import java.util.logging.Logger;
import net.guts.event.Channel;

/**
 *
 * @author Suhail
 */
public class Supplier {

    static private final Logger log = Logger.getLogger(Supplier.class.getCanonicalName());
    private final Channel<Integer> integerChannel;

    @Inject
    public Supplier(Channel<Integer> channel) {
        integerChannel = channel;
        log.info("init");
        integerChannel.publish(10);
        log.info("publised");
    }
}
