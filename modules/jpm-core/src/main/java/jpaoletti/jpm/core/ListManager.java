package jpaoletti.jpm.core;

import java.util.List;
import jpaoletti.jpm.core.operations.OperationScope;
import jpaoletti.jpm.util.DisplacedList;

/**
 *
 * @author jpaoletti
 */
public class ListManager {

    public PaginatedList initList(PMContext ctx, Operations operations) throws PMException {
        final PaginatedList pmlist = new PaginatedList();
        //Initial values
        final String _listfilter = ctx.getOperation().getConfig("listfilter");
        if (_listfilter != null) {
            try {
                pmlist.setListFilter((ListFilter) PresentationManager.getPm().newInstance(_listfilter));
            } catch (Exception e) {
                PresentationManager.getPm().error("Unable to load list filter: " + _listfilter);
                throw new PMException("cant.load.list.filter");
            }
        }
        pmlist.setSort(new ListSort(null, ListSort.SortDirection.ASC));
        pmlist.setPage(1);
        pmlist.setRowsPerPage(Integer.parseInt(ctx.getOperation().getConfig("rows-per-page", "10")));

        pmlist.setEntity(ctx.getEntity());
        pmlist.setOperations(operations.getOperationsForScope(OperationScope.GENERAL, OperationScope.SELECTED));
        String sortfield = ctx.getOperation().getConfig("sort-field");
        String sortdirection = ctx.getOperation().getConfig("sort-direction");
        if (sortfield != null) {
            pmlist.getSort().setFieldId(sortfield);
            if (sortdirection != null && sortdirection.toLowerCase().compareTo("desc") == 0) {
                pmlist.getSort().setDirection(ListSort.SortDirection.DESC);
            }
        }
        return pmlist;
    }

    public void configureList(final PMContext ctx, final PaginatedList pmlist, Operations operations) throws PMException {
        List<Object> contents = null;
        Long total = null;
        ctx.getEntityContainer().setList(pmlist);
        try {
            if (isPaginable(ctx)) {
                contents = (List<Object>) ctx.getEntity().getDataAccess().list(ctx,
                        ctx.getEntityContainer().getFilter(),
                        ctx.getEntityContainer().getList().getListFilter(),
                        pmlist.getSort(), pmlist.from(), pmlist.rpp());
            } else {
                contents = (List<Object>) ctx.getEntity().getDataAccess().list(ctx,
                        ctx.getEntityContainer().getFilter(),
                        ctx.getEntityContainer().getList().getListFilter(),
                        pmlist.getSort(), null, null);
            }
            if (!ctx.getEntity().getNoCount()) {
                total = ctx.getEntity().getDataAccess().count(ctx);
            }
        } catch (Exception e) {
            ctx.getEntityContainer().setList(null);
            ctx.getPresentationManager().error(e);
            throw new PMException("pm.operation.cant.load.list");
        }
        ctx.getPresentationManager().debug(this, "List Contents: " + contents);
        pmlist.setContents(new DisplacedList<Object>(contents));
        pmlist.setTotal(total);
        ctx.getPresentationManager().debug(this, "Resulting list: " + pmlist);
        pmlist.setRowsPerPage(pmlist.rpp());
        prepareParameters(ctx, operations);
    }

    public boolean isPaginable(PMContext ctx) {
        return ctx.getOperation().getConfig("paginable", "true").compareTo("true") == 0;
    }

    private void prepareParameters(PMContext ctx, Operations operations) throws PMException {
        boolean searchable = ctx.getOperation().getConfig("searchable", "true").compareTo("true") == 0;
        Boolean showRowNumber = ctx.getOperation().getConfig("show-row-number", "false").compareTo("true") == 0;
        String operationColWidth = ctx.getOperation().getConfig("operation-column-width", "50px");

        ctx.getList().setSearchable(searchable);
        ctx.getList().setPaginable(isPaginable(ctx));
        ctx.getList().setShowRowNumber(showRowNumber);
        ctx.getList().setOperationColWidth(operationColWidth);
        ctx.getList().setHasSelectedScope(operations.getOperationsForScope(OperationScope.SELECTED).count() > 0);
    }
}
