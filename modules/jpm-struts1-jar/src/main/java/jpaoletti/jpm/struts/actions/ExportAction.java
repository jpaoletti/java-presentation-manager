package jpaoletti.jpm.struts.actions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.operations.Export;
import jpaoletti.jpm.struts.PMForwardException;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 * Struts action for Export operation
 *
 * @author jpaoletti
 * @since 25/11/2011
 * @version 1.0.1
 *
 */
public class ExportAction extends ActionSupport {

    @Override
    protected void doExecute(PMStrutsContext ctx) throws PMException {
        try {
            final boolean finish = ctx.getParameter("finish") == null;
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            ctx.put("out", out);
            final Export op = new Export();
            op.execute(ctx);
            if (finish) {
                throw new PMForwardException(CONTINUE);
            }
            ctx.getResponse().setContentType("text/csv");
            ctx.getResponse().addHeader("Content-Disposition", "attachment;filename=export.csv");
            ctx.getResponse().getOutputStream().write(out.toByteArray());
            ctx.getResponse().getOutputStream().close();
            out.close();
            noAction();
        } catch (IOException ex) {
            throw new PMException(ex);
        }
    }
}
