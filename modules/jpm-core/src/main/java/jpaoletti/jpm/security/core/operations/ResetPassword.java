package jpaoletti.jpm.security.core.operations;

import java.util.UUID;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.security.core.PMSecurityUser;

/**
 *
 * @author jpaoletti
 */
public class ResetPassword extends SecurityOperation {

    public ResetPassword(String operationId) {
        super(operationId);
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        final PMSecurityUser u = (PMSecurityUser) ctx.getSelected().getInstance();
        if (ctx.getUser().equals(u)) {
            throw new PMException("pm.user.cant.reset.his.psw");
        }
        final String generatedpsw = UUID.randomUUID().toString().substring(0, 8);
        getConnector(ctx).changePassword(u.getUsername(), null, generatedpsw);
        ctx.put("generatedpsw", generatedpsw);
        ctx.put("username", u.getUsername());
    }
}
