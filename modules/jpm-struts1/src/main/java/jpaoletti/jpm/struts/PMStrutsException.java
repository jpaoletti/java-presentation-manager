package jpaoletti.jpm.struts;

import jpaoletti.jpm.core.PMException;

/**
 * PMException specific for pm_struts module
 *
 * @author jpaoletti
 * @see PMException
 */
public class PMStrutsException extends PMException {

    private static final long serialVersionUID = -1685585143991954053L;

    /**
     * Constructor
     * @param key The key of the error
     */
    public PMStrutsException(String key) {
        super(key);
    }
}
