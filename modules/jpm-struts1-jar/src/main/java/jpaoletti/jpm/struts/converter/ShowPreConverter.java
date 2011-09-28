package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.converter.ShowStringConverter;
import jpaoletti.jpm.core.PMContext;

/**
 *
 * @author jpaoletti
 */
public class ShowPreConverter extends ShowStringConverter {

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        Object p = ctx.getFieldValue();
        if (p == null) {
            p = getValue(ctx.getEntityInstance(), ctx.getField());
        }
        final String value = (p == null) ? "" : p.toString();
        ctx.setFieldValue(value);
        return super.visualize("pre.jsp?");
    }
}
