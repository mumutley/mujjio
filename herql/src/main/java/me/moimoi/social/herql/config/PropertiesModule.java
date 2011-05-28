/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.config;

import com.google.inject.AbstractModule;
import com.google.inject.CreationException;
import com.google.inject.name.Names;
import com.google.inject.spi.Message;

import org.apache.commons.io.IOUtils;
import org.apache.shindig.common.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 *
 * @author suhail
 */
public class PropertiesModule extends AbstractModule {

    private final static String DEFAULT_PROPERTIES = "herql.properties";
    private final Properties properties;

    public PropertiesModule() {
        super();
        this.properties = readPropertyFile(getDefaultPropertiesPath());
    }

    public PropertiesModule(String propertyFile) {
        this.properties = readPropertyFile(propertyFile);
    }

    public PropertiesModule(Properties properties) {
        this.properties = properties;
    }

    @Override
    protected void configure() {
        this.binder().bindConstant().annotatedWith(Names.named("shindig.contextroot")).to(getContextRoot());
        Names.bindProperties(this.binder(), getProperties());
        // This could be generalized to inject any system property...
        this.binder().bindConstant().annotatedWith(Names.named("shindig.port")).to(getServerPort());
        this.binder().bindConstant().annotatedWith(Names.named("shindig.host")).to(getServerHostname());
    }

    /**
     * Should return the context root where the current web module is deployed with.  Useful for testing and working out of the box configs.
     * If not set uses fixed value of "".
     * @return an context path as a string.
     */
    private String getContextRoot() {
        return System.getProperty("shindig.contextroot") != null ? System.getProperty("shindig.contextroot") : "";
    }

    /**
     * Should return the port that the current server is running on.  Useful for testing and working out of the box configs.
     * Looks for a port in system properties "shindig.port" then "jetty.port", if not set uses fixed value of "8080"
     * @return an integer port number as a string.
     */
    protected String getServerPort() {
        return System.getProperty("shindig.port") != null ? System.getProperty("shindig.port")
                : System.getProperty("jetty.port") != null ? System.getProperty("jetty.port")
                : "8080";
    }

    /*
     * Should return the hostname that the current server is running on.  Useful for testing and working out of the box configs.
     * Looks for a hostname in system properties "shindig.host", if not set uses fixed value of "localhost"
     * @return a hostname
     */
    protected String getServerHostname() {
        return System.getProperty("shindig.host") != null ? System.getProperty("shindig.host")
                : System.getProperty("jetty.host") != null ? System.getProperty("jetty.host")
                : "localhost";
    }

    protected static String getDefaultPropertiesPath() {
        return DEFAULT_PROPERTIES;
    }

    protected Properties getProperties() {
        return properties;
    }

    private Properties readPropertyFile(String propertyFile) {
        Properties properties = new Properties();
        InputStream is = null;
        String contextRoot = getContextRoot();
        try {
            is = ResourceLoader.openResource(propertyFile);
            properties.load(is);

            String value = null;
            for (Object key : properties.keySet()) {
                value = (String) properties.get((String) key);
                if (value != null && value.indexOf("%contextRoot%") >= 0) {
                    properties.put(key, value.replace(("%contextRoot%"), contextRoot));
                }
            }

        } catch (IOException e) {
            throw new CreationException(Arrays.asList(
                    new Message("Unable to load properties: " + propertyFile)));
        } finally {
            IOUtils.closeQuietly(is);
        }

        return properties;
    }
}
