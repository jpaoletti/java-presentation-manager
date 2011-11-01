package jpaoletti.jpm.core;

/**
 * Generic DataAccess implementing some general behavior
 * 
 * @author jpaoletti
 */
public abstract class AbstractDataAccess implements DataAccess {

    private Entity entity;

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public Object getItem(PMContext ctx, InstanceId instanceId) throws PMException {
        if (getEntity().isIdentified()) {
            return getItem(ctx, getEntity().getIdField(), instanceId.getId().toString());
        } else {
            return ctx.getList().getContents().get(instanceId.getIndex());
        }
    }

    @Override
    public InstanceId getInstanceId(PMContext ctx, EntityInstanceWrapper instanceWrapper) throws PMException {
        if (getEntity().isIdentified()) {
            return new InstanceId(PresentationManager.getPm().get(instanceWrapper.getInstance(), getEntity().getIdField()));
        } else {
            return new InstanceId(ctx.getList().getContents().indexOf(instanceWrapper.getInstance()));
        }
    }

    @Override
    public EntityFilter createFilter(PMContext ctx) throws PMException {
        return new EntityFilter();
    }

    @Override
    public Object refresh(PMContext ctx, Object o) throws PMException {
        return o;
    }
}
