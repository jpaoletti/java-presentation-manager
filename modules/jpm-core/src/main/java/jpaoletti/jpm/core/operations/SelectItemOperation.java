package jpaoletti.jpm.core.operations;

import java.util.Set;
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
            final Set<Integer> selectedIndexes = ctx.getEntityContainer().getSelectedIndexes();
            if (selectedIndexes.contains(item)) {
                selectedIndexes.remove(item);
            } else {
                selectedIndexes.add(item);
            }
        }
    }
}
