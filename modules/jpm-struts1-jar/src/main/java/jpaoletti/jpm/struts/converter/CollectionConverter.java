package jpaoletti.jpm.struts.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.EntityInstanceWrapper;
import jpaoletti.jpm.core.InstanceId;
import jpaoletti.jpm.core.ListFilter;
import jpaoletti.jpm.core.ListSort;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.PresentationManager;
import org.javatuples.Pair;

/**
 * A converter for collections. Entity idField must be defined.<br>
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.CollectionConverter">
 *     <properties>
 *         <property name="entity"          value="other_entity" />
 *         <property name="readonly"        value="true | false" />
 *         <property name="display"         value="{field1} some text {field2} ... " />
 *         <property name="filter"          value="jpaoletti.jpm.core.ListFilterXX" />
 *         <property name="sort-field"      value="xxx" /> NOT IMPLEMENTED!
 *         <property name="sort-direction"  value="asc | desc" /> NOT IMPLEMENTED!
 *     </properties>
 * </converter>
 * }
 * </pre>
 * 
 * @author jpaoletti
 */
public class CollectionConverter extends DefaultStrutsConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        final String _entity = getConfig("entity");
        if (_entity == null) {
            throw new ConverterException("collection.converter.entity.cannot.be.null");
        }
        final Entity entity = PresentationManager.getPm().getEntity(_entity);
        if (entity == null) {
            throw new ConverterException("collection.converter.entity.cannot.be.null");
        }
        final String collection_class = getConfig("collection-class");
        if (collection_class == null) {
            throw new ConverterException("pm.struts.converter.class.mustbedefined");
        }
        final Object instance = ctx.getEntityInstanceWrapper().getInstance();
        Collection<Object> result = null;
        result = (Collection<Object>) getValue(instance, ctx.getField());
        if (result == null) {
            result = (Collection<Object>) ctx.getPresentationManager().newInstance(collection_class);
        }
        result.clear();
        final Object[] values = ctx.getParameters("f_" + ctx.getField().getId());
        try {
            for (Object object : values) {
                final String id = (String) object;
                result.add(entity.getDataAccess().getItem(ctx, new InstanceId(id)));
            }
        } catch (PMException ex) {
            throw new ConverterException(ex);
        }
        return result;
    }

    @Override
    public Object visualize(PMContext ctx) throws ConverterException {
        final String _entity = getConfig("entity");
        if (_entity == null) {
            throw new ConverterException("collection.converter.entity.cannot.be.null");
        }
        final Entity entity = PresentationManager.getPm().getEntity(_entity);
        if (entity == null) {
            throw new ConverterException("collection.converter.entity.cannot.be.null");
        }
        final Collection fieldValue = (Collection) ctx.getFieldValue();
        if (getConfig("readonly", "true").equalsIgnoreCase("true")) {
            final List<String> listOfTexts = getListOfTexts(fieldValue);
            ctx.put("collection", listOfTexts);
            return super.visualize("collection-show.jsp?");
        } else {
            ctx.put("fullList", getFullList(ctx, entity));

            final List<String> idList = new ArrayList<String>();
            if (fieldValue != null) {
                try {
                    for (Object object : fieldValue) {
                        idList.add(entity.getDataAccess().getInstanceId(ctx, new EntityInstanceWrapper(object)).getValue());
                    }
                } catch (PMException ex) {
                    throw new ConverterException(ex);
                }
            }
            ctx.put("idList", idList);
            return super.visualize("collection-edit.jsp?");
        }
    }

    protected List<String> getListOfTexts(final Collection collection) throws ConverterException {
        final String _display = getConfig("display");
        if (_display == null) {
            throw new ConverterException("object.converter.display.cannot.be.null");
        }
        final String[] _display_fields = _display.split("[{.*?}]");
        final List<String> result = new ArrayList();
        for (Object object : collection) {
            result.add(getObjectDisplay(_display_fields, object, _display));
        }
        return result;
    }

    protected String getObjectDisplay(final String[] _display_fields, Object object, final String _display) {
        final Map<String, String> replaces = new HashMap<String, String>();
        for (String _display_field : _display_fields) {
            if (_display_field != null && !"".equals(_display_field.trim())) {
                replaces.put("\\{" + _display_field + "\\}", PresentationManager.getPm().getAsString(object, _display_field));
            }
        }
        String result = _display;
        for (Map.Entry<String, String> entry : replaces.entrySet()) {
            result = result.replaceFirst(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public List<Pair<String, String>> getFullList(PMContext ctx, Entity entity) throws ConverterException {
        final String filter = getConfig("filter");
        final String sortField = getConfig("sord-field");
        final String sortd = getConfig("sord-direction");
        final ListSort sort = new ListSort(sortField, (sortd != null) ? ListSort.SortDirection.ASC : ListSort.SortDirection.DESC);

        ListFilter lfilter = null;
        if (filter != null && filter.compareTo("null") != 0 && filter.compareTo("") != 0) {
            lfilter = (ListFilter) ctx.getPresentationManager().newInstance(filter);
        }
        List<?> list = null;
        synchronized (entity) {
            //TODO review
            try {
                final ListFilter tmp = entity.getListfilter();
                entity.setListfilter(lfilter);
                list = entity.getList(ctx, null, sort, null, null);
                entity.setListfilter(tmp);
            } catch (PMException ex) {
                throw new ConverterException(ex);
            }
        }
        if (list == null) {
            return null;
        }
        final String _display = getConfig("display");
        if (_display == null) {
            throw new ConverterException("object.converter.display.cannot.be.null");
        }
        final String[] _display_fields = _display.split("[{.*?}]");
        final List<Pair<String, String>> result = new ArrayList<Pair<String, String>>();
        try {
            for (Object object : list) {
                result.add(new Pair<String, String>(
                        entity.getDataAccess().getInstanceId(ctx, new EntityInstanceWrapper(object)).getValue(),
                        getObjectDisplay(_display_fields, object, _display)));
            }
        } catch (PMException ex) {
            throw new ConverterException(ex);
        }
        return result;
    }
}
