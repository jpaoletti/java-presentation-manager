package jpaoletti.jpm.core.operations;

import jpaoletti.jpm.core.*;

/**
 *
 * @author jpaoletti
 */
public class ListOperation extends OperationCommandSupport {

    protected static final String LIST_CONTENTS = "listContents";

    public ListOperation(String operationId) {
        super(operationId);
    }

    public ListOperation() {
        super("list");
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        super.doExecute(ctx);
        final ListManager listManager = new ListManager();
        final Operations operations = (Operations) ctx.get(OPERATIONS);

        PaginatedList pmlist = ctx.getList();
        if (pmlist == null) {
            pmlist = listManager.initList(ctx, operations);
        }

        configureOrder(ctx, pmlist);
        final Integer page = (Integer) ctx.get("page");
        if (page != null) {
            pmlist.setPage(page);
        }
        final Integer rpp = (Integer) ctx.get("rowsPerPage");
        if (rpp != null) {
            pmlist.setRowsPerPage(rpp);
        }
        listManager.configureList(ctx, pmlist, operations);
        ctx.put(LIST_CONTENTS, ctx.getEntityContainer().getList().getContents());
        //Optimization, with identified entities we don't need to keep the list in memory
        if (ctx.getEntity().isIdentified()) {
            ctx.getEntityContainer().getList().setContents(null);
        }
    }

    public void configureOrder(PMContext ctx, PaginatedList pmlist) {
        final String o = ctx.getString("order");
        if (o != null) {
            pmlist.getSort().setFieldId(o);
        } else {
            if (!pmlist.getSort().isSorted()) {
                pmlist.getSort().setFieldId(ctx.getEntity().getOrderedFields().get(0).getId());
            }
        }
        final Object d = ctx.getParameter("desc");
        if (d != null) {
            if ("true".equals(d)) {
                pmlist.getSort().setDirection(ListSort.SortDirection.DESC);
            } else {
                pmlist.getSort().setDirection(ListSort.SortDirection.ASC);
            }
        }
    }
}
