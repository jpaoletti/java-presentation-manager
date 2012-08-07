package jpaoletti.jpm.struts.actions;

import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.EntityContainer;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 * An ajax action to load a weak list.
 *
 * @author jpaoletti
 */
public class WeakListAction extends ActionSupport {

    @Override
    protected synchronized void doExecute(PMStrutsContext ctx) throws PMException {
        final String entityId = (String) ctx.getParameter("entityId");
        final String weakEntityId = (String) ctx.getParameter("weakid");
        final String field = (String) ctx.getParameter("field");
        final EntityContainer weakContainer = ctx.getEntityContainer(weakEntityId);
        final Entity weak = weakContainer.getEntity();
        final EntityContainer entityContainer = ctx.getEntityContainer(entityId);
        final Object instance = ctx.getEntityContainer(entityId).getEntity().getDataAccess().refresh(ctx, entityContainer.getSelected().getInstance());
        final Object list = ctx.getPresentationManager().get(instance, entityContainer.getEntity().getFieldById(field).getProperty());

        ctx.put("weakContainer", weakContainer);
        ctx.put("weak", weak);
        ctx.put("list", list);
        ctx.put("addInstanceId", (String) ctx.getParameter("addInstanceId"));
        ctx.put("woperation", weak.getOperations().getOperation("list"));
        success(ctx, "/WEB-INF/jsp/weaklist.jsp", false);
    }
}
