/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.config;

import org.apache.shindig.samples.springexample.spi.MockPersonSpi;
import org.apache.shindig.social.core.config.SocialApiGuiceModule;
import org.apache.shindig.social.opensocial.spi.PersonService;

/**
 *
 * @author ManzoorS
 */
public class SpringGuiceModule extends SocialApiGuiceModule {
    
  /* (non-Javadoc)
   * @see org.apache.shindig.social.core.config.SocialApiGuiceModule#configure()
   */
  @Override
  protected void configure() {
    // Get spring application context
    //ApplicationContext applicationContext = ApplicationContextFactory.getApplicationContext();

    // Bind Mock Person Spi
    //this.bind(PersonService.class).toInstance((PersonService)applicationContext.getBean(PERSON_SPI_BEAN_NAME));

    // Use SocialApiGuiceModule to configure shindig
    this.bind(PersonService.class).to(MockPersonSpi.class);
    super.configure();
  }
}
