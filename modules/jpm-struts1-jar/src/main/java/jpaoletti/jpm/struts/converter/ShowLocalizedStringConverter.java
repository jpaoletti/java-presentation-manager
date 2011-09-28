package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.converter.ShowStringConverter;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.struts.PMEntitySupport;

public class ShowLocalizedStringConverter extends ShowStringConverter {

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        final String s = (String) getValue(ctx.getEntityInstance(), ctx.getField());
        return super.visualize("localized_string_converter.jsp?value=" + PMEntitySupport.toHtml(s));
    }
}
