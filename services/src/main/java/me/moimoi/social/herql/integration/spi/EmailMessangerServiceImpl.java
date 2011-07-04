/*
 * Copyright 2011 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.moimoi.social.herql.integration.spi;

import com.google.inject.Singleton;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import me.moimoi.social.herql.integration.MessangerService;

/**
 *
 * @author suhail
 */
@Singleton
public class EmailMessangerServiceImpl implements MessangerService {

    private String msg;
    private String recipient;
    private String subject;
    private QueueConnectionFactory fac;
    private Queue que;

    public EmailMessangerServiceImpl() {
    }

    @PostConstruct
    public void setUp() {
        try {
            LOG.log(Level.INFO, "this should be a singleton {0}", this.toString());
            Context cxt = new InitialContext();
            fac = (QueueConnectionFactory) cxt.lookup("java:comp/env/jms/emailFactory");
            que = (Queue) cxt.lookup("java:comp/env/jms/emailQueue");
        } catch (NamingException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void send() {
        Connection connection = null;
        try {

            connection = fac.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(que);
            MapMessage message = session.createMapMessage();
            message.setString(MESSAGE, getMsg());
            message.setString(SUBJECT, getSubject());
            message.setString(RECIPIENT, getRecipient());

            producer.send(message);

        } catch (JMSException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void work() {
        this.send();
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @return the recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * @param recipient the recipient to set
     */
    @Override
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * @param subject the subject to set
     */
    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    private static final String MESSAGE = "MESSAGE";
    private static final String SUBJECT = "SUBJECT";
    private static final String RECIPIENT = "RECIPIENT";
    private static final Logger LOG = Logger.getLogger(MessangerService.class.getName());
    //http://forums.oracle.com/forums/thread.jspa?threadID=2220105&tstart=0        
}
