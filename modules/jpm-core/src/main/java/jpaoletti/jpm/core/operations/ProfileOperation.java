package jpaoletti.jpm.core.operations;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.security.core.PMSecurityUser;

/**
 * User Profile operation
 *
 * @author jpaoletti
 */
public class ProfileOperation extends OperationCommandSupport {

    public ProfileOperation(String operationId) {
        super(operationId);
    }

    public ProfileOperation() {
        super("profile");
    }

    @Override
    protected boolean prepare(PMContext ctx) throws PMException {
        super.prepare(ctx);
        return finished(ctx);
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        final PMSecurityUser user = ctx.getPmsession().getUser();
        user.setEmail((String) ctx.getParameter("email"));
        user.setName((String) ctx.getParameter("name"));
        ctx.getPresentationManager().getSecurityConnector(ctx).updateUser(user);
    }

    @Override
    protected boolean openTransaction() {
        return true;
    }

    @Override
    protected boolean checkUser() {
        return true;
    }

    @Override
    protected boolean checkSelected() {
        return false;
    }

    @Override
    protected boolean checkEntity() {
        return false;
    }

    @Override
    protected boolean checkOperation() {
        return false;
    }
}
