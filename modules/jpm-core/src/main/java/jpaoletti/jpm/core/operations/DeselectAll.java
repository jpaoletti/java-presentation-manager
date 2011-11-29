package jpaoletti.jpm.core.operations;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;

/**
 *
 * @author jpaoletti
 */
public class DeselectAll extends OperationCommandSupport {

    public DeselectAll(String operationId) {
        super(operationId);
    }

    public DeselectAll() {
        super("deselectall");
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        super.doExecute(ctx);
        if (ctx.hasEntityContainer()) {
            ctx.getEntityContainer().getSelectedInstanceIds().clear();
        }
    }

    @Override
    protected boolean checkEntity() {
        return true;
    }
}
