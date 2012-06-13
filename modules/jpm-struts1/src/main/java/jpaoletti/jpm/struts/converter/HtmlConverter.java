package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;

/**
 * HTML editor converter. 
 * 
 * @author jpaoletti
 */
public class HtmlConverter extends EditStringConverter {

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        Object p = ctx.getFieldValue();
        if (p == null) {
            p = getValue(ctx.getEntityInstance(), ctx.getField());
        }
        final String value = (p != null) ? p.toString() : "";
        ctx.setFieldValue(value);
        return super.visualize("html_converter.jsp?");
    }
}
