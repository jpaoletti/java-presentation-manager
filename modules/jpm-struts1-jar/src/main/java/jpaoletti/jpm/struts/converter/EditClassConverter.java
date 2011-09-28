package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;

/**
 * Converter for a class name. Not fully working at the moment
 * 
 * @author jpaoletti
 */
public class EditClassConverter extends StrutsEditConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        Object value = ctx.getFieldValue();
        if (value == null) {
            return null;
        }
        String s = (String) value;
        if (s.compareTo("") == 0) {
            return null;
        }
        try {
            return ctx.getPresentationManager().newInstance(s);
        } catch (Exception e) {
            throw new ConverterException(e.getMessage());
        }
    }

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        String s = "";
        try {
            s = getValue(ctx.getEntityInstance(), ctx.getField()).getClass().getName();
        } catch (Exception e) {
        }
        return super.visualize("string_converter.jsp?value=" + s);
    }
}
