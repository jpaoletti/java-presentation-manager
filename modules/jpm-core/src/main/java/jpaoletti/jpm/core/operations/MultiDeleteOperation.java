package jpaoletti.jpm.core.operations;

import java.util.Set;
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
        final Set<Integer> selectedIndexes = ctx.getEntityContainer().getSelectedIndexes();
        final DeleteOperation deleteOp = new DeleteOperation();
        for (Integer item : selectedIndexes) {
            ctx.put(PM_ITEM, item.toString());
            deleteOp.execute(ctx);
        }
        ctx.getEntityContainer().getSelectedIndexes().clear();
    }
}
