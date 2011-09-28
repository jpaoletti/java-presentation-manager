package jpaoletti.jpm.struts.converter;

import java.math.BigDecimal;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;

/**Converter for integer <br>
 * Properties: currency and format
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.EditDecimalConverter">
 *     <operationId>edit</operationId>
 * </converter>
 * }
 * </pre>
 * @author jpaoletti
 * */
public class EditDecimalConverter extends EditStringConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        try {
            return new BigDecimal((String) ctx.getFieldValue());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
