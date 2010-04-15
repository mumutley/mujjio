/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cementframework.research;

import junit.framework.TestCase;
import org.cementframework.querybyproxy.shared.impl.AbstractProxyQueryFactory;
import org.cementframework.recordingproxy.api.RecordingSessions;

/**
 *
 * @author manzoors
 */
public class AbstractSessionTestBase extends TestCase {

    protected TestQueryFactory factory = new TestQueryFactory();

    public AbstractSessionTestBase(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    public void testRecording() {
        RecordingSessions.get().clear();
        assertTrue(RecordingSessions.get().isEmpty());
        AbstractProxyQueryFactory s;
    }

}
