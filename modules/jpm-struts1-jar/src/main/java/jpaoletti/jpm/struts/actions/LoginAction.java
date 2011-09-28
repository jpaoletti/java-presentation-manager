package jpaoletti.jpm.struts.actions;

import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.operations.LoginOperation;
import jpaoletti.jpm.struts.PMEntitySupport;
import jpaoletti.jpm.struts.PMForwardException;
import jpaoletti.jpm.struts.PMStrutsContext;

public class LoginAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        final LoginOperation op = new LoginOperation("login");
        if (op.execute(ctx)) {
            ctx.getSession().setAttribute(PMEntitySupport.PMSESSION, ctx.getPmsession());
            if (ctx.getUser().isChangePassword()) {
                throw new PMForwardException("changepassword");
            }
        }
    }

    @Override
    protected boolean checkUser() {
        return false;
    }
}
