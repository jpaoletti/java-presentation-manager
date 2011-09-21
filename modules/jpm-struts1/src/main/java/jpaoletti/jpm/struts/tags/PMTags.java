package jpaoletti.jpm.struts.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.jsp.tagext.TagSupport;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.Field;
import jpaoletti.jpm.core.Highlight;
import jpaoletti.jpm.core.Operation;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.PaginatedList;
import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.core.operations.OperationScope;
import jpaoletti.jpm.struts.PMEntitySupport;
import jpaoletti.jpm.struts.PMStrutsContext;
import jpaoletti.jpm.util.DisplacedList;

/**
 * Helper tags for Presentation Manager
 *
 * @author jpaoletti
 * @since 15/09/2011
 * @version 1.2
 *
 */
public class PMTags extends TagSupport {

    public static String listItemOperations(PMStrutsContext ctx, DisplacedList list, Object item) {
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("<span style='white-space: nowrap;' class='operationspopup'>");
            for (Operation itemOperation : ctx.getOperations(item, ctx.getOperation()).getOperations()) {
                //If we have permission
                if (ctx.getPmsession().getUser().hasPermission(itemOperation.getPerm())) {
                    //if operation is at item scope
                    if (OperationScope.ITEM.is(itemOperation.getScope())) {
                        String furl = "";
                        if (itemOperation.getUrl() != null) {
                            furl = itemOperation.getUrl();
                        } else {
                            furl = getContextPath() + "/" + itemOperation.getId() + ".do?pmid=" + ctx.getEntity().getId() + "&item=" + list.indexOf(item);
                        }
                        sb.append("<a class='confirmable_");
                        sb.append(itemOperation.getConfirm());
                        sb.append("' href='");
                        sb.append(furl);
                        sb.append("' id='operation");
                        sb.append(itemOperation.getId());
                        sb.append("' title='");
                        sb.append(PresentationManager.getMessage("operation." + itemOperation.getId()));
                        sb.append("'><img src='");
                        sb.append(getContextPath());
                        sb.append("/templates/");
                        sb.append(ctx.getPresentationManager().getTemplate());
                        sb.append("/img/").append(itemOperation.getId());
                        sb.append(".gif' alt='");
                        sb.append(itemOperation.getId());
                        sb.append("' /></a>");
                    }
                }
            }
            sb.append("&nbsp;</span>");
            return sb.toString();
        } catch (PMException ex) {
            PresentationManager.getPm().error(ex);
            return "?";
        }
    }

    public static String highlight(Entity entity, Field field, Object item, Object field_value) {
        final Highlight highlight = entity.getHighlight(field, item);
        if (highlight != null) {
            return highlight.getStyle();
        } else {
            return "";
        }
    }

    public static String rowNumber(PaginatedList pmlist, Object item) {
        if (pmlist.isShowRowNumber()) {
            return String.format("[%0" + pmlist.getListTotalDigits() + "d]", pmlist.getContents().indexOf(item));
        } else {
            return "";
        }
    }

    /**
     * This method show a tooltip if the key is defined
     * @param key Key
     */
    public static String tooltip(Entity entity, Field field) {
        final String key = "pm.field." + entity.getId() + "." + field.getId() + ".tooltip";
        if (key == null) {
            return "";
        }
        final String message = PresentationManager.getMessage(key);
        if (key.equals(message)) {
            return "";
        }
        return "<img class='tooltip' title='" + message + "' alt='?' src='" + getContextPath() + "/templates/" + getTemplate() + "/img/tooltip.gif' />";
    }

    public static List<Field> displayedFields(Entity entity, String operationId) {
        final List<Field> displayedFields = new ArrayList<Field>();
        for (Field field : entity.getOrderedFields()) {
            final String display = field.getDisplay();
            if (display.contains(operationId) || "all".equalsIgnoreCase(display)) {
                displayedFields.add(field);
            }
        }
        return displayedFields;
    }

    /**
     * Getter for template name
     */
    protected static String getTemplate() {
        return PresentationManager.getPm().getTemplate();
    }

    /**
     * Getter for context path
     */
    protected static String getContextPath() {
        return PMEntitySupport.getInstance().getContext_path();
    }

    /**
     * Prints the objects in the jsp writer of the page context
     */
    protected void print(Object... objects) throws IOException {
        for (Object object : objects) {
            pageContext.getOut().print(object);
        }
    }

    /**
     * Prints the objects in the jsp writer of the page context with a new line
     */
    protected void println(Object... objects) throws IOException {
        for (Object object : objects) {
            pageContext.getOut().println(object);
        }
    }
}
