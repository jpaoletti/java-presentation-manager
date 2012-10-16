package jpaoletti.jpm.struts.tags;

import javax.servlet.jsp.JspException;
import jpaoletti.jpm.core.Field;
import jpaoletti.jpm.core.Operation;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 * Tag that execute the body only if the field is displayed on the operation
 *
 * @author jpaoletti
 */
public class DisplaysTag extends PMTags {

    private Field field;
    private Operation operation;
    private String operationId;

    @Override
    public int doStartTag() throws JspException {
        final PMStrutsContext ctx = (PMStrutsContext) pageContext.getRequest().getAttribute("ctx");
        if (getField().shouldDisplay(getOperationId(), ctx.getUser())) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    public String getOperationId() {
        if (getOperation() != null) {
            return getOperation().getId();
        }
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
