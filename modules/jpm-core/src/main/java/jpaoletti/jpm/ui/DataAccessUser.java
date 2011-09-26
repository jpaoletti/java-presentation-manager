package jpaoletti.jpm.ui;

import java.util.List;
import jpaoletti.jpm.core.*;
import jpaoletti.jpm.security.core.*;

public class DataAccessUser implements DataAccess {

    private Entity entity;

    @Override
    public void delete(PMContext ctx, Object object) throws PMException {
        PMSecurityUser instance = (PMSecurityUser) object;
        getConnector().removeUser(instance);
    }

    @Override
    public Object refresh(PMContext ctx, Object o) throws PMException {
        if (o != null) {
            PMSecurityUser instance = (PMSecurityUser) o;
            return getItem(ctx, "", instance.getUsername());
        } else {
            return null;
        }
    }

    @Override
    public Object getItem(PMContext ctx, String property, String value) throws PMException {
        return getConnector().getUser(value);
    }

    private PMSecurityConnector getConnector() {
        return PresentationManager.getPm().getSecurityConnector();
    }

    @Override
    public List<?> list(PMContext ctx, EntityFilter filter, ListSort sort, Integer from, Integer count) throws PMException {
        List<PMSecurityUser> list = getConnector().getUsers();
        Integer f = (from == null) ? 0 : from;
        Integer t = (count == null) ? list.size() : (from + count > list.size() ? list.size() : from + count);
        return list.subList(f, t);
    }

    @Override
    public void update(PMContext ctx, Object instance) throws PMException {
        getConnector().updateUser((PMSecurityUser) instance);
    }

    @Override
    public void add(PMContext ctx, Object instance) throws PMException {
        getConnector().addUser((PMSecurityUser) instance);

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
