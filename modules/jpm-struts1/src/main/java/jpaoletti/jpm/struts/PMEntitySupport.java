package jpaoletti.jpm.struts;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import jpaoletti.jpm.core.EntitySupport;
import jpaoletti.jpm.core.Field;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMCoreConstants;
import jpaoletti.jpm.core.PMSession;
import jpaoletti.jpm.core.PresentationManager;

/**
 * Helper class for internal use.
 *
 * @author jpaoletti
 * @see EntitySupport
 */
public class PMEntitySupport extends EntitySupport implements PMCoreConstants, PMStrutsConstants {

    private String context_path;
    private static PMEntitySupport instance;
    private HttpServletRequest request;
    public static final Map<String, String> htmlConversions = new HashMap<String, String>();

    /* TODO Externalize this values into a resource */
    static {
        htmlConversions.put("á", "&aacute;");
        htmlConversions.put("é", "&eacute;");
        htmlConversions.put("í", "&iacute;");
        htmlConversions.put("ó", "&oacute;");
        htmlConversions.put("ú", "&uacute;");
        htmlConversions.put("Á", "&Aacute;");
        htmlConversions.put("É", "&Eacute;");
        htmlConversions.put("Í", "&Iacute;");
        htmlConversions.put("Ó", "&Oacute;");
        htmlConversions.put("Ú", "&Uacute;");
        htmlConversions.put("ñ", "&ntilde;");
        htmlConversions.put("Ñ", "&Ntilde;");
        htmlConversions.put("º", "&ordm;");
        htmlConversions.put("ª", "&ordf;");
        htmlConversions.put("ü", "&uuml;");
        htmlConversions.put("Ü", "&Uuml;");
        htmlConversions.put("ç", "&ccedil;");
        htmlConversions.put("Ç", "&Ccedil;");
    }

    /**
     * Singleton getter
     *
     * @return The PMEntitySupport
     */
    public synchronized static PMEntitySupport getInstance() {
        if (instance == null) {
            instance = new PMEntitySupport();
        }
        return instance;
    }

    public PMSession getPMSession() throws PMStrutsException {
        if (request == null) {
            throw new PMStrutsException("request.not.found");
        }
        return (PMSession) request.getSession().getAttribute(PMSESSION);
    }

    /**
     * Setter for context path
     *
     * @param context_path The context_path
     */
    public void setContext_path(String context_path) {
        this.context_path = context_path;
    }

    /**
     * Getter for context path
     *
     * @return The context_path
     */
    public String getContext_path() {
        return context_path;
    }

    @Deprecated
    public HttpServletRequest getRequest() {
        return request;
    }

    @Deprecated
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getWelcomePage() {
        return PresentationManager.getPm().getCfg().getProperty("welcome-page", "pages/welcome.jsp");
    }

    @Deprecated
    public PMContext prepareForConversion(Field field, Object item, Object field_value) {
        final PMContext ctx = (PMContext) request.getAttribute(PM_CONTEXT);
        ctx.setField(field);
        if (field_value != null) {
            ctx.setFieldValue(field_value);
        } else {
            ctx.setFieldValue(ctx.getPresentationManager().get(item, field.getProperty()));
        }
        ctx.setEntityInstance(item);
        request.setAttribute("ctx", ctx);
        return ctx;
    }

    public static String toHtml(final String s) {
        if (s == null) {
            return null;
        }
        if ("true".equalsIgnoreCase(PresentationManager.getPm().getCfg().getProperty("html-convert", "true"))) {
            String tmp = s;
            for (Map.Entry<String, String> entry : htmlConversions.entrySet()) {
                tmp = tmp.replace(entry.getKey(), entry.getValue());
            }
            return tmp;
        } else {
            return s;
        }
    }

    /**
     * Getter for PMSession from http session
     */
    public static PMSession getPMSession(final HttpServletRequest request) {
        return (PMSession) request.getSession().getAttribute(PMSESSION);
    }

    /**
     * @return The actual template path
     */
    public String getTemplatePath() {
        return getContext_path() + "/templates/" + PresentationManager.getPm().getTemplate() + "/";
    }

    public String getCssMode() {
        return getPm().getCfg().getProperty("css", "css");
    }
}
