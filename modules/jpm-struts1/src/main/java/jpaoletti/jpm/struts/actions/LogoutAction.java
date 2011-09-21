package jpaoletti.jpm.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import jpaoletti.jpm.core.PMSession;
import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.struts.PMEntitySupport;
import jpaoletti.jpm.struts.PMStrutsConstants;

public class LogoutAction extends Action implements PMStrutsConstants {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        final PMEntitySupport es = PMEntitySupport.getInstance();
        final PMSession pmsession = PMEntitySupport.getPMSession(request);
        if (pmsession != null) {
            PresentationManager.getPm().removeSession(pmsession.getId());
        }
        request.getSession().invalidate();
        es.setContext_path(request.getContextPath());
        request.getSession().setAttribute(ENTITY_SUPPORT, es);
        return mapping.findForward(ActionSupport.SUCCESS);
    }
}
