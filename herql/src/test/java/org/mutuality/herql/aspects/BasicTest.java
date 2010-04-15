package org.mutuality.herql.aspects;

import org.mutuality.herql.domain.SimpleClass;

import junit.framework.TestCase;

public class BasicTest extends TestCase {

	public void testSomething(){
		SimpleClass sc = new SimpleClass();
		sc.initialize();
		sc.start();
		sc.stop();
		sc.terminate();
		sc.save();
		sc.getState();
	}
}
