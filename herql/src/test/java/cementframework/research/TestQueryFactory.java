/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cementframework.research;

import org.cementframework.querybyproxy.shared.api.ProxyQuery;
import org.cementframework.querybyproxy.shared.api.model.joins.QueryJoin;
import org.cementframework.querybyproxy.shared.impl.AbstractProxyQueryFactory;
import org.cementframework.recordingproxy.impl.MethodCallUtils;

/**
 *
 * @author manzoors
 */
public class TestQueryFactory extends AbstractProxyQueryFactory {

    @Override
    protected <T> ProxyQuery<T> createCorrelated(T proxy, QueryJoin join) {
        return new TestProxyQuery<T>(proxy, join);
    }

    @Override
    public <T> ProxyQuery<T> createQuery(Class<T> targetClass) {
        T proxy = MethodCallUtils.proxy(targetClass);
        TestProxyQuery<T> query = new TestProxyQuery<T>(proxy,targetClass);
        return query;
    }
}
