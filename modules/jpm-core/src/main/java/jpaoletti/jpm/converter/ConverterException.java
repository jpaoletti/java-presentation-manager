package jpaoletti.jpm.converter;

import jpaoletti.jpm.core.PMException;

/**
 * An exception thrown if a conversion fail
 *
 * @author jpaoletti
 */
public class ConverterException extends PMException {

    private static final long serialVersionUID = 6940226004619692335L;

    /**
     *
     * @param string
     */
    public ConverterException(String string) {
        super(string);
    }

    /**
     *
     * @param e
     */
    public ConverterException(Exception e) {
        super(e);
    }

    /**
     *
     */
    public ConverterException() {
        super();
    }
}
