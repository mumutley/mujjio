/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.config.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import me.moimoi.social.herql.config.ApplicationContextFactory;

/**
 *
 * @author ManzoorS
 */
public class ApplicationServletContextListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent event) {}

    @Override
    public void contextDestroyed(ServletContextEvent event) {
      ApplicationContextFactory.getApplicationContext().destroy();
    }
}
