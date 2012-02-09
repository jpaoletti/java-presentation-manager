package jpaoletti.jpm.struts.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.jsp.tagext.TagSupport;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.EntityInstanceWrapper;
import jpaoletti.jpm.core.Field;
import jpaoletti.jpm.core.Highlight;
import jpaoletti.jpm.core.InstanceId;
import jpaoletti.jpm.core.Operation;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.PMSession;
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

    public static boolean contains(List list, Object o) {
        return list != null && list.contains(o);
    }

    /**
     * Builds a jpm url based on given url
     * 
     * @param session Session user. Cannot be null
     * @param url Url to be built, <b>without</b> context path
     * 
     * @return ready to use url
     */
    public static String url(PMSession session, String url) {
        return url(session, url, false);
    }

    /**
     * Builds a jpm url based on given url
     * 
     * @param session Session user. Cannot be null
     * @param url Url to be built, <b>without</b> context path
     * @param confirm if confirm is true, uses loadPageConfirm instead of 
     * loadPage
     * 
     * @return ready to use url
     */
    public static String url(PMSession session, String url, boolean confirm) {
        return url(session, url, confirm, "javascript:");
    }

    /**
     * Builds a jpm url based on given url
     * 
     * @param session Session user. Cannot be null
     * @param url Url to be built, <b>without</b> context path
     * @param confirm if confirm is true, uses loadPageConfirm instead of 
     * loadPage
     * 
     * @return ready to use url
     */
    public static String url(PMSession session, String url, Boolean confirm, String prefix) {
        final String contextPath = PMEntitySupport.getInstance().getContext_path();
        final StringBuilder sb = new StringBuilder();
        if (prefix != null) {
            sb.append(prefix);
        }
        if (confirm) {
            sb.append("loadPageConfirm('");
        } else {
            sb.append("loadPage('");
        }
        sb.append(contextPath);
        sb.append("/");
        sb.append(session.getStringEncrypter().encrypt(
                (url.startsWith("/")) ? url.substring(1) : url));
        sb.append(".jpm')");
        return sb.toString();
    }

    public static String plainUrl(PMSession session, String url) {
        final String contextPath = PMEntitySupport.getInstance().getContext_path();
        return contextPath + "/"
                + session.getStringEncrypter().encrypt((url.startsWith("/")) ? url.substring(1) : url)
                + ".jpm";
    }

    public static String itemCheckbox(PMStrutsContext ctx, DisplacedList list, Object item) throws PMException {
        if (ctx.getEntityContainer().getList().isHasSelectedScope()) {
            final StringBuilder input = new StringBuilder();
            final InstanceId id = ctx.getDataAccess().getInstanceId(ctx, new EntityInstanceWrapper(item));
            final String idValue = id.getValue();
            input.append("<input class='ui-list-icon' type='checkbox' ");
            input.append("onchange='selectItem(").append(idValue).append(");'");
            if (ctx.getEntityContainer().isSelected(id)) {
                input.append("checked");
            }
            input.append("/>");
            return input.toString();
        } else {
            return "";
        }
    }

    public static String listItemOperations(PMStrutsContext ctx, DisplacedList list, Object item) {
        try {
            final StringBuilder sb = new StringBuilder();
            for (Operation itemOperation : ctx.getOperations(item, ctx.getOperation()).getOperations()) {
                //If we have permission
                if (ctx.getPmsession().getUser().hasPermission(itemOperation.getPerm())) {
                    //if operation is at item scope
                    if (OperationScope.ITEM.is(itemOperation.getScope())) {
                        String furl = "";
                        if (itemOperation.getUrl() != null) {
                            furl = itemOperation.getUrl();
                        } else {
                            final InstanceId id = ctx.getDataAccess().getInstanceId(ctx, new EntityInstanceWrapper(item));
                            final String idValue = id.getValue();
                            furl = itemOperation.getId() + ".do?pmid=" + ctx.getEntity().getId() + "&item=" + idValue;
                        }
                        sb.append("<a class='ui-list-icon-container' class=''href=\"");
                        sb.append(url(ctx.getPmsession(), furl, itemOperation.getConfirm(), "javascript:"));
                        sb.append("\" id='operation");
                        sb.append(itemOperation.getId());
                        sb.append("' title='");
                        sb.append(PresentationManager.getMessage("operation." + itemOperation.getId()));
                        sb.append("'><div class='ui-list-icon ui-icon ui-icon-operation-").append(itemOperation.getId()).append("'>&nbsp;</div></a>");
                    }
                }
            }
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

    public static String rowNumber(PaginatedList pmlist, DisplacedList list, Object item) {
        if (pmlist.isShowRowNumber()) {
            return String.format("<span class='ui-list-icon'>[%0" + pmlist.getListTotalDigits() + "d]&nbsp;</span>", list.indexOf(item));
        } else {
            return "";
        }
    }

    /**
     * This method show a tooltip if the key is defined
     * @param key Key
     */
    public static String tooltip(Field field) {
        final String tooltip = field.getTooltip();
        if (tooltip == null) {
            return "";
        }
        return "<img class='tooltip' title='" + tooltip + "' alt='?' src='"
                + getContextPath() + "/templates/"
                + getTemplate() + "/img/tooltip.gif' />";
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

    public static Field getField(Entity entity, String fieldId) {
        return entity.getFieldById(fieldId);
    }
}
