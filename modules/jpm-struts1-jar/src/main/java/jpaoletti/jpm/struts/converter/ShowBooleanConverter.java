package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.Converter;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.struts.PMEntitySupport;

/**Converter for showing a boolean value.<br>
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.ShowBooleanConverter" operations="show list">
 *     <properties>
 *         <property name="true-text" value="pm.true.text" />
 *         <property name="false-text" value="pm.false.text" />
 *     <properties>
 * </converter>
 * }
 * </pre>
 * @author jpaoletti
 * */
public class ShowBooleanConverter extends Converter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        return Boolean.valueOf((String) ctx.getFieldValue());
    }

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        Object value = getValue(ctx.getEntityInstance(), ctx.getField());
        if (!(value instanceof Boolean)) {
            throw new ConverterException("invalid.conversion");
        }
        boolean b = ((Boolean) value).booleanValue();
        String s;
        if (b) {
            s = getConfig("true-text", "pm.converter.boolean_converter.yes");
        } else {
            s = getConfig("false-text", "pm.converter.boolean_converter.no");
        }
        return super.visualize("localized_string_converter.jsp?value=" + PMEntitySupport.toHtml(s));
    }
}
