/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cementframework.research;

import java.util.List;
import org.cementframework.querybyproxy.shared.api.DynamicQuery;

/**
 *
 * @author manzoors
 */
public class TestDynamicQuery implements DynamicQuery {

     /**
     * {@inheritDoc}
     */
    @Override
    public List<Object[]> find() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] findSingleResult() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DynamicQuery first(int startPosition) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DynamicQuery limit(int rowLimit) {
        return null;
    }


}
