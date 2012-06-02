package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;

/**
 * Converter for long <br> Properties: currency and format
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.EditLongConverter">
 *     <operationId>edit</operationId>
 * </converter>
 * }
 * </pre>
 *
 * @author jpaoletti
 *
 */
public class EditLongConverter extends DefaultStrutsConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        try {
            final String v = (String) ctx.getFieldValue();
            if ((v == null || "".equals(v.trim()))) {
                if (getConfig("not-null", "false").equals("true")) {
                    throw new ConverterException("pm.struts.converter.invalid.null.long");
                } else {
                    return null;
                }
            }
            return Long.parseLong(v);
        } catch (NumberFormatException e) {
            throw new ConverterException("pm.struts.converter.invalid.long");
        }
    }

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        Object p = ctx.getFieldValue();
        if (p == null) {
            p = getValue(ctx.getEntityInstance(), ctx.getField());
        }
        final String value = (p == null) ? "" : p.toString();
        ctx.setFieldValue(value);
        return super.visualize("long-edit.jsp?"
                + "ml=" + getConfig("max-length")
                + "&isNull=" + (p == null)
                + "&withNull=" + getConfig("with-null", "false"));
    }
}
