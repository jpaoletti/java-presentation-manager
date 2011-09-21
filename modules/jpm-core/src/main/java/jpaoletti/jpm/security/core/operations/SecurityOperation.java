package jpaoletti.jpm.security.core.operations;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.core.operations.OperationCommandSupport;
import jpaoletti.jpm.security.core.PMSecurityConnector;

/**
 *
 * @author jpaoletti
 */
public class SecurityOperation extends OperationCommandSupport {

    public SecurityOperation(String operationId) {
        super(operationId);
    }

    @Override
    protected boolean checkEntity() {
        return true;
    }

    @Override
    protected boolean checkSelected() {
        return true;
    }

    @Override
    protected boolean openTransaction() {
        return true;
    }

    protected PMSecurityConnector getConnector(PMContext ctx) {
        return PresentationManager.getPm().getSecurityConnector();
    }
}
