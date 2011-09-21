package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;

/**
 * Converter for passwords
 * 
 * @author jpaoletti
 */
public class EditPasswordConverter extends EditStringConverter {
    
    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        return super.visualize("password_converter.jsp?");
    }
}