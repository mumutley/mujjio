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
    this.bind(PersonService.class).to(MockPersonSpi.class);
    super.configure();
  }
}
