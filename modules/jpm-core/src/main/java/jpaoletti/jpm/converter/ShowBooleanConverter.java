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
    public String visualize(PMContext ctx) throws ConverterException {
        if (!(ctx.getFieldValue() instanceof Boolean)) {
            throw new ConverterException("boolean.converter.invalid.value");
        }
        final String result = ((Boolean) ctx.getFieldValue())
                ? PresentationManager.getMessage(getConfig("true-text", "pm.converter.boolean_converter.yes"))
                : PresentationManager.getMessage(getConfig("false-text", "pm.converter.boolean_converter.no"));
        ctx.setFieldValue(result);
        return visualize(result);
    }
}
