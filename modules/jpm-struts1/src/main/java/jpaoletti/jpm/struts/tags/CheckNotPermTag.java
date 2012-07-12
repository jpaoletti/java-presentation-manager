package jpaoletti.jpm.struts.tags;

import javax.servlet.jsp.JspException;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 *
 * @author jpaoletti
 */
public class CheckNotPermTag extends PMTags {

    private String perm;

    @Override
    public int doStartTag() throws JspException {
        final PMStrutsContext ctx = (PMStrutsContext) pageContext.getRequest().getAttribute("ctx");
        if ("".equals(perm) || (ctx.getUser() != null && !ctx.getUser().hasPermission(getPerm()))) {
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }
}
