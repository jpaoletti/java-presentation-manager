package jpaoletti.jpm.core.exception;

/**
 * A connection not found exception is thrown in case we try to get a connection
 * object somewhere and it is not there. Defined as Runtime so we don't need to
 * add it to every method.
 * 
 * @author jpaoletti
 */
public class ConnectionNotFoundException extends RuntimeException {
}
