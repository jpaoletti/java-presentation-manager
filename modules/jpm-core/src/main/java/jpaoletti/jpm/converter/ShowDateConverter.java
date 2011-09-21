package jpaoletti.jpm.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jpaoletti.jpm.core.PMContext;

/**Converter for date.<br>
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.ShowBooleanConverter">
 *     <operationId>show</operationId>
 *     <properties>
 *         <property name="format" value="dd/MM/yyyy" />
 *     <properties>
 * </converter>
 * }
 * </pre>
 * @author jpaoletti
 * */
public class ShowDateConverter extends Converter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        throw new IgnoreConvertionException("");
    }

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        final Date o = (Date) getValue(ctx.getEntityInstance(), ctx.getField());
        return (o == null) ? "" : getDateFormat().format(o);
    }

    /**
     *
     * @return the DateFormat object with the configured date format
     */
    protected DateFormat getDateFormat() {
        DateFormat df = new SimpleDateFormat(getConfig("format", "MM/dd/yyyy"));
        return df;
    }
}
