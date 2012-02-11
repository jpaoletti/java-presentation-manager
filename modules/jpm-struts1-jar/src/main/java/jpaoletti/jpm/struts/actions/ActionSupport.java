package jpaoletti.jpm.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpaoletti.jpm.core.PMCoreConstants;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.exception.NotAuthenticatedException;
import jpaoletti.jpm.core.exception.NotAuthorizedException;
import jpaoletti.jpm.core.message.MessageFactory;
import jpaoletti.jpm.core.operations.OperationCommandSupport;
import jpaoletti.jpm.struts.PMEntitySupport;
import jpaoletti.jpm.struts.PMForwardException;
import jpaoletti.jpm.struts.PMStrutsConstants;
import jpaoletti.jpm.struts.PMStrutsContext;
import jpaoletti.jpm.struts.tags.PMTags;
import org.apache.struts.action.*;

/**
 * A super class for all actions with some helpers and generic stuff
 *
 * @author jpaoletti
 */
public abstract class ActionSupport extends Action implements PMCoreConstants, PMStrutsConstants {

    protected abstract void doExecute(PMStrutsContext ctx) throws PMException;

    /**
     * Forces execute to check if any user is logged in
     */
    protected boolean checkUser() {
        return true;
    }

    protected boolean prepare(PMStrutsContext ctx) throws PMException {
        if (checkUser() && ctx.getPmsession() == null) {
            //Force logout
            final PMEntitySupport es = PMEntitySupport.getInstance();
            ctx.getSession().invalidate();
            es.setContext_path(ctx.getRequest().getContextPath());
            ctx.getSession().setAttribute(ENTITY_SUPPORT, es);
            ctx.getRequest().setAttribute("reload", 1);
            throw new NotAuthenticatedException();
        }
        return true;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        final PMStrutsContext ctx = (PMStrutsContext) request.getAttribute("ctx");
        ctx.setMapping(mapping);
        ctx.setForm(form);

        final Object pmid = ctx.getParameter("pmid");
        ctx.put(OperationCommandSupport.PM_ID, pmid);
        ctx.getRequest().setAttribute("pmid", pmid);

        final Object item = ctx.getParameter("item");
        ctx.put(OperationCommandSupport.PM_ITEM, item);
        ctx.getRequest().setAttribute("item", item);
        ctx.getPersistenceManager(); // deprecated. Used to back compat

        try {
            boolean step = prepare(ctx);
            if (step) {
                excecute(ctx);
                if (ctx.getOperation() != null && ctx.getOperation().getFollows() != null) {
                    success(ctx, "/" + ctx.getOperation().getFollows() + ".do", true);
                }
            }
            return mapping.findForward(SUCCESS);
        } catch (PMForwardException e) {
            if (e.getActionForward() != null) {
                return e.getActionForward();
            } else {
                return mapping.findForward(e.getKey());
            }
        } catch (NotAuthenticatedException e) {
            return ctx.fwdLogin();
        } catch (NotAuthorizedException e) {
            return ctx.fwdDeny();
        } catch (PMException e) {
            ctx.getPresentationManager().debug(this, e);
            if (e.getKey() != null) {
                ctx.addMessage(MessageFactory.error(e.getKey()));
            }
            return mapping.findForward(FAILURE);
        }
    }

    /**
     * Return the context path of the application
     */
    protected String getContextPath() {
        return PMEntitySupport.getInstance().getContext_path();
    }

    protected void excecute(PMStrutsContext ctx) throws PMException {
        doExecute(ctx);
    }

    /**
     * Consider the operation successful and redirect or forward to the given
     * url
     *
     * @param ctx Context
     * @param url Next url
     * @param redirect If true, redirects, else, forwards
     *
     * @throws PMForwardException always
     */
    protected void success(PMStrutsContext ctx, String url, boolean redirect) throws PMForwardException {
        final String plainUrl = PMTags.plainUrl(ctx.getPmsession(), url).substring(getContextPath().length());
        if (redirect) {
            throw new PMForwardException(new ActionRedirect(plainUrl));
        } else {
            throw new PMForwardException(new ActionForward(plainUrl));
        }
    }

    /**
     * Consider the operation done by the Action and does not forward to any
     * know action. This mus be used in case the Action resolves de output
     * without any need of forward or redirect. url
     *
     * @throws PMForwardException always
     */
    protected void noAction() throws PMForwardException {
        throw new PMForwardException("none");
    }
}
