package jpaoletti.jpm.struts;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpaoletti.jpm.core.PMCoreConstants;
import jpaoletti.jpm.core.PMSession;
import jpaoletti.jpm.struts.actions.ActionSupport;

/**
 * This servlet decrypt *.jpm urls based on session encripter and dispatch them
 *
 * @author jpaoletti
 * @since 11/01/2012
 * @version 1.2.0
 *
 */
public class EncryptedURLServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String sp = req.getServletPath();
        final String enc = sp.substring(1, sp.length() - 4);
        final PMSession pmsession = PMEntitySupport.getPMSession(req);
        if (pmsession == null) {
            resp.sendRedirect(req.getContextPath());
        } else {
            final PMStrutsContext ctx = (PMStrutsContext) req.getAttribute(PMCoreConstants.PM_CONTEXT);
            final String url = pmsession.getStringEncrypter().decrypt(enc);
            if (url.contains("?")) {
                final String _parameters = url.substring(url.indexOf("?") + 1);
                final String[] parameters = _parameters.split("&");
                for (String _parameter : parameters) {
                    final String[] parameter = _parameter.split("=");
                    ctx.put("param_" + parameter[0], getParameterValue(ctx, parameter[0], (parameter.length == 2) ? parameter[1] : ""));
                }
            }
            try {
                ctx.put(ActionSupport.ACTION_NAME, url.substring(0, url.indexOf(".do")));
            } catch (Exception e) {
            }
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private Object getParameterValue(PMStrutsContext ctx, String key, String value) {
        final Object actualValue = ctx.getParameter(key);
        //If there is no previous value, new value is returned
        if (actualValue == null) {
            return value;
        }
        //If there is a parameter with this key, an array must be returned 
        //containing all the values.
        if (actualValue instanceof String) {
            final String[] result = {(String) actualValue, value};
            return result;
        }
        final String[] actualValues = (String[]) actualValue;
        final String[] result = new String[actualValues.length + 1];
        System.arraycopy(actualValues, 0, result, 0, actualValues.length);
        result[actualValues.length] = value;
        return result;
    }
}
