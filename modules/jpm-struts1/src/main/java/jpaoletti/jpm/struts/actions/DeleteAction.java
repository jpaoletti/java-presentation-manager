package jpaoletti.jpm.struts.actions;

import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.operations.DeleteOperation;
import jpaoletti.jpm.struts.PMStrutsContext;

public class DeleteAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        (new DeleteOperation("delete")).execute(ctx);
    }
}
