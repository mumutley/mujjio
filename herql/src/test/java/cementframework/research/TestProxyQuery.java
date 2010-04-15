/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cementframework.research;

import org.cementframework.querybyproxy.shared.api.DynamicQuery;
import org.cementframework.querybyproxy.shared.api.ProxyQueryFactory;
import org.cementframework.querybyproxy.shared.api.TypedQuery;
import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoin;
import org.cementframework.querybyproxy.shared.impl.AbstractProxyQueryImpl;

/**
 *
 * @author manzoors
 */
public class TestProxyQuery<T> extends AbstractProxyQueryImpl<T> {

    public TestProxyQuery(T proxy, Class<T> rootProxyClass) {
        super(proxy, rootProxyClass);
    }

    public TestProxyQuery(T proxy, QueryJoin join) {
        super(proxy, join);
    }

    @Override
    public DynamicQuery createDynamicQuery() {
        return new TestDynamicQuery();

    }

    @Override
    protected TypedQuery createTypedQuery() {
        return new TestTypedQuery<T>();
    }

    @Override
    public ProxyQueryFactory getQueryFactory() {
        return null;
    }
}
