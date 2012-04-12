package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.Converter;
import jpaoletti.jpm.converter.ConverterException;

public abstract class StrutsEditConverter extends Converter {

    protected String visualize(String s) throws ConverterException {
        return s;
    }
}