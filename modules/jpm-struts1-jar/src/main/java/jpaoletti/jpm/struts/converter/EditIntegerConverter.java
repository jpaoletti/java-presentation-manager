package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;

/**Converter for integer <br>
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.EditIntegerConverter">
 *     <operationId>edit</operationId>
 * </converter>
 * }
 * </pre>
 * @author jpaoletti
 * */
public class EditIntegerConverter extends EditStringConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        try {
            return Integer.parseInt((String) ctx.getFieldValue());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
