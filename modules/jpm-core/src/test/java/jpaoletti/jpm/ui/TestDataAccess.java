package jpaoletti.jpm.ui;

import java.util.ArrayList;
import java.util.List;
import jpaoletti.jpm.core.DataAccess;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.EntityFilter;
import jpaoletti.jpm.core.ListSort;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;

/**
 * Test Data Access
 *
 * @author jpaoletti
 * @since 21/09/2011
 * @version 1.0
 *
 */
public class TestDataAccess implements DataAccess {

    private Entity entity;

    @Override
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public Entity getEntity() {
        return entity;
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
    public Long count(PMContext ctx) throws PMException {
        return 0L;
    }

    @Override
    public void delete(PMContext ctx, Object object) throws PMException {
    }

    @Override
    public void update(PMContext ctx, Object instance) throws PMException {
    }

    @Override
    public void add(PMContext ctx, Object instance) throws PMException {
    }

    @Override
    public Object refresh(PMContext ctx, Object o) throws PMException {
        return o;
    }

    @Override
    public EntityFilter createFilter(PMContext ctx) throws PMException {
        return null;
    }
}
