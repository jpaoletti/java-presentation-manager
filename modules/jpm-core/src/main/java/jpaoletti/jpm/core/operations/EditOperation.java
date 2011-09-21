package jpaoletti.jpm.core.operations;

import jpaoletti.jpm.core.*;

/**
 *
 * @author jpaoletti
 */
public class EditOperation extends OperationCommandSupport {

    public EditOperation(String operationId) {
        super(operationId);
    }

    @Override
    protected boolean prepare(PMContext ctx) throws PMException {
        super.prepare(ctx);
        if (ctx.getParameter("finish") != null) {
            if (ctx.getSelected() == null) {
                throw new PMException("pm.instance.not.found");
            }
            for (Field f : ctx.getEntity().getAllFields()) {
                if (f.shouldDisplay(ctx.getOperation().getId())) {
                    proccessField(ctx, f, ctx.getSelected());
                }
            }
            if (ctx.hasErrors()) {
                throw new PMException();
            }
            return true;
        }
        return false;
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        super.doExecute(ctx);
        ctx.getPresentationManager().debug(this, "Updating '" + ctx.getEntity().getId() + "' to Data Access");
        ctx.getEntity().getDataAccess().update(ctx, ctx.getSelected().getInstance());
    }

    @Override
    protected boolean openTransaction() {
        return true;
    }

    @Override
    protected boolean checkUser() {
        return true;
    }

    @Override
    protected boolean checkEntity() {
        return true;
    }
}
