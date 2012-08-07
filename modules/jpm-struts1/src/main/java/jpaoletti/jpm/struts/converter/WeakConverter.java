package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.EntityContainer;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.message.MessageFactory;

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
        final String weakEntityId = getConfig("weak-entity");
        final EntityContainer weakContainer = ctx.getEntityContainer(weakEntityId);
        if (weakContainer == null) {
            throw new ConverterException(MessageFactory.error(ctx.getEntity(), ctx.getField(), "weak.entity.not.found", weakEntityId));
        } else {
            final Entity weak = weakContainer.getEntity();
            final StringBuilder sb = new StringBuilder();
            sb.append("weak_converter.jsp?weakid=");
            sb.append(weakEntityId);
            sb.append("&showlist=");
            sb.append(getConfig("show-list", "true"));
            sb.append("&addInstanceId=");
            sb.append(getConfig("add-instance-id", "false"));
            sb.append("&showbutton=");
            sb.append(getConfig("show-modify", "true"));
            sb.append("&property=");
            sb.append(ctx.getField().getProperty());
            sb.append("&buttontext=");
            sb.append(getConfig("button-text", "pm.struts.weak.converter.edit"));

            ctx.put("weakContainer", weakContainer);
            ctx.put("weak", weak);
            ctx.put("woperation", weak.getOperations().getOperation("list"));
            return super.visualize(sb.toString());
        }
    }
}
