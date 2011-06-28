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
package me.moimoi.social.herql.templates;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import me.moimoi.social.herql.spi.templates.HtmlEncodedRenderer;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.stringtemplate.v4.AttributeRenderer;
import org.stringtemplate.v4.NoIndentWriter;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.StringRenderer;

/**
 *
 * @author suhail
 */
public class TemplateTests {

    public TemplateTests() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }   

    @Test
    public void getTemplateCache() throws FileNotFoundException, IOException {
        InputStream fis = new FileInputStream(new File("src/main/resources/ehcacheConfig.xml").getAbsolutePath());
        CacheManager manager = new CacheManager(fis);

        Cache c = manager.getCache("templates");
        c.put(new Element("verify", "give me a break"));

        System.out.println(c.getName());
        System.out.println(c.get("verify").getValue());
        
        
        String dir = "/Users/suhail/NetBeansProjects/Herql/services/src/main/resources/templates";
        STGroupDir std = new STGroupDir(dir);
        ST st = std.getInstanceOf("mujjio/something");
        
        //AttributeRenderer htmlEncodedRenderer = new HtmlEncodedRenderer();
        //std.registerRenderer(String.class,  htmlEncodedRenderer);
        
        //StringWriter sw = new StringWriter();
        //NoIndentWriter w = new NoIndentWriter(sw);
        //st.write(w); // same as render() except with a different writer
        //String result = sw.toString();

        st.add("name", "suhail");
        
        //System.out.println(result);
        System.out.println();
        
        System.out.println(st.render());
        //TemplateService ts = new StringTemplateService(dir);
        //ts.getTemplate("", "verify");
    }
}