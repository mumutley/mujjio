/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.config;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ManzoorS
 */
public class ApplicationContextFactory {
    
    /** The Constant LOCATIONS. */
  private static final String[] LOCATIONS = {"application-context.xml"};

  /** The Constant CONTEXT. */
  private static final AbstractApplicationContext CONTEXT = new ClassPathXmlApplicationContext(LOCATIONS);

  /**
   * Instantiates a new application context factory.
   */
  private ApplicationContextFactory() {
  }

  /**
   * Gets the application context.
   *
   * @return the application context
   */
  public static AbstractApplicationContext getApplicationContext() {
    return CONTEXT;
  }
}
