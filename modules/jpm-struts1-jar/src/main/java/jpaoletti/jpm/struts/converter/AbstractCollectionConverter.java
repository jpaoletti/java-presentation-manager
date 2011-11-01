package jpaoletti.jpm.struts.converter;

import java.util.List;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.ListFilter;
import jpaoletti.jpm.core.ListSort;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 *
 * @author jpaoletti
 */
@Deprecated
public abstract class AbstractCollectionConverter extends StrutsEditConverter {

    public List<?> saveList(PMStrutsContext ctx, String eid) {
        try {
            final List<?> list = getList(ctx, null);
            ctx.getSession().setAttribute(ctx.getTmpName(), list);
            return list;
        } catch (PMException ex) {
            ctx.getPresentationManager().error(ex);
            return null;
        }
    }

    public static List<?> recoverList(PMStrutsContext ctx, String eid, boolean remove) {
        try {
            final List<?> r = (List<?>) ctx.getSession().getAttribute(ctx.getTmpName());
            if (remove) {
                ctx.getSession().removeAttribute(ctx.getTmpName());
            }
            return r;
        } catch (PMException ex) {
            ctx.getPresentationManager().error(ex);
            return null;
        }
    }

    public List<?> getList(PMStrutsContext ctx, String entity_id) throws PMException {
        final String filter = getConfig("filter");
        final String eid = (entity_id == null) ? getConfig("entity") : entity_id;
        final String sortField = getConfig("sord-field");
        final String sortd = getConfig("sord-direction");
        final ListSort sort = new ListSort(sortField, (sortd != null) ? ListSort.SortDirection.ASC : ListSort.SortDirection.DESC);

        ListFilter lfilter = null;
        if (filter != null && filter.compareTo("null") != 0 && filter.compareTo("") != 0) {
            lfilter = (ListFilter) ctx.getPresentationManager().newInstance(filter);
        }
        final Entity e = ctx.getPresentationManager().getEntity(eid);
        if (e == null) {
            throw new ConverterException("Cannot find entity " + eid);
        }
        return e.getList(ctx, lfilter, sort, null, null);
    }
}
