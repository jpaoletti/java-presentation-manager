package jpaoletti.jpm.core.operations;

import java.util.List;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;

/**
 *
 * @author jpaoletti
 */
public class MultiDeleteOperation extends OperationCommandSupport {

    public MultiDeleteOperation(String operationId) {
        super(operationId);
    }

    public MultiDeleteOperation() {
        super("multidelete");
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        super.doExecute(ctx);
        final List<Object> instances = getSelectedInstances(ctx);
        for (Object instance : instances) {
            ctx.getDataAccess().delete(ctx, instance);
        }
        ctx.getEntityContainer().getSelectedInstanceIds().clear();
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
