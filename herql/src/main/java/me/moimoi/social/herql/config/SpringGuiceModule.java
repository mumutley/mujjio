/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.config;

import org.apache.shindig.social.core.config.SocialApiGuiceModule;
import org.apache.shindig.social.opensocial.spi.PersonService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author ManzoorS
 */
public class SpringGuiceModule extends SocialApiGuiceModule {
    
     /** The Constant PERSON_SPI_BEAN_NAME. */
  private static final String PERSON_SPI_BEAN_NAME = "personSpi";

  /* (non-Javadoc)
   * @see org.apache.shindig.social.core.config.SocialApiGuiceModule#configure()
   */
  @Override
  protected void configure() {
    // Get spring application context
    ApplicationContext applicationContext = ApplicationContextFactory.getApplicationContext();

    // Bind Mock Person Spi
    this.bind(PersonService.class).toInstance((PersonService)applicationContext.getBean(PERSON_SPI_BEAN_NAME));

    // Use SocialApiGuiceModule to configure shindig
    super.configure();
  }
}
