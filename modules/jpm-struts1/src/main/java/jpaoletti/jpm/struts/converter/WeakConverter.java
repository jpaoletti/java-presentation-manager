package jpaoletti.jpm.struts.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 * Converter for weak entities.
 *
 * Properties:
 *
 * <b>weak-entity</b> Id of the weak entity
 * <b>show-list</b> If true (default) show the list of items
 * <b>show-modify</b> If true (default) show a button to edit screen
 *
 * @author jpaoletti
 */
public class WeakConverter extends StrutsEditConverter {

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        final StringBuilder sb = new StringBuilder();
        sb.append("weak_converter.jsp?weakid=");
        sb.append(getConfig("weak-entity"));
        sb.append("&showlist=");
        sb.append(getConfig("show-list", "true"));
        sb.append("&showbutton=");
        sb.append(getConfig("show-modify", "true"));
        sb.append("&property=");
        sb.append(ctx.getField().getProperty());
        sb.append("&buttontext=");
        sb.append(getConfig("button-text", "pm.struts.weak.converter.edit"));

        return super.visualize(sb.toString());
    }

    public static Collection getCollection(PMStrutsContext ctx) throws PMException {
        final Collection collection = (Collection) ctx.getPresentationManager().get(ctx.getSelected().getInstance(), ctx.getRequest().getParameter("property"));
        final List<Object> result = new ArrayList<Object>();
        final Entity entity = getEntity(ctx);
        if (entity == null) {
            ctx.getPresentationManager().error("Weak entity not found");
            throw new ConverterException("pm.struts.entity.not.found");
        }
        if (collection != null) {
            for (Object object : collection) {
                result.add(entity.getDataAccess().refresh(ctx, object));
            }
        }
        return result;
    }

    public static Entity getEntity(PMStrutsContext ctx) {
        return ctx.getPresentationManager().getEntity((String) ctx.getRequest().getParameter("weakid"));
    }
}
