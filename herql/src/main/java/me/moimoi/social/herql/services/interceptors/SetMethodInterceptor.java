/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.services.interceptors;

import com.google.code.morphia.query.UpdateOperations;
import java.lang.reflect.Method;
import java.util.logging.Logger;
import me.moimoi.social.herql.domain.mutable.MutableSocialPerson;
import me.moimoi.social.herql.services.MutableObject;
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
            MutableObject person = (MutableObject)invocation.getThis();
            Mutator m = invocation.getMethod().getAnnotation(Mutator.class);
            Object arg = invocation.getArguments()[0];            
            UpdateOperations ops =  person.getUpdateOperation();
            if(arg != null) {                                
                if(m.compound()) {
                    invocation.proceed();
                    String methodName =  m.accessor();
                    Class prams[] = new Class[0];                    
                    Method method = invocation.getThis().getClass().getMethod(methodName, prams);
                    arg = method.invoke(invocation.getThis(), new Object[0]);
                }
                ops.set(m.name(), arg);
            } else {
                ops.unset(m.name());
            }            
        }                
        return invocation.proceed();
    }    
}
