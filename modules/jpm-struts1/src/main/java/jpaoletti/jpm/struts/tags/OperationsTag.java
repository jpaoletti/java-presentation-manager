package jpaoletti.jpm.struts.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.Operation;
import jpaoletti.jpm.core.Operations;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMSession;
import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.core.operations.OperationCommandSupport;
import jpaoletti.jpm.struts.PMStrutsConstants;

/**
 * Display an html div bar with the operations
 *
 * @author jpaoletti
 * @since 15/09/2011
 * @version v1.2
 *
 */
public class OperationsTag extends PMTags {

    private boolean labels = true;
    private PMContext ctx;
    private Operations operations;

    @Override
    public int doStartTag() throws JspException {
        try {
            if (getOperations() != null && getOperations().getOperations() != null && !getOperations().getOperations().isEmpty()) {
                pageContext.getOut().println("<div id='operation_bar'>");
                for (Operation operation : getOperations().getOperations()) {
                    if (getPmsession().getUser().hasPermission(operation.getPerm())) {
                        processOperation(pageContext.getOut(), operation);
                    }
                }
                pageContext.getOut().println("</div>");
            } else {
                pageContext.getOut().print("");
            }
        } catch (Exception ex) {
            throw new JspTagException("OperationsTag: " + ex.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    public Operations getOperations() {
        if (operations != null) {
            return operations;
        } else {
            return (Operations) ctx.get(PMStrutsConstants.OPERATIONS);
        }
    }

    public PMSession getPmsession() {
        return ctx.getPmsession();
    }

    private void processOperation(JspWriter out, Operation operation) throws IOException {
        final String opid = operation.getId();

        final String onclick = (operation.getConfirm())
                ? "onclick=\"return confirm('" + PresentationManager.getMessage("pm.operation.confirm.question", "operation." + opid, "pm.entity." + getEntity().getId()) + "');\""
                : "";
        final String style = "background-image:url(" + getContextPath() + "/templates/" + getTemplate() + "/img/" + opid + ".gif);";
        final String item = getCtx().getString(OperationCommandSupport.PM_ITEM);
        final String hreff = (operation.getUrl() != null)
                ? operation.getUrl()
                : getContextPath() + "/" + opid + ".do"
                + "?pmid=" + getEntity().getId()
                + ((item != null) ? "&item=" + item : "");

        out.print("<a href='" + hreff + "' class='button' style=" + style + " id='operation" + opid + "' " + onclick + ">&nbsp;");
        if (isLabels()) {
            out.print(PresentationManager.getMessage("operation." + opid, "pm.entity." + getEntity().getId()));
        }
        out.println("</a>");
    }

    public boolean isLabels() {
        return labels;
    }

    public void setLabels(boolean labels) {
        this.labels = labels;
    }

    public Entity getEntity() {
        return ctx.getEntity();
    }

    public PMContext getCtx() {
        return ctx;
    }

    public void setCtx(PMContext ctx) {
        this.ctx = ctx;
    }

    public void setOperations(Operations operations) {
        this.operations = operations;
    }
}
