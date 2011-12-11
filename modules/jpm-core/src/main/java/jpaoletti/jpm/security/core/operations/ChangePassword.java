package jpaoletti.jpm.security.core.operations;

import jpaoletti.jpm.core.*;
import jpaoletti.jpm.security.core.PMSecurityException;
import jpaoletti.jpm.security.core.PMSecurityUser;

/**
 *
 * @author jpaoletti
 */
public class ChangePassword extends SecurityOperation {

    public ChangePassword(String operationId) {
        super(operationId);
    }

    @Override
    protected boolean prepare(PMContext ctx) throws PMException {
        super.prepare(ctx);
        return finished(ctx);
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        final PMSecurityUser u = ctx.getUser();

        final String actual = (String) ctx.getParameter("actual");
        assertNotNull(actual, "chpass.actual.not.null");

        final String newpass = (String) ctx.getParameter("newpass");
        assertNotNull(newpass, "chpass.newpass.not.null");

        final String newrep = (String) ctx.getParameter("newrep");
        assertNotNull(newrep, "chpass.newrep.not.null");

        assertTrue(newpass.equals(newrep), "chpass.newrep.diferent");
        assertTrue(!newpass.equals(actual), "chpass.repeated.passw");

        try {
            getConnector(ctx).changePassword(u.getUsername(), actual, newpass);
        } catch (PMSecurityException e) {
            throw new PMException("pm_security.password.invalid");
        }
    }

    @Override
    protected boolean checkEntity() {
        return false;
    }

    @Override
    protected boolean checkSelected() {
        return false;
    }
}
