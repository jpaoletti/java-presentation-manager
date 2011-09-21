package jpaoletti.jpm.struts.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import jpaoletti.jpm.core.PresentationManager;

/**
 * Display an internationalized message
 *
 * @author jpaoletti
 * @since 15/09/2011
 * @version v1.2
 *
 */
public class MessageTag extends PMTags {

    private String key;
    private String arg0;
    private String arg1;
    private String arg2;
    private String arg3;
    private String arg4;

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(PresentationManager.getMessage(
                    getKey(),
                    PresentationManager.getMessage(getArg0()),
                    PresentationManager.getMessage(getArg1()),
                    PresentationManager.getMessage(getArg2()),
                    PresentationManager.getMessage(getArg3()),
                    PresentationManager.getMessage(getArg4())));
        } catch (Exception ex) {
            throw new JspTagException("MessageTag: " + ex.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    public String getArg0() {
        return arg0;
    }

    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getArg3() {
        return arg3;
    }

    public void setArg3(String arg3) {
        this.arg3 = arg3;
    }

    public String getArg4() {
        return arg4;
    }

    public void setArg4(String arg4) {
        this.arg4 = arg4;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
