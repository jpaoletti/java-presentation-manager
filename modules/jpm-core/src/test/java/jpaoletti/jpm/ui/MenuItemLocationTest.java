package jpaoletti.jpm.ui;

import jpaoletti.jpm.menu.MenuItem;
import jpaoletti.jpm.menu.MenuItemLocation;

/**
 * MenuItemLocation test class
 *
 * @author jpaoletti
 * @since 21/09/2011
 * @version 1.0
 *
 */
public class MenuItemLocationTest implements MenuItemLocation{

    @Override
    public Object build(MenuItem item, Object... params) {
        return ".";
    }

}
