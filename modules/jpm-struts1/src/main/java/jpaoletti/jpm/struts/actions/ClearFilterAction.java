package jpaoletti.jpm.struts.actions;

import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.operations.ClearFilterOperation;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 *
 * @author jpaoletti
 */
public class ClearFilterAction extends ActionSupport{

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        (new ClearFilterOperation("clearfilter")).execute(ctx);
    }

}
