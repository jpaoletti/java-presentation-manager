package jpaoletti.jpm.menu;

import java.util.ArrayList;
import java.util.List;

/**This class represents an internal node in the menu tree. It contains another menus (list or leaf)
 * 
 * @author jpaoletti
 **/
public class MenuList extends Menu {

    private List<Menu> submenus;

    /**
     * Default constructor with a new empty list of submenus
     */
    public MenuList() {
        submenus = new ArrayList<Menu>();
    }

    /**Add the given menu to the submenu list
     * @param m The submenu to add
     * @return The same added menu.*/
    public Menu add(Menu m) {
        submenus.add(m);
        m.setParent(this);
        return m;
    }

    /**
     * @return the submenus
     */
    public List<Menu> getSubmenus() {
        return submenus;
    }

    /**
     * @param submenus the submenus to set
     */
    public void setSubmenus(List<Menu> submenus) {
        this.submenus = submenus;
    }
}
