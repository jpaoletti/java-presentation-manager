package jpaoletti.jpm.struts.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import jpaoletti.jpm.core.EntityContainer;
import jpaoletti.jpm.core.NavigationList;
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
        final StringBuilder sb = new StringBuilder("<ul class=\"breadcrumb\">");
        final PMStrutsContext ctx = (PMStrutsContext) pageContext.getRequest().getAttribute("ctx");
        for (int i = 0; i < ctx.getPmsession().getNavigationList().size(); i++) {
            final NavigationList.NavigationListItem item = ctx.getPmsession().getNavigationList().get(i);
            if (i == ctx.getPmsession().getNavigationList().size() - 1) {
                sb.append("<li class='active'>");
                sb.append(item.getTitle());
            } else {
                sb.append("<li>");
                final String url = PMTags.url(ctx.getPmsession(),
                        "/" + item.getOperation().getId() + ".do?"
                        + "pmid=" + item.getEntityContainer().getId()
                        + ((item.getSelectedId() != null) ? "&item=" + item.getSelectedId().getValue() : ""));
                sb.append("<a href=\"").append(url).append("\">");
                sb.append(PMEntitySupport.toHtml(item.getTitle()));
                sb.append("</a><span class='divider'>/</span>");
            }
            sb.append("</li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }

    public EntityContainer getContainer() {
        return container;
    }

    public void setContainer(EntityContainer container) {
        this.container = container;
    }
}
