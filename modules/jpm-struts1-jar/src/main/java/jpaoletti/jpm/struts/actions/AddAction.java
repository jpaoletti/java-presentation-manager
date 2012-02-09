package jpaoletti.jpm.struts.actions;

import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.operations.AddOperation;
import jpaoletti.jpm.struts.PMForwardException;
import jpaoletti.jpm.struts.PMStrutsContext;

public class AddAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        ctx.put("editable", true);
        final boolean finish = ctx.getParameter("finish") != null;
        if (!finish) {
            ctx.put("clean_selected", true);
            ctx.put("validate", false);
        }

        final AddOperation op = new AddOperation("add");
        op.execute(ctx);

        if (finish) {
            success(ctx, "/list.do", true);
        } else {
            throw new PMForwardException(CONTINUE);
        }
    }
}
