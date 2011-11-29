package jpaoletti.jpm.core.operations;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;

/**
 *
 * @author jpaoletti
 */
public class ShowOperation extends OperationCommandSupport {

    public ShowOperation(String operationId) {
        super(operationId);
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        super.doExecute(ctx);
    }

    @Override
    protected boolean checkSelected() {
        return true;
    }

    @Override
    protected boolean checkEntity() {
        return true;
    }
}
