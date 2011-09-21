package jpaoletti.jpm.core.operations;

import java.util.Collection;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;

/**
 *
 * @author jpaoletti
 */
public class DeleteOperation extends OperationCommandSupport {

    public DeleteOperation(String operationId) {
        super(operationId);
    }

    @Override
    protected boolean openTransaction() {
        return true;
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        super.doExecute(ctx);
        final Object instance = ctx.getSelected().getInstance();
        if (ctx.getEntity().isWeak()) {
            final Collection<Object> collection = getOwnerCollection(ctx);
            if (collection != null) {
                collection.remove(instance);
            }
        }
        try {
            ctx.getEntity().getDataAccess().delete(ctx, instance);
        } catch (Exception e) {
            ctx.getPresentationManager().error(e);
            throw new PMException("pm.cant.delete");
        }
        ctx.getEntityContainer().setSelected(null);
    }
}
