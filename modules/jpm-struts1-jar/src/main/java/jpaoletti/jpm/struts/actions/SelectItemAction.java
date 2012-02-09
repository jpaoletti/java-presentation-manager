package jpaoletti.jpm.struts.actions;

import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.operations.SelectItemOperation;
import jpaoletti.jpm.struts.PMForwardException;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 *
 * @author jpaoletti
 */
public class SelectItemAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        final SelectItemOperation op = new SelectItemOperation();
        op.execute(ctx);
        throw new PMForwardException("none");
    }
}
