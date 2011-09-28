package jpaoletti.jpm.struts.actions;

import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.operations.ShowOperation;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 * Action for show operation.
 *
 * @author jpaoletti
 */
public class ShowAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        ctx.put("editable", false);
        final ShowOperation op = new ShowOperation("show");
        op.execute(ctx);
    }
}
