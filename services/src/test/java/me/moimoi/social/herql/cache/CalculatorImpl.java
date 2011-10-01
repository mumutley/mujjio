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

import me.moimoi.social.herql.cache.annotation.Cached;


/**
 *
 * @author suhail
 */
public class CalculatorImpl implements Calculator {

    @Cached(name="null")
    @Override
    public int calculateSomethingWild(Integer number)
            throws InterruptedException {
        Thread.sleep(2000);
        return number;
    }

    @Cached(name="null")
    @Override
    public int calculateSomethingWild(Integer number, Integer number2)
            throws InterruptedException {
        Thread.sleep(2000);
        return number + number2;
    }
}
