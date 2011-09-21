package jpaoletti.jpm.struts.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import jpaoletti.jpm.core.PMSession;
import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.menu.Menu;
import jpaoletti.jpm.menu.MenuItem;
import jpaoletti.jpm.menu.MenuList;
import jpaoletti.jpm.struts.MenuItemContext;

/**
 * Display the menu
 *
 * @author jpaoletti
 * @since 15/09/2011
 * @version v1.2
 *
 */
public class MenuTag extends PMTags {

    private PMSession pmsession;

    @Override
    public int doStartTag() throws JspException {
        try {

            pageContext.getOut().println("<div id='menu' class='jqueryslidemenu'>");
            pageContext.getOut().println("<ul>");

            if (pmsession != null && pmsession.getMenu() != null) {
                final MenuList list = (MenuList) pmsession.getMenu();
                for (Menu m : list.getSubmenus()) {
                    printMenu(m, pageContext.getOut());
                }
            }
            pageContext.getOut().println("</ul>");
            pageContext.getOut().println("</div>");
        } catch (Exception ex) {
            throw new JspTagException("MessageTag: " + ex.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    protected void printMenu(final Menu m, final JspWriter out) {
        try {
            //Base case
            if (m instanceof MenuItem) {
                final MenuItem item = (MenuItem) m;
                out.print("<li>");
                if (item.getLocation() == null) {
                    out.print("<a href='#'>" + PresentationManager.getMessage(m.getText()) + "</a>");
                } else {
                    final MenuItemContext ctx = (MenuItemContext) item.getLocation().build(item, getContextPath());
                    out.print(ctx.getPrefix());
                    out.print(PresentationManager.getMessage(ctx.getValue()));
                    out.print(ctx.getSufix());
                }
                out.print("</li>");
            } else {
                final MenuList list = (MenuList) m;
                out.print("<li>");
                out.print("<a href='#'>" + PresentationManager.getMessage(m.getText()) + "</a>");
                out.print("<ul>");
                for (Menu sm : list.getSubmenus()) {
                    printMenu(sm, out);
                }
                out.print("</ul>");
                out.print("</li>");
            }
        } catch (Exception e) {
            PresentationManager.getPm().error(e);
        }
    }

    public PMSession getPmsession() {
        return pmsession;
    }

    public void setPmsession(PMSession pmsession) {
        this.pmsession = pmsession;
    }
}
