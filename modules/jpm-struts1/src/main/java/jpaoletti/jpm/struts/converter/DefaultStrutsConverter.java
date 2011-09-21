package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.Converter;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.struts.PMEntitySupport;
import jpaoletti.jpm.struts.PMStrutsConstants;

/**
 *
 * @author jpaoletti
 */
public class DefaultStrutsConverter extends Converter implements PMStrutsConstants {

    @Override
    public Object visualize(PMContext ctx) throws ConverterException {
        Object s = ctx.getFieldValue();
        if (s != null && s instanceof String && (s.toString().contains(".jsp?") || s.toString().contains(".do?"))) {
            return s;
        } else {
            if (s == null) {
                s = "";
            }
            ctx.put(PM_VOID_TEXT, PMEntitySupport.toHtml(s.toString()));
            return "void.jsp?";
        }
    }
}
