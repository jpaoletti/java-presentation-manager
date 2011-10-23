package jpaoletti.jpm.struts.actions;

import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.security.core.operations.ChangePassword;
import jpaoletti.jpm.struts.PMForwardException;
import jpaoletti.jpm.struts.PMStrutsContext;

public class ChangePasswordAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {

        final ChangePassword op = new ChangePassword("changepassword");
        if (!op.execute(ctx)) {
            throw new PMForwardException(CONTINUE);
        }
    }
}