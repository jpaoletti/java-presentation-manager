package jpaoletti.jpm.ui;

import java.util.ArrayList;
import java.util.List;
import jpaoletti.jpm.core.*;

/**
 *
 * @author jpaoletti
 */
public class DataAccessEntityContainer implements DataAccess {

    private Entity entity;

    @Override
    public Object getItem(PMContext ctx, String property, String value) throws PMException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<?> list(PMContext ctx, EntityFilter filter, ListSort sort, Integer from, Integer count) throws PMException {
        PMSession pc = getParent(ctx);
        List<?> weaklist = new ArrayList(pc.getContainers());
        int f = (from == null) ? 0 : from;
        int c = (int) ((count == null) ? count(ctx) : count);
        if (count(ctx) < c) {
            return weaklist;
        }
        if (count(ctx) < f) {
            return null;
        }
        int fpc = f + c;
        return weaklist.subList(f, (int) ((fpc > count(ctx)) ? count(ctx) : fpc));
    }

    public PMSession getParent(PMContext ctx) throws PMException {
        return (PMSession) ctx.getEntityContainer().getOwner().getSelected().getInstance();
    }

    @Override
    public Long count(PMContext ctx) throws PMException {
        return new Long(getParent(ctx).getContainers().size());
    }

    @Override
    public void delete(PMContext ctx, Object object) throws PMException {
        final EntityContainer ec = (EntityContainer) object;
        getParent(ctx).setContainer(ec.getId(), null);
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }
}
