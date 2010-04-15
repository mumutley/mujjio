/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cementframework.research;

import java.util.List;
import org.cementframework.querybyproxy.shared.api.TypedQuery;

/**
 *
 * @author manzoors
 */
public class TestTypedQuery<T> implements TypedQuery<T> {


    @Override
    public List<T> find() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T findSingleResult() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypedQuery<T> first(int startPosition) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypedQuery<T> limit(int rowLimit) {
        return null;
    }
}
