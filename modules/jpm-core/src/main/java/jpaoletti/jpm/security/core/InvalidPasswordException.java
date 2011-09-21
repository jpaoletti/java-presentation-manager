package jpaoletti.jpm.security.core;

public class InvalidPasswordException extends PMSecurityException {

    private static final long serialVersionUID = 7548517732552851015L;

    public InvalidPasswordException(String key) {
        super(key);
    }

    public InvalidPasswordException() {
    }

    public InvalidPasswordException(Throwable nested) {
        super(nested);
    }

    public InvalidPasswordException(String s, Throwable nested) {
        super(s, nested);
    }
}
