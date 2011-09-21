package jpaoletti.jpm.struts.actions;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.EntityFilter;
import jpaoletti.jpm.core.FilterBehavior;
import jpaoletti.jpm.core.FilterOperation;
import jpaoletti.jpm.core.ListFilter;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 * Needs the following parameters. <br/>
 *
 * <ul>
 *  <li>entity: Entity id of the wanted list</li>
 *  <li>id: identifier of the wanted list</li>
 *  <li>display: list of properties separated by blanks for displaying</li>
 *  <li>filter: asuming that display is a string, the filter looks for the item list with display "like" the value</li>
 *  <li>filter_class: filter class implementing jpaoletti.jpm.core.ListFilter</li>
 *  <li></li>
 * </ul>
 *
 * @author jpaoletti
 */
public class GetListAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        final Gson gson = new Gson();
        ctx.getResponse().setContentType("application/json");
        final List<ACListItem> finalist = new ArrayList<ACListItem>();
        try {
            final String _entity = ctx.getRequest().getParameter("entity");
            final Entity entity = ctx.getPresentationManager().getEntity(_entity);
            if (entity == null) {
                throw new ConverterException("Cannot find entity " + entity);
            }
            final String _id = ctx.getRequest().getParameter("id");
            final String _display = ctx.getRequest().getParameter("display");
            if (_id == null) {
                throw new PMException("getlist.id.cannot.be.null");
            }
            if (_display == null) {
                throw new PMException("getlist.display.cannot.be.null");
            }
            final Integer _from = getInt(ctx, "from");
            final Integer _count = getInt(ctx, "count");
            final String _filter = ctx.getRequest().getParameter("filter");
            final String _filter_class = ctx.getRequest().getParameter("filter_class");
            ListFilter lfilter = null;
            if (_filter_class != null && _filter_class.compareTo("null") != 0 && _filter_class.compareTo("") != 0) {
                lfilter = (ListFilter) ctx.getPresentationManager().newInstance(_filter_class);
            }
            List<?> list = null;
            synchronized (entity) {
                ListFilter tmp = entity.getListfilter();
                entity.setListfilter(lfilter);
                if (_filter != null) {
                    final EntityFilter filter = entity.getDataAccess().createFilter(ctx);
                    filter.setBehavior(FilterBehavior.OR);
                    String[] _display_fields = _display.split("[ ]");
                    for (String _display_field : _display_fields) {
                        filter.addFilter(_display_field, _filter, FilterOperation.LIKE);
                    }
                    filter.process(entity);
                    list = entity.getList(ctx, filter, null, _from, _count);
                } else {
                    list = entity.getList(ctx, null, null, _from, _count);
                }
                entity.setListfilter(tmp);
            }
            for (Object object : list) {
                if (object != null) {
                    String[] _display_fields = _display.split("[ ]");
                    String _selected_value = "";
                    for (String _display_field : _display_fields) {
                        _selected_value += " " + ctx.getPresentationManager().getAsString(object, _display_field);
                    }
                    finalist.add(new ACListItem(
                            ctx.getPresentationManager().getAsString(object, _id),
                            _selected_value,
                            false));
                }
            }
        } catch (Exception ex) {
            ctx.getPresentationManager().error(ex);
        }
        try {
            ctx.getResponse().getWriter().print(gson.toJson(finalist));
        } catch (IOException ex) {
        }
    }

    protected Integer getInt(PMStrutsContext ctx, String param) {
        final String from = ctx.getRequest().getParameter(param);
        if (from == null) {
            return null;
        }
        return Integer.parseInt(from);
    }

    public static class ACListItem {

        private String id;
        private String text;
        private boolean selected;

        public ACListItem(String id, String text, boolean selected) {
            this.id = id;
            this.text = text;
            this.selected = selected;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
