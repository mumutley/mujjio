/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.services.interceptors;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 *
 * @author Suhail
 */
public class RegisterSideEffect implements MethodInterceptor {

    private static final Logger LOG = Logger.getLogger(RegisterSideEffect.class.getCanonicalName());           
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        LOG.log(Level.INFO, "RegisterSideEffect {0}", invocation);
        return invocation.proceed();
    }    
}
