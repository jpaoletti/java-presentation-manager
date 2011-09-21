package jpaoletti.jpm.converter;

/**
 * An exception that tell the engine to ignore this conversion
 * 
 * @author jpaoletti
 */
public class IgnoreConvertionException extends ConverterException {
    private static final long serialVersionUID = -3933157442033340283L;

    /**
     *
     * @param string
     */
    public IgnoreConvertionException(String string) {
        super(string);
    }

    /**
     *
     */
    public IgnoreConvertionException() {
        super();
    }
}