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
package me.moimoi.social.herql.cache;

import java.util.logging.Level;
import junit.framework.Assert;

import org.apache.commons.lang.time.StopWatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.logging.Logger;
import me.moimoi.social.herql.config.CacheModule;
import me.moimoi.social.herql.config.PropertiesModule;
import org.apache.shindig.common.cache.ehcache.EhCacheModule;

public class CacheBenchMarkTest {

    private Injector injector;
    private Calculator cacheCalculator;
    private Calculator nonCacheCalculator = new CalculatorImpl();
    private static final Logger logger = Logger.getLogger(CacheBenchMarkTest.class.getName());

    //@Before
    public void beforeTest() {
        injector = Guice.createInjector(new EhCacheModule(), new PropertiesModule(), new CacheModule(), new GuiceModule());
        cacheCalculator = injector.getInstance(Calculator.class);
    }

    //@Test
    public void minimalTest() throws InterruptedException {

        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        // Got to cache!
        Assert.assertEquals(2, cacheCalculator.calculateSomethingWild(2));

        logger.log(Level.INFO, "Done calculating Cached Warmup '{''}' ms {0}", stopwatch.getTime());

        stopwatch = new StopWatch();
        stopwatch.start();
        Assert.assertEquals(2, cacheCalculator.calculateSomethingWild(2));

        logger.log(Level.INFO, "Done calculating Cached '{''}' ms {0}", stopwatch.getTime());

        stopwatch = new StopWatch();
        stopwatch.start();

        Assert.assertEquals(2, nonCacheCalculator.calculateSomethingWild(2));

        logger.log(Level.INFO, "Done calculating  Non Cached '{''}' ms {0}", stopwatch.getTime());

    }

    //@Test
    public void checkingCacheCollision() throws InterruptedException {

        minimalTest();

        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        // Got to cache!
        Assert.assertEquals(4, cacheCalculator.calculateSomethingWild(2, 2));

        logger.log(Level.INFO, "Done calculating Cached Warmup '{''}' ms {0}", stopwatch.getTime());

        stopwatch = new StopWatch();
        stopwatch.start();

        Assert.assertEquals(4, cacheCalculator.calculateSomethingWild(2, 2));

        logger.log(Level.INFO, "Done calculating Cached '{''}' ms {0}", stopwatch.getTime());

        stopwatch = new StopWatch();
        stopwatch.start();

        Assert.assertEquals(4, nonCacheCalculator.calculateSomethingWild(2, 2));

        logger.log(Level.INFO, "Done calculating  Non Cached '{''}' ms {0}", stopwatch.getTime());

    }

    //@After
    public void afterTest() {
    }
}
