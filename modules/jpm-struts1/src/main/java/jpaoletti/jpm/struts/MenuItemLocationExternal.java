package jpaoletti.jpm.struts;

import jpaoletti.jpm.menu.MenuItem;
import jpaoletti.jpm.menu.MenuItemLocation;

/**
 * Location representing external links.
 * 
 * @author jpaoletti
 */
public class MenuItemLocationExternal implements MenuItemLocation {

    @Override
    public Object build(MenuItem item, Object... params) {
        MenuItemContext context = new MenuItemContext();
        StringBuilder sb = new StringBuilder("<a target='_blank' href=");
        sb.append("'");
        sb.append(buildLink(item, params));
        sb.append("'");
        sb.append(">");
        context.setPrefix(sb.toString());
        context.setValue(item.getText());
        context.setSufix("</a>");
        return context;
    }

    /**
     * Builds an external link
     * @param item The menu item
     * @param params Extra parameters
     * @return The string with the external link
     */
    protected String buildLink(MenuItem item, Object... params) {
        return item.getLocationValue();
    }
}
