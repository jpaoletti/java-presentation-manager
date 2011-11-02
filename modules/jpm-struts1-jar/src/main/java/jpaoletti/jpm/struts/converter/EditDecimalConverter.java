package jpaoletti.jpm.struts.converter;

import java.math.BigDecimal;

import java.math.RoundingMode;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;

/**Converter for integer <br>
 * Properties: currency and format
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.EditDecimalConverter">
 *     <properties>
 *         <property name="separator" value="." />
 *         <property name="decimals" value="2" />
 *         <property name="with-null" value="false" />
 *     </properties>
 * </converter>
 * }
 * </pre>
 * @author jpaoletti
 * */
public class EditDecimalConverter extends DefaultStrutsConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        try {
            final String separator = getConfig("separator", ".");
            return new BigDecimal(((String) ctx.getFieldValue()).replace(separator, "."));
        } catch (Exception e) {
            return null;
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
