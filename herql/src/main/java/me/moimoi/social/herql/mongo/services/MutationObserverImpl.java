/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.mongo.services;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.moimoi.social.herql.services.MutationObserver;
import net.guts.event.Consumes;
import org.apache.shindig.social.opensocial.model.Person;

/**
 *
 * @author Suhail
 */
public class MutationObserverImpl implements MutationObserver {

    static private final Logger LOG = Logger.getLogger(MutationObserverImpl.class.getCanonicalName());
    
    public MutationObserverImpl() {
        LOG.info("MutationObserverImpl init");
    }
    
    @Override @Consumes
    public void register(Person person) {
        LOG.log(Level.INFO, "person here {0}", person);
    }
    
}
