package jpaoletti.jpm.core.operations;

import java.util.List;
import jpaoletti.jpm.core.InstanceId;
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
            final InstanceId id = buildInstanceId(ctx.getEntity(), _item);
            final List<InstanceId> selectedIndexes = ctx.getEntityContainer().getSelectedInstanceIds();
            if (selectedIndexes.contains(id)) {
                selectedIndexes.remove(id);
            } else {
                selectedIndexes.add(id);
            }
        }
    }

    @Override
    protected boolean checkEntity() {
        return true;
    }
}
