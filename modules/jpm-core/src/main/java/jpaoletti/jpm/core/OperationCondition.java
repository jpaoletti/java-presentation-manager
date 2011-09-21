package jpaoletti.jpm.core;

/**
 * This interface is intended to determine if an operation should be displayed
 * on other or not depending on this conditional
 *
 * @author jpaoletti
 */
public interface OperationCondition {

    public boolean check(final PMContext ctx, final Object instance, final Operation operation, final String displayAt) throws PMException;
}
