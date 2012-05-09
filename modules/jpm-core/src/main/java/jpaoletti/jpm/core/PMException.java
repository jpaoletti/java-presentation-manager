package jpaoletti.jpm.core;

import jpaoletti.jpm.core.message.Message;

/**
 * A generic expection for Presentation Manager engine.
 *
 * @author jpaoletti
 */
public class PMException extends Exception {

    private String key;
    private Message msg;
    private static final long serialVersionUID = -1685585143991954053L;

    public PMException(Message msg) {
        this.msg = msg;
    }

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

    public Message getMsg() {
        return msg;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        if (getMsg() != null) {
            return PresentationManager.getMessage(getMsg().getKey(), getMsg().getArgs());
        } else if (getKey() != null) {
            return PresentationManager.getMessage(getKey());
        } else {
            return super.getMessage();
        }
    }
}
