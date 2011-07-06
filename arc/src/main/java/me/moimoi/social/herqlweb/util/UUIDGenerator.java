/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herqlweb.util;

import java.util.concurrent.Future;
import javax.ejb.Local;

/**
 *
 * @author suhail
 */
@Local
public interface UUIDGenerator {
    public Future<String> getUUID();
}
