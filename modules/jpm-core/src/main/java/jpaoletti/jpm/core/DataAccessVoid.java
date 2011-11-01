package jpaoletti.jpm.core;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of Data Access that simply do nothing
 * 
 * @author jpaoletti
 * 
 */
public class DataAccessVoid extends AbstractDataAccess {

    @Override
    public void add(PMContext ctx, Object instance) throws PMException {
    }

    @Override
    public Long count(PMContext ctx) throws PMException {
        return 0L;
    }


    @Override
    public void delete(PMContext ctx, Object object) throws PMException {
    }

    @Override
    public Object getItem(PMContext ctx, String property, String value) throws PMException {
        return null;
    }

    @Override
    public List<?> list(PMContext ctx, EntityFilter filter, ListSort sort, Integer from, Integer count) throws PMException {
        return new ArrayList<Object>();
    }

    @Override
    public void update(PMContext ctx, Object instance) throws PMException {
    }
}
