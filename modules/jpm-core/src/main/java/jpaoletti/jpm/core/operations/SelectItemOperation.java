package jpaoletti.jpm.core.operations;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;

/**
 *
 * @author jpaoletti
 */
public class SelectItemOperation extends OperationCommandSupport {

    public SelectItemOperation(String operationId) {
        super(operationId);
    }

    public SelectItemOperation() {
        super("selectitem");
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        super.doExecute(ctx);
        final String _item = (String) ctx.getParameter("idx");
        if (_item != null) {
            final Integer item = Integer.parseInt(_item);
            ctx.getEntityContainer().getSelectedIndexes().add(item);
        }
    }
}
