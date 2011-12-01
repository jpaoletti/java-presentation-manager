package jpaoletti.jpm.converter;

import jpaoletti.jpm.core.PMContext;

/**Converter for strings <br>
 * <pre>
 * {@code
 * TO-DO
 * }
 * </pre>
 * @author jpaoletti
 * */
public class ToStringConverter extends Converter {

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        return visualize(ctx.getFieldValue());
    }
}
