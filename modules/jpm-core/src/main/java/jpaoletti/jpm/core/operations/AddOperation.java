package jpaoletti.jpm.core.operations;

import jpaoletti.jpm.core.*;

/**
 *
 * @author jpaoletti
 */
public class AddOperation extends OperationCommandSupport {

    public AddOperation(String operationId) {
        super(operationId);
    }

    @Override
    protected boolean prepare(PMContext ctx) throws PMException {
        super.prepare(ctx);
        if (ctx.getParameter("finish") == null) {
            initBean(ctx);
            return false;
        } else {
            if (ctx.getSelected() == null) {
                initBean(ctx); //Auto init bean
            }
            for (Field f : ctx.getEntity().getAllFields()) {
                if (f.shouldDisplay(ctx.getOperation().getId(), ctx.getUser())) {
                    proccessField(ctx, f, ctx.getSelected());
                }
            }
            if (ctx.hasErrors()) {
                throw new PMException();
            }

            if (ctx.getEntity().isWeak()) {
                final Object parent = ctx.getEntityContainer().getOwner().getSelected().getInstance();
                final EntityOwner owner = ctx.getEntity().getOwner();
                final Object instance = ctx.getSelected().getInstance();
                ctx.getPresentationManager().set(instance, owner.getLocalProperty(), parent);
                getOwnerCollection(ctx).add(instance);
            }
        }
        return true;
    }

    @Override
    protected void rollback(PMContext ctx) throws PMException {
        super.rollback(ctx);
        initBean(ctx);
    }

    protected void initBean(PMContext ctx) throws PMException {
        //Creates bean and put it in session
        final Object obj = PresentationManager.getPm().newInstance(ctx.getEntity().getClazz());
        ctx.getEntityContainer().setSelected(new EntityInstanceWrapper(obj));
        ctx.getEntityContainer().setSelectedNew(true);
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        Object instance = ctx.getSelected().getInstance();
        ctx.getPresentationManager().debug(this, "Saving '" + ctx.getEntity().getId() + "' to Data Access");
        ctx.getEntity().getDataAccess().add(ctx, instance);
        ctx.getEntityContainer().setSelectedNew(false);
    }

    @Override
    public boolean checkSelected() {
        return false;
    }

    @Override
    protected boolean openTransaction() {
        return true;
    }

    @Override
    protected boolean checkEntity() {
        return true;
    }
}
