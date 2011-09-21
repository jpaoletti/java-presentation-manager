package jpaoletti.jpm.core;

/**
 *
 * @author jpaoletti
 */
public interface OperationCommand {
    /*
     * Excecute the operation with the given context.
     * 
     * @param ctx
     * @return true if the operation was completly excecuted.
     */

    public boolean execute(PMContext ctx) throws PMException;

    public String getOperationId();
}
