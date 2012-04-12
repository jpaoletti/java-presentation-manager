package jpaoletti.jpm.struts.actions;

import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.operations.SelectAll;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 *
 * @author jpaoletti
 */
public class SelectAllAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        final SelectAll op = new SelectAll();
        op.execute(ctx);
        success(ctx, "/list.do", true);
    }
}
