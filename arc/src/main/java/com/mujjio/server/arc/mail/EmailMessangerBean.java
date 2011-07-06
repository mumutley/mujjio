/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mujjio.server.arc.mail;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author suhail
 */
@MessageDriven(mappedName = "jms/emailQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class EmailMessangerBean implements MessageListener {
    
    @Resource(name = "mail/google")
    javax.mail.Session mail;
    
    @Resource
    private MessageDrivenContext context;
    
    public EmailMessangerBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            MapMessage map = (MapMessage)message;
            String msg = map.getString("MESSAGE");
            String subject = map.getString("SUBJECT");
            String recipient = map.getString("RECIPIENT");

            MimeMessage email = new MimeMessage(mail);
            email.setFrom(new InternetAddress(FROM));
            email.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recipient));
            email.setSubject(subject);
            email.setText(msg);        
            
            email.setHeader("X-Mailer", "mu-mail");
            email.setSentDate(new Date());
            
            //LOG.log(Level.INFO, "message is {0}", msg);
            //Transport.send(email);
            
        } catch (MessagingException ex) {
            Logger.getLogger(EmailMessangerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    private static final String FROM = "suhailski@gmail.com";    
    private static final Logger LOG = Logger.getLogger(EmailMessangerBean.class.getName());
}
