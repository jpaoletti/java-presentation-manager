package jpaoletti.jpm.struts.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;

/**
 * Converter for big decimals <br> Properties: separator, decimals and not-null
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.EditDecimalConverter">
 *     <properties>
 *         <property name="separator" value="." />
 *         <property name="decimals" value="2" />
 *         <property name="not-null" value="false" />
 *     </properties>
 * </converter>
 * }
 * </pre>
 *
 * @author jpaoletti
 *
 */
public class EditDecimalConverter extends DefaultStrutsConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        try {
            final String separator = getConfig("separator", ".");
            final String v = (String) ctx.getFieldValue();
            if ((v == null || "".equals(v.trim()))) {
                if (getConfig("not-null", "false").equals("true")) {
                    throw new ConverterException("pm.struts.converter.invalid.null.decimal");
                } else {
                    return null;
                }
            }
            return new BigDecimal(v.replace(separator, "."));
        } catch (Exception e) {
            throw new ConverterException("pm.struts.converter.invalid.decimal");
        }
    }

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        BigDecimal p = (BigDecimal) ctx.getFieldValue();
        if (p == null) {
            p = (BigDecimal) getValue(ctx.getEntityInstance(), ctx.getField());
        }
        final Integer decimals = Integer.parseInt(getConfig("decimals", "2"));
        final String separator = getConfig("separator", ".");
        final String value = (p == null) ? "" : p.setScale(
                decimals,
                RoundingMode.HALF_EVEN).toString().replace(".", separator);
        ctx.setFieldValue(value);
        return super.visualize("decimal-edit.jsp?"
                + "&isNull=" + (p == null)
                + "&withNull=" + getConfig("with-null", "false")
                + "&separator=" + separator
                + "&decimals=" + decimals);
    }
}
