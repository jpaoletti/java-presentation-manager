package jpaoletti.jpm.struts.actions;

import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.operations.ProfileOperation;
import jpaoletti.jpm.struts.PMForwardException;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 * Controller for profile operation
 * 
 * @author jpaoletti
 */
public class ProfileAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        final boolean finish = ctx.getParameter("finish") != null;
        final ProfileOperation op = new ProfileOperation();
        op.execute(ctx);
        if (!finish) {
            throw new PMForwardException(CONTINUE);
        }
    }
}
