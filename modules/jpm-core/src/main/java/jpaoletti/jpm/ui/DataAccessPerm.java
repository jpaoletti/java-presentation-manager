package jpaoletti.jpm.ui;

import java.util.List;
import jpaoletti.jpm.core.*;
import jpaoletti.jpm.security.core.*;

public class DataAccessPerm implements DataAccess {

    private Entity entity;

    @Override
    public void delete(PMContext ctx, Object object) {
    }

    @Override
    public Object getItem(PMContext ctx, String property, String value) throws PMException {
        return null;
    }

    @Override
    public Object refresh(PMContext ctx, Object o) throws PMException {
        if (o != null) {
            PMSecurityPermission instance = (PMSecurityPermission) o;
            return getItem(ctx, "", instance.getName());
        } else {
            return null;
        }
    }

    @Override
    public List<?> list(PMContext ctx, EntityFilter filter, ListSort sort, Integer from, Integer count) throws PMException {
        try {
            List<PMSecurityPermission> list = getConnector().getPermissions();
            Integer f = (from == null) ? 0 : from;
            Integer t = (count == null) ? list.size() : (from + count > list.size() ? list.size() : from + count);
            return list.subList(f, t);
        } catch (PMSecurityException e) {
            ctx.getPresentationManager().error(e);
            return null;
        }
    }

    @Override
    public void update(PMContext ctx, Object instance) throws PMException {
    }

    @Override
    public void add(PMContext ctx, Object instance) throws PMException {
    }

    private PMSecurityConnector getConnector() {
        return PresentationManager.getPm().getSecurityConnector();
    }

    @Override
    public Long count(PMContext ctx) throws PMException {
        return new Long(list(ctx, null, null, null, null).size());
    }

    @Override
    public EntityFilter createFilter(PMContext ctx) throws PMException {
        return new EntityFilter();
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
