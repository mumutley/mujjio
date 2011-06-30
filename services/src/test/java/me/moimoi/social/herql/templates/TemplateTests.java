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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import me.moimoi.social.herql.spi.templates.StringTemplateService;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;
import org.junit.Test;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupDir;

/**
 *
 * @author suhail
 */
public class TemplateTests {

    private static final String dir = "src/main/resources/templates";
    private static final Logger LOG = Logger.getAnonymousLogger();
    
    public TemplateTests() {
    }
    
    public void testRhino() {
        ScriptEngineManager sem = new ScriptEngineManager();
        List list = sem.getEngineFactories();
        ScriptEngineFactory f;
        for (int i = 0; i < list.size(); i++) {
            f = (ScriptEngineFactory) list.get(i);
            String engineName = f.getEngineName();
            String engineVersion = f.getEngineVersion();
            String langName = f.getLanguageName();
            String langVersion = f.getLanguageVersion();
            LOG.log(Level.INFO, "{0} {1} {2} {3}", new Object[]{engineName, engineVersion, langName, langVersion});
        }
        ScriptEngine se = sem.getEngineByName("rhino-nonjdk");
        f = se.getFactory();
        String engineName = f.getEngineName();
        String engineVersion = f.getEngineVersion();
        String langName = f.getLanguageName();
        String langVersion = f.getLanguageVersion();
        LOG.log(Level.INFO, "-> {0} {1} {2} {3}", new Object[]{engineName, engineVersion, langName, langVersion});


    }

    @Test
    public void applyWithoutCache() throws FileNotFoundException, IOException {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        STGroupDir std = new STGroupDir(dir);
        ST st = std.getInstanceOf("mujjio/something");
        st.add("name", "suhail");        
        stopwatch.stop();
        LOG.log(Level.INFO, "time t0 {0}", stopwatch.toString());
    }

    @Test
    public void applyWithCaching() throws IOException {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        StringTemplateService sts = new StringTemplateService(dir);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "suhail");
        sts.apply("mujjio", "something", params);
        stopwatch.stop();
        LOG.log(Level.INFO, "time t1 {0}", stopwatch.toString());

        stopwatch.reset();
        stopwatch.start();
        params = new HashMap<String, Object>();
        params.put("name", "suhail");

        sts.apply("mujjio", "something", params);
        stopwatch.stop();
        LOG.log(Level.INFO, "time t2 {0}", stopwatch.toString());
    }

    @Test
    public void applyNormal() throws IOException {
        StringTemplateService sts = new StringTemplateService(dir);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "suhail");
        params.put("url", "http://localhost:8080/verification/something");
        String content = sts.apply("mujjio", "verification", params);
        LOG.log(Level.INFO, "content {0}", content);
        Assert.assertTrue("template has not been applied", (content.indexOf("suhail") > -1));
    }

    @Test(expected=IllegalArgumentException.class)
    public void templateGroupNotFound() throws IOException {
        StringTemplateService sts = new StringTemplateService(dir + "/wrongplace");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "suhail");
        String content = sts.apply("mujjio", "something", params);
        Assert.assertTrue("template has not been applied", (content.indexOf("suhail") > -1));        
    }
    
    @Test(expected=RuntimeException.class)
    public void templateNotFound() throws IOException {
        StringTemplateService sts = new StringTemplateService(dir);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "suhail");
        String content = sts.apply("mujsjio", "something", params);        
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void paramsNotFound() throws IOException {
        StringTemplateService sts = new StringTemplateService(dir);
        String content = sts.apply("mujsjio", "something", null);                
    }
}