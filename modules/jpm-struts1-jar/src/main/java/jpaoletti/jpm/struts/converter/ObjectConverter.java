package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;

/**Converter for integer <br>
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.ObjectConverter">
 *     <operationId>edit</operationId>
 *         <properties>
 *             <property name="entity"          value="other_entity" />
 *             <property name="id"              value="other_entity_id" />
 *             <property name="display"         value="other_entity_display" />
 *             <property name="with-null"       value="true" />
 *             <property name="filter"          value="jpaoletti.jpm.core.ListFilterXX" />
 *             <property name="sort-field"      value="xxx" /> NOT IMPLEMENTED!
 *             <property name="sort-direction"  value="asc | desc" /> NOT IMPLEMENTED!
 *             <property name="min-search-size" value="0" />
 *         </properties>
 * </converter>
 * }
 * </pre>
 * @author jpaoletti
 * */
public class ObjectConverter extends StrutsEditConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        try {
            final String _id = getConfig("id");
            final String _entity = getConfig("entity");
            final Entity entity = ctx.getPresentationManager().getEntity(_entity);
            final String newFieldValue = (String) ctx.getFieldValue();
            if (newFieldValue == null || newFieldValue.trim().compareTo("-1") == 0) {
                return null;
            }
            return entity.getDataAccess().getItem(ctx, _id, newFieldValue);
        } catch (PMException ex) {
            throw new ConverterException(ex);
        }
    }

    @Override
    public Object visualize(PMContext ctx) throws ConverterException {
        final String _id = getConfig("id");
        if (_id == null) {
            throw new ConverterException("object.converter.id.cannot.be.null");
        }
        final String _display = getConfig("display");
        if (_display == null) {
            throw new ConverterException("object.converter.display.cannot.be.null");
        }
        final Object fieldValue = ctx.getFieldValue();
        if (fieldValue == null) {
            ctx.put("_selected_value", "");
            ctx.put("_selected_id", "-1");
            ctx.put("_with_null", false); //false because selected is already null
        } else {
            String[] _display_fields = _display.split("[ ]");
            String _selected_value = "" ;
            for (String _display_field : _display_fields) {
                _selected_value += " "+ctx.getPresentationManager().getAsString(fieldValue, _display_field);
            }
            ctx.put("_selected_value", _selected_value);
            ctx.put("_selected_id", ctx.getPresentationManager().getAsString(fieldValue, _id));
            ctx.put("_with_null", getConfig("with-null", "false"));
        }
        ctx.put("_min_search_size", getConfig("min-search-size", "0"));
        ctx.put("_entity", getConfig("entity"));
        ctx.put("_id", _id);
        ctx.put("_display", _display);
        ctx.put("_filter", getConfig("filter"));
        return super.visualize("object_converter.jsp?");
    }
}
