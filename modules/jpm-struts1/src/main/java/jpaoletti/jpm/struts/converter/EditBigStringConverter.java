package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;

/**
 *
 * @author jpaoletti
 */
public class EditBigStringConverter extends EditStringConverter {

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        Object p = ctx.getFieldValue();
        if (p == null) {
            p = getValue(ctx.getEntityInstance(), ctx.getField());
        }
        final String value = (p != null) ? p.toString() : "";
        ctx.setFieldValue(value);
        return super.visualize("bigstring_converter.jsp?"
                + "isNull=" + (p == null)
                + "&cols=" + getConfig("cols", "40")
                + "&rows=" + getConfig("rows", "10")
                + "&withNull=" + getConfig("with-null", "false"));
    }
}
