package jpaoletti.jpm.struts.converter;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.struts.PMStrutsContext;

/**Converter for integer <br>
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.EditSingleAggregationConverter">
 *     <operationId>edit</operationId>
 *         <properties>
 *             <property name="entity"          value="the_other_entity" />
 *             <property name="with-null"       value="true" />
 *             <property name="filter"          value="jpaoletti.jpm.core.ListFilterXX" />
 *             <property name="filter"          value="jpaoletti.jpm.core.ListFilterXX" />
 *             <property name="sort-field"      value="xxx" />
 *             <property name="sort-direction"  value="asc | desc" />
 *             <property name="search"          value="true | false" />
 *             <property name="min-search-size" value="0" />
 *         </properties>
 * </converter>
 * }
 * </pre>
 * @author jaoletti 
 * */
public class EditSingleAggregationConverter extends AbstractCollectionConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        try {
            String s = (String) ctx.getFieldValue();
            if (s == null || s.trim().compareTo("") == 0) {
                return null;
            }
            Integer x = Integer.parseInt(s);
            if (x == -1) {
                return null;
            }
            final List<?> list = recoverList((PMStrutsContext) ctx, getConfig("entity"), true);
            return list.get(x);
        } catch (Exception e1) {
            ctx.getPresentationManager().error(e1);
            throw new ConverterException("Cannot convert single aggregation");
        }
    }

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        String wn = getConfig("with-null", "false");
        boolean withNull = (wn == null || wn.compareTo("true") != 0) ? false : true;
        final String filter = getConfig("filter");
        final String entity = getConfig("entity");
        saveList((PMStrutsContext) ctx, entity);

        final List<?> list = recoverList((PMStrutsContext) ctx, entity, false);
        final List<ACListItem> finalist = new ArrayList<ACListItem>();
        if (withNull) {
            finalist.add(new ACListItem(-1, "", false));
        }
        for (Object object : list) {
            if (object != null) {
                finalist.add(new ACListItem(list.indexOf(object), object.toString(), object.equals(ctx.getFieldValue())));
            }
        }
        Gson gson = new Gson();
        ctx.put("json_list", gson.toJson(finalist));
        return super.visualize("single_aggregation_converter.jsp"
                + "?filter=" + filter
                + "&entity=" + entity
                + "&with_null=" + withNull
                + "&show_search=" + hasSearch(ctx)
                + "&min_search_size=" + getConfig("min-search-size", "0")
                + "&prop=" + ctx.getField().getProperty());
    }

    private boolean hasSearch(PMContext ctx) {
        final String s = getConfig("search", "false");
        return ("true".equals(s));
    }

    public static class ACListItem {

        private int id;
        private String text;
        private boolean selected;

        public ACListItem(int id, String text, boolean selected) {
            this.id = id;
            this.text = text;
            this.selected = selected;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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
