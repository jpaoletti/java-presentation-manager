package jpaoletti.jpm.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jpaoletti.jpm.core.DataAccess;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.EntityFilter;
import jpaoletti.jpm.core.EntityInstanceWrapper;
import jpaoletti.jpm.core.InstanceId;
import jpaoletti.jpm.core.ListSort;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.model.JPMTest;

/**
 * Test Data Access
 *
 * @author jpaoletti
 * @since 21/09/2011
 * @version 1.0
 *
 */
public class TestDataAccess implements DataAccess {

    private static final List<JPMTest> contents = new ArrayList<JPMTest>();
    private Entity entity;

    static {
        try {
            contents.add(new JPMTest(1L, "One", 1, new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-20"), true));
        } catch (ParseException ex) {
        }
        contents.add(new JPMTest(2L, "Two", 2, new Date(), false));
        contents.add(new JPMTest(3L, "Three", 3, new Date(), false));
    }

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
        return contents;
    }

    @Override
    public Long count(PMContext ctx) throws PMException {
        return new Long(contents.size());
    }

    @Override
    public void delete(PMContext ctx, Object object) throws PMException {
        contents.remove((JPMTest) object);
    }

    @Override
    public void update(PMContext ctx, Object instance) throws PMException {
    }

    @Override
    public void add(PMContext ctx, Object instance) throws PMException {
        contents.add((JPMTest) instance);
    }

    @Override
    public Object refresh(PMContext ctx, Object o) throws PMException {
        return o;
    }

    @Override
    public EntityFilter createFilter(PMContext ctx) throws PMException {
        return null;
    }

    @Override
    public InstanceId getInstanceId(PMContext ctx, EntityInstanceWrapper instanceWrapper) {
        if (getEntity().isIdentified()) {
            return new InstanceId(((JPMTest) instanceWrapper.getInstance()).getId());
        } else {
            return new InstanceId(contents.indexOf(instanceWrapper.getInstance()));
        }
    }

    @Override
    public Object getItem(PMContext ctx, InstanceId instanceId) throws PMException {
        if (getEntity().isIdentified()) {
            for (JPMTest test : contents) {
                if (test.getId() == instanceId.getId()) {
                    return test;
                }
            }
        } else {
            return contents.get(instanceId.getIndex());
        }
        return null;
    }
}
