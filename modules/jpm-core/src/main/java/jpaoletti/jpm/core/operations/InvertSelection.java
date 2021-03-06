package jpaoletti.jpm.core.operations;

import java.util.List;
import jpaoletti.jpm.core.EntityInstanceWrapper;
import jpaoletti.jpm.core.InstanceId;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;

/**
 *
 * @author jpaoletti
 */
public class InvertSelection extends ListOperation {

    public InvertSelection(String operationId) {
        super(operationId);
    }

    public InvertSelection() {
        super("invertselection");
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        super.doExecute(ctx);
        final List<Object> contents = (List<Object>) ctx.get(LIST_CONTENTS);
        for (Object object : contents) {
            final InstanceId id = ctx.getDataAccess().getInstanceId(ctx, new EntityInstanceWrapper(object));
            final List<InstanceId> selectedIndexes = ctx.getEntityContainer().getSelectedInstanceIds();
            if (selectedIndexes.contains(id)) {
                selectedIndexes.remove(id);
            } else {
                selectedIndexes.add(id);
            }
        }
    }
}
