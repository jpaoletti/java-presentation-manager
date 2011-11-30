package jpaoletti.jpm.converter;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PresentationManager;

public class ShowLocalizedStringConverter extends Converter {

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        final String s = PresentationManager.getMessage((String) ctx.getFieldValue());
        ctx.setFieldValue(s);
        return s;
    }
}
