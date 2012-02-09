package jpaoletti.jpm.struts.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import jpaoletti.jpm.core.EntityContainer;
import jpaoletti.jpm.struts.PMEntitySupport;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 * Navigation list tag
 * 
 * @author jpaoletti
 */
public class NavigationTag extends PMTags {

    private EntityContainer container;

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(getNavigationList(getContainer()));
        } catch (Exception ex) {
            throw new JspTagException("NavigationTag: " + ex.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    public String getNavigationList(final EntityContainer c) {
        final StringBuilder sb = new StringBuilder();
        if (c != null && c.getSelected() != null) {
            final PMStrutsContext ctx = (PMStrutsContext) pageContext.getRequest().getAttribute("ctx");
            final String url = PMTags.url(ctx.getPmsession(), "/" + c.getOperation().getId() + ".do?pmid=" + c.getEntity().getId());
            sb.append(getNavigationList(c.getOwner()));
            sb.append("&nbsp; &gt; &nbsp;");
            sb.append("<a href=\"").append(url).append("\">");
            final Object inst = c.getSelected().getInstance();
            if (inst == null) {
                sb.append("");
            } else {
                sb.append(PMEntitySupport.toHtml(inst.toString()));
            }
            sb.append("</a>");
        }
        return sb.toString();
    }

    public EntityContainer getContainer() {
        return container;
    }

    public void setContainer(EntityContainer container) {
        this.container = container;
    }
}
