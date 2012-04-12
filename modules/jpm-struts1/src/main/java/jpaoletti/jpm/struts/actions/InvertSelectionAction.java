package jpaoletti.jpm.struts.actions;

import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.operations.InvertSelection;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 *
 * @author jpaoletti
 */
public class InvertSelectionAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        final InvertSelection op = new InvertSelection();
        op.execute(ctx);
        success(ctx, "/list.do", true);
    }
}
