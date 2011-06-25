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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import me.moimoi.social.herql.integration.MessangerService;

/**
 *
 * @author suhail
 */
public class MessangerServiceImpl implements MessangerService {
   
    @Resource(mappedName = "jms/emailQueue")
    private Queue queue;
    @Resource(mappedName = "jms/emailFactory")
    private ConnectionFactory factory;

    @Override
    public void send(String something) {
        Connection connection = null;
        try {            
            
            Context cxt = new InitialContext();
            QueueConnectionFactory fac = (QueueConnectionFactory)cxt.lookup("java:comp/env/jms/emailFactory");
            Queue que = (Queue)cxt.lookup("java:comp/env/jms/emailQueue");
            
            connection = fac.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(que);
            TextMessage message = session.createTextMessage();
            message.setText("Hello World");
            producer.send(message);
        } catch (NamingException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (JMSException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
        }
        //http://forums.oracle.com/forums/thread.jspa?threadID=2220105&tstart=0
    }
    
    private static final Logger LOG = Logger.getLogger(MessangerService.class.getName());
}
