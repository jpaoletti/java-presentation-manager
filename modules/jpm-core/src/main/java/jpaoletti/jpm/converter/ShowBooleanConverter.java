package jpaoletti.jpm.converter;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PresentationManager;

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
        if (!(ctx.getFieldValue() instanceof Boolean)) {
            throw new ConverterException("boolean.converter.invalid.value");
        }
        if ((Boolean) ctx.getFieldValue()) {
            return visualize(PresentationManager.getMessage(getConfig("true-text", "pm.converter.boolean_converter.yes")));
        } else {
            return visualize(PresentationManager.getMessage(getConfig("false-text", "pm.converter.boolean_converter.no")));
        }
    }
}
