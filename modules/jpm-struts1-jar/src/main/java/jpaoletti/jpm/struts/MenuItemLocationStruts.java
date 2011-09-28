package jpaoletti.jpm.struts;

import jpaoletti.jpm.menu.MenuItem;
import jpaoletti.jpm.menu.MenuItemLocation;

/**
 * Location for internal links that uses loadPage javascript function
 *
 * @author jpaoletti
 */
public class MenuItemLocationStruts implements MenuItemLocation {

    @Override
    public Object build(MenuItem item, Object... params) {
        MenuItemContext context = new MenuItemContext();
        StringBuilder sb = new StringBuilder("<a href=");
        String link = buildLink(item, params);
        sb.append("javascript:loadPage('");
        sb.append(link);
        sb.append("')");
        sb.append(">");
        context.setPrefix(sb.toString());
        context.setValue(item.getText());
        context.setSufix("</a>");
        return context;
    }

    protected String buildLink(MenuItem item, Object... params) {
        return (String) params[0] + item.getLocationValue();
    }
}
