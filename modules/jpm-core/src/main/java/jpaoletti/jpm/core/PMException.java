package jpaoletti.jpm.core;

/**
 * A generic expection for Presentation Manager engine.
 * 
 * @author jpaoletti
 */
public class PMException extends Exception {

    private String key;
    private static final long serialVersionUID = -1685585143991954053L;

    /**
     *
     * @param key
     */
    public PMException(String key) {
        setKey(key);
    }

    /**
     *
     */
    public PMException() {
        super();
    }

    /**
     *
     * @param nested
     */
    public PMException(Throwable nested) {
        super(nested);
    }

    /**
     *
     * @param s
     * @param nested
     */
    public PMException(String s, Throwable nested) {
        super(s, nested);
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }
}
