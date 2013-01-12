package jpaoletti.jpm.struts.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.converter.IgnoreConvertionException;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.EntityInstanceWrapper;
import jpaoletti.jpm.core.InstanceId;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.struts.CollectionHelper;

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
 *         <property name="sort-field"      value="xxx" />
 *         <property name="sort-direction"  value="asc | desc" />
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
        if (getConfig("readonly", "true").equalsIgnoreCase("true")) {
            throw new IgnoreConvertionException();
        }
        final Entity entity = getEntity();
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
            if (values != null) {
                for (Object object : values) {
                    final String id = (String) object;
                    final Object item = entity.getDataAccess().getItem(ctx, new InstanceId(id));
                    if (item != null) {
                        result.add(item);
                    }
                }
            }
        } catch (PMException ex) {
            throw new ConverterException(ex);
        }
        return result;
    }

    @Override
    public Object visualize(PMContext ctx) throws ConverterException {
        final CollectionHelper helper = new CollectionHelper(getConfig("display"));
        final Entity entity = getEntity();
        final Collection fieldValue = (Collection) ctx.getFieldValue();
        if (getConfig("readonly", "true").equalsIgnoreCase("true")) {
            ctx.put("collection", helper.getListOfTexts(fieldValue));
            return super.visualize("collection-show.jsp?");
        } else {
            ctx.put("fullList", helper.getFullList(ctx, entity, getConfig("filter"), null, getConfig("sort-field"), getConfig("sort-direction"), null, null));
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

    /**
     * Getter for entity property. Must be defined.
     * @return the entity
     */
    protected Entity getEntity() throws ConverterException {
        final String _entity = getConfig("entity");
        if (_entity == null) {
            throw new ConverterException("collection.converter.entity.cannot.be.null");
        }
        final Entity entity = PresentationManager.getPm().getEntity(_entity);
        if (entity == null) {
            throw new ConverterException("collection.converter.entity.cannot.be.null");
        }
        return entity;
    }
}
