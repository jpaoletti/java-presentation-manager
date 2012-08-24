package jpaoletti.jpm.struts;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMCoreConstants;
import jpaoletti.jpm.core.PMException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * An extension of the jpaoletti.jpm.core.PMContext class with some helpers for
 * PMStruts.
 */
public class PMStrutsContext extends PMContext implements PMCoreConstants, PMStrutsConstants {

    private HttpServletRequest request;
    private ActionMapping mapping;
    private ActionForm form;
    private HttpServletResponse response;

    public PMStrutsContext(String sessionId) {
        super(sessionId);
    }

    public ActionForm getForm() {
        return form;
    }

    public void setForm(ActionForm form) {
        this.form = form;
    }

    public ActionMapping getMapping() {
        return mapping;
    }

    public void setMapping(ActionMapping mapping) {
        this.mapping = mapping;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    /* ActionForwards Helpers */
    /**
     * Helper for success action forward
     *
     * @return success action forward
     */
    public ActionForward successful() {
        return getMapping().findForward(SUCCESS);
    }

    /**
     * Helper for continue action forward
     *
     * @return continue action forward
     */
    public ActionForward go() {
        return getMapping().findForward(CONTINUE);
    }

    /**
     * Helper for fwdDeny action forward
     *
     * @return fwdDeny action forward
     */
    public ActionForward fwdDeny() {
        return getMapping().findForward("denied");
    }

    /**
     * Helper for login action forward
     *
     * @return fwdDeny action forward
     */
    public ActionForward fwdLogin() {
        return getMapping().findForward("login");
    }

    /**
     * Retrieve the http session
     *
     * @return The session
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * Getter for the entity support helper object
     *
     * @return The entity support
     */
    public PMEntitySupport getEntitySupport() {
        PMEntitySupport r = (PMEntitySupport) getRequest().getSession().getAttribute(ENTITY_SUPPORT);
        return r;
    }

    private String getPmId() {
        return (String) getRequest().getAttribute(PM_ID);
    }

    public String getTmpName() throws PMException {
        return "tmp_" + getEntity().getId() + "_" + getField().getId();
    }

    public List<?> getTmpList() {
        try {
            final List<?> r = (List<?>) getSession().getAttribute(getTmpName());
            return r;
        } catch (PMException ex) {
            getPresentationManager().error(ex);
            return null;
        }
    }
}
