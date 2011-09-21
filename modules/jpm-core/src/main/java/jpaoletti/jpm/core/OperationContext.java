package jpaoletti.jpm.core;


/**
 * This interface allows the programmer to defines some code to execute before
 * or after any operation execution.
 * 
 * @author jpaoletti 
 * 
 */
public interface OperationContext {

    /**This method is executed at the very beginning of the process, before
     * converterting or replace any data on objects.
     * @param ctx The context
     * @throws PMException
     */
    public void preConversion(PMContext ctx) throws PMException;

    /**This method is executed before trying to execute the main method of the operation, that is
     * before opening any transaction. 
     * @param ctx The context
     * @throws PMException
     */
    public void preExecute(PMContext ctx) throws PMException;

    /**This method is executed after the main method of the operation.
     * @param ctx The context
     * @throws PMException
     */
    public void postExecute(PMContext ctx) throws PMException;
}
