/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cementframework.research;

import cementframework.research.model.SimpleCase;
import org.cementframework.querybyproxy.shared.api.ProxyQuery;
import org.cementframework.querybyproxy.shared.api.ProxyQueryBuilder;

/**
 * @author manzoors
 */
public class FirstTest extends AbstractSessionTestBase {
    ProxyQueryBuilder qb = factory.getQueryBuilder();

    public FirstTest(String testName) {
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
    public void testHello() {
        ProxyQuery<SimpleCase> query = factory.createQuery(SimpleCase.class);        
        assertFalse(query.getSelect().isDistinct());
        System.out.println(query.getClass());
        query.orWhere("a").equalTo("b");
    }
}
