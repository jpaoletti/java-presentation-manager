package jpaoletti.jpm.converter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import jpaoletti.jpm.core.PMContext;

/**Converter for BigDecimals and amounts <br>
 * Properties: currency and format
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.ShowDecimalConverter">
 *     <operationId>show</operationId>
 *     <properties>
 *         <property name="currency" value="U$S" />
 *         <property name="format" value="0.00" />
 *         <property name="null-value" value="$ 0.00" />
 *     </properties>
 * </converter>
 * }
 * </pre>
 * @author jpaoletti
 * */
public class ShowDecimalConverter extends ShowStringConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        throw new IgnoreConvertionException("");
    }

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        BigDecimal o = null;
        try {
            o = (BigDecimal) getValue(ctx.getSelected().getInstance(), ctx.getField());
        } catch (Exception e) {
        }
        NumberFormat formatter = new DecimalFormat(getConfig("format", "#0.00"));
        if (o == null) {
            return getConfig("null-value", "0.00");
        } else {
            return visualize(formatter.format(o));
        }
    }
}
