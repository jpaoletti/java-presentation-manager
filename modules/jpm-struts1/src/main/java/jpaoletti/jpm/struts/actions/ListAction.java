package jpaoletti.jpm.struts.actions;

import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.operations.ListOperation;
import jpaoletti.jpm.struts.PMStrutsContext;

public class ListAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        ctx.put("order", ctx.getParameter("order"));
        ctx.put("desc", ctx.getParameter("desc") != null && ctx.getParameter("order").equals("true"));
        ctx.put("page", (ctx.getParameter("page") == null) ? null : Integer.parseInt((String) ctx.getParameter("page")));
        ctx.put("rowsPerPage", (ctx.getParameter("rowsPerPage") == null) ? null : Integer.parseInt((String) ctx.getParameter("rowsPerPage")));
        ListOperation op = new ListOperation("list");
        op.execute(ctx);
    }
}
