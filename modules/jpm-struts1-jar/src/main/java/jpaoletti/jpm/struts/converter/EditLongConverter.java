package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;

/**Converter for long <br>
 * Properties: currency and format
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.EditLongConverter">
 *     <operationId>edit</operationId>
 * </converter>
 * }
 * </pre>
 * @author jpaoletti
 * */
public class EditLongConverter extends EditStringConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        try {
            return Long.parseLong((String) ctx.getFieldValue());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
