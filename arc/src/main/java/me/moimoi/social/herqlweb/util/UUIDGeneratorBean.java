/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herqlweb.util;

import java.util.UUID;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

/**
 *
 * @author suhail
 */
@Stateless
public class UUIDGeneratorBean implements UUIDGenerator {

    @Asynchronous
    @Override
    public Future<String> getUUID() {        
        return new AsyncResult<String>(UUID.randomUUID().toString());
    }
    
    private static final Logger LOG = Logger.getLogger(UUIDGeneratorBean.class.getName());
}
