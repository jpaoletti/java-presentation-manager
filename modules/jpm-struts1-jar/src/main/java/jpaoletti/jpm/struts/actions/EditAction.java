package jpaoletti.jpm.struts.actions;

import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.operations.EditOperation;
import jpaoletti.jpm.struts.PMForwardException;
import jpaoletti.jpm.struts.PMStrutsContext;

public class EditAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        ctx.put("editable", true);
        final boolean finish = ctx.getParameter("finish") == null;
        if (finish) {
            ctx.put("validate", false);
        }

        final EditOperation op = new EditOperation("edit");
        op.execute(ctx);

        if (finish) {
            throw new PMForwardException(CONTINUE);
        }
    }
}
