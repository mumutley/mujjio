/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mujjio.server.arc.mail;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author suhail
 */
@MessageDriven(mappedName = "jms/email", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class EmailMessangerBean implements MessageListener {
    
    @Resource
    private MessageDrivenContext context;
    
    public EmailMessangerBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            LOG.log(Level.INFO, "{0}", ((TextMessage)message).getText());
        } catch (JMSException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    private static final Logger LOG = Logger.getLogger(EmailMessangerBean.class.getName());
}
