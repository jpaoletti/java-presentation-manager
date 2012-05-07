package jpaoletti.jpm.struts;

import jpaoletti.jpm.menu.MenuItem;
import jpaoletti.jpm.menu.MenuItemLocation;

/**
 * Just a menu separator.
 *
 * @author jpaoletti
 */
public class MenuItemLocationSeparator implements MenuItemLocation {

    @Override
    public Object build(MenuItem item, Object... params) {
        final MenuItemContext context = new MenuItemContext();
        context.setPrefix("");
        context.setValue("<div class='divider'></div>");
        context.setSufix("");
        return context;
    }
}
