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
package me.moimoi.social.herql.util;

import com.google.inject.Singleton;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.moimoi.social.herql.services.WorkerPool;

/**
 *
 * @author suhail
 */
@Singleton
public class OutputWorkerPool implements WorkerPool {

    ExecutorService executor = Executors.newFixedThreadPool(20);

    public OutputWorkerPool() {
    }

    @Override
    public void submit(final OutputWorker work) {
        FutureTask<OutputWorker> future = new FutureTask<OutputWorker>(
                new Callable<OutputWorker>() {

                    @Override
                    public OutputWorker call() throws Exception {
                        LOG.log(Level.INFO, "Object handle is {0}", work.toString());
                        work.work();
                        return null;
                    }
                });
        executor.execute(future);
    }
    
    private static final Logger LOG = Logger.getLogger(OutputWorkerPool.class.getName());
}
