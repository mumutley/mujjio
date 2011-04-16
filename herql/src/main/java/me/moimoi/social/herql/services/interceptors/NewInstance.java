/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.services.interceptors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Suhail
 */
@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.METHOD)
public @interface NewInstance {
    
}
