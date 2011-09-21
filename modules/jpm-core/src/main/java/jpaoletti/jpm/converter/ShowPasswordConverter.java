package jpaoletti.jpm.converter;

import jpaoletti.jpm.core.PMContext;

/**
 * Conveter for showing passwords
 * 
 * @author jpaoletti
 */
public class ShowPasswordConverter extends Converter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        throw new IgnoreConvertionException("");
    }

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        return "**********************";
    }
}