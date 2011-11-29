package jpaoletti.jpm.core.operations;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;

/**
 *
 * @author jpaoletti
 */
public class SortOperation extends OperationCommandSupport {

    public SortOperation(String operationId) {
        super(operationId);
    }

    @Override
    protected boolean prepare(PMContext ctx) throws PMException {
        super.prepare(ctx);
        return finished(ctx);
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        ctx.put("order", ctx.getParameter("order"));
        ctx.put("desc", ctx.getParameter("desc"));
        //After that, a redirection to list operation is needed and preserving
        //this keys results in sorting.
    }

    @Override
    protected boolean checkEntity() {
        return true;
    }
}
