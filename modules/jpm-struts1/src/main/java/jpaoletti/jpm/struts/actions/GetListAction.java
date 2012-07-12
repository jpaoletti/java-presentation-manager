package jpaoletti.jpm.struts.actions;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.struts.CollectionHelper;
import jpaoletti.jpm.struts.PMStrutsContext;
import jpaoletti.jpm.util.KeyValue;

/**
 * Needs the following parameters. <br/>
 *
 * <ul> <li>entity: Entity id of the wanted list</li> <li>display: list of
 * properties separated by blanks for displaying</li> <li>filter: asuming that
 * display is a string, the filter looks for the item list with display "like"
 * the value</li> <li>filter_class: filter class implementing
 * jpaoletti.jpm.core.ListFilter</li> <li></li> </ul>
 *
 * @author jpaoletti
 */
public class GetListAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        final CollectionHelper helper = new CollectionHelper((String) ctx.getParameter("display"));
        final Gson gson = new Gson();
        try {
            final String _entity = (String) ctx.getParameter("entity");
            final Entity entity = ctx.getPresentationManager().getEntity(_entity);
            if (entity == null) {
                throw new ConverterException("Cannot find entity " + entity);
            }
            final String originalEntity = (String) ctx.getParameter("originalEntity", "");
            final String originalOp = (String) ctx.getParameter("originalOperation", "");
            final String relatedFieldName = (String) ctx.getParameter("relatedFieldName", "");
            final String _relatedFieldValue = (String) ctx.getParameter("relatedFieldValue", "");
            ctx.setFieldValue(_relatedFieldValue);
            Object object = null;
            if (!"".equals(originalEntity) && !"".equals(originalOp) && !"".equals(relatedFieldName) && !"".equals(_relatedFieldValue)) {
                object = ctx.getPresentationManager().getEntity(originalEntity).getFieldById(relatedFieldName).getConverter(originalOp).build(ctx);
            }
            if (object == null && "true".equals(ctx.getParameter("relatedRequired", "false"))) {
                jSONSuccess(ctx, gson.toJson(new ArrayList<KeyValue>()));
            } else {
                final List<KeyValue> finalist = helper.getFullList(ctx, entity,
                        (String) ctx.getParameter("filter_class"),
                        (String) ctx.getParameter("filter"),
                        (String) ctx.getParameter("sortField"),
                        (String) ctx.getParameter("sortDir"), relatedFieldName,
                        object);
                jSONSuccess(ctx, finalist);
            }
        } catch (PMException ex) {
            throw ex;
        } catch (Exception ex) {
            ctx.getPresentationManager().error(ex);
            jSONSuccess(ctx, ex.getMessage());
        }
    }
}
