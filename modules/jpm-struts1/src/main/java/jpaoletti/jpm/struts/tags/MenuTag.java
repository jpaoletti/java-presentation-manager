package jpaoletti.jpm.struts.tags;

import java.util.Calendar;
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
            final JspWriter out = pageContext.getOut();
            out.println("<div id='menu'>");
            out.println("<ul>");

            if (pmsession != null && pmsession.getMenu() != null) {
                final MenuList list = (MenuList) pmsession.getMenu();
                for (Menu m : list.getSubmenus()) {
                    printMenu(m, out);
                }
            }
            out.println("</ul>");
            out.println("<div class='menu-button-bar'>");
            out.println("<a href='index.jsp' title=" + PresentationManager.getMessage("home") + "><div class='home'></div></a>");
            final String contact = PresentationManager.getPm().getContact();
            if (contact != null) {
                out.println("<a href='mailto:" + contact + "' title=" + PresentationManager.getMessage("contact") + "><div class='contact'></div></a>");
            }
            final String copyright = PresentationManager.getPm().getCopyright();
            if (copyright != null) {
                out.println("<div class='copyright' title='" + PresentationManager.getMessage("header.copyright", copyright, Calendar.getInstance().get(Calendar.YEAR)) + "'>&copy;</div>");
            }
            if (PresentationManager.getPm().isHideableHeader()) {
                out.println("<a href='#' id='btnColapseExpand' title=" + PresentationManager.getMessage("header.expand") + "><div class='expand'></div></a>");
            }
            if (pmsession != null) {
                out.println("<a href='" + PMTags.plainUrl(pmsession, "logout.do") + "' title=" + PresentationManager.getMessage("logout") + "><div class='logout'></div></a>");
            }
            out.println("<div class='version'>v" + PresentationManager.getPm().getAppversion() + "</div>");
            out.println("</div>");
            out.println("</div>");
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
                    final MenuItemContext ctx = (MenuItemContext) item.getLocation().build(item, getContextPath(), getPmsession());
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
