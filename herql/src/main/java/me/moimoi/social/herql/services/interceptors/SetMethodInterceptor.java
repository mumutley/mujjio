/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.services.interceptors;

import com.google.code.morphia.query.UpdateOperations;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.moimoi.social.herql.domain.MutableSocialPerson;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 *
 * @author Suhail
 */
public class SetMethodInterceptor implements MethodInterceptor {

    private static final Logger LOG = Logger.getLogger(SetMethodInterceptor.class.getCanonicalName());           
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {        
        
        if((invocation.getThis() instanceof MutableSocialPerson)) {
            MutableSocialPerson person = (MutableSocialPerson)invocation.getThis();
            Mutator m = invocation.getMethod().getAnnotation(Mutator.class);

            Object arg = invocation.getArguments()[0];            
            UpdateOperations ops =  person.getUpdateOperation();
            if(arg != null) {                
                ops.set(m.name(), arg);
            } else {
                ops.unset(m.name());
            }            
        }                
        return invocation.proceed();
    }    
}