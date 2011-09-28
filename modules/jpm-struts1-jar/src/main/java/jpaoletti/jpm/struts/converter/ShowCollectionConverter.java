package jpaoletti.jpm.struts.converter;

import java.util.Collection;
import jpaoletti.jpm.converter.Converter;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.converter.IgnoreConvertionException;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.struts.PMEntitySupport;
import jpaoletti.jpm.struts.PMStrutsConstants;

/**Converter for a collection (1..* aggregation).<br>
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.ShowCollectionConverter">
 *     <operationId>show</operationId>
 * </converter>
 * }
 * </pre>
 * @author jpaoletti
 * */
public class ShowCollectionConverter extends Converter implements PMStrutsConstants {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        throw new IgnoreConvertionException();
    }

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        try {
            final Collection<?> list = (Collection<?>) getValue(ctx.getEntityInstance(), ctx.getField());
            final StringBuilder sb = new StringBuilder();
            sb.append("<ul>");
            for (Object o : list) {
                sb.append("<li>");
                sb.append(o.toString());
                sb.append("</li>");
            }
            sb.append("</ul>");
            ctx.put(PM_VOID_TEXT, PMEntitySupport.toHtml(sb.toString()));
            return super.visualize("void.jsp?");
        } catch (Exception e1) {
            getPresentationManager().error(e1);
            throw new ConverterException("pm_core.converter.not.collection");
        }
    }
}
