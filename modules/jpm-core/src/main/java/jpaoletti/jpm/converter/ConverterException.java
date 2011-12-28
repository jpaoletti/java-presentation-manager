package jpaoletti.jpm.converter;

import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.message.Message;
import jpaoletti.jpm.core.message.MessageFactory;

/**
 * An exception thrown if a conversion fail
 *
 * @author jpaoletti
 */
public class ConverterException extends PMException {

    private static final long serialVersionUID = 6940226004619692335L;

    public ConverterException(Message msg) {
        super(msg);
    }

    /**
     *
     * @param string
     */
    public ConverterException(String string) {
        super(MessageFactory.error(string));
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
