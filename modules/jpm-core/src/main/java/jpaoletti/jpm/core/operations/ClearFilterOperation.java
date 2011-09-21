package jpaoletti.jpm.core.operations;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;

/**
 *
 * @author jpaoletti
 */
public class ClearFilterOperation extends OperationCommandSupport {

    public ClearFilterOperation(String operationId) {
        super(operationId);
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        super.doExecute(ctx);
        ctx.getEntityContainer().setFilter(null);
    }
}
