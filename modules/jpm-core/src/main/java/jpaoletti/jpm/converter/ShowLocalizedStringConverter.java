package jpaoletti.jpm.converter;

import jpaoletti.jpm.core.PMContext;

public class ShowLocalizedStringConverter extends Converter {

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        final String s = getPm().message((String) ctx.getFieldValue());
        ctx.setFieldValue(s);
        return s;
    }
}
