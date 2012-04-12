package jpaoletti.jpm.struts;

import jpaoletti.jpm.core.PMSession;
import jpaoletti.jpm.menu.MenuItem;
import jpaoletti.jpm.menu.MenuItemLocation;
import jpaoletti.jpm.struts.tags.PMTags;

/**
 * Location for internal links that uses loadPage javascript function
 *
 * @author jpaoletti
 */
public class MenuItemLocationStruts implements MenuItemLocation {

    @Override
    public Object build(MenuItem item, Object... params) {
        final MenuItemContext context = new MenuItemContext();
        final StringBuilder sb = new StringBuilder("<a href=\"");
        final PMSession session = (PMSession) params[1];
        final String link = buildLink(item, params);
        sb.append(PMTags.url(session, link));
        sb.append("\">");
        context.setPrefix(sb.toString());
        context.setValue(item.getText());
        context.setSufix("</a>");
        return context;
    }

    protected String buildLink(MenuItem item, Object... params) {
        return item.getLocationValue();
    }
}
