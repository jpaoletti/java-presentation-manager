package jpaoletti.jpm.core;

/**
 * Support class for OperationContext. @see OperationContext
 * 
 * @author jpaoletti 
 */
public class OperationContextSupport extends PMCoreObject implements OperationContext {

    @Override
    public void preConversion(PMContext ctx) throws PMException {
    }

    @Override
    public void preExecute(PMContext ctx) throws PMException {
    }

    @Override
    public void postExecute(PMContext ctx) throws PMException {
    }
}
