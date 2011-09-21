package jpaoletti.jpm.menu;

import jpaoletti.jpm.core.PMCoreObject;

/**Abstract class that represents a Menu in a generic form. The basic idea is that we can have a Menu
 * with submenus or a leaf menu, making this a recursive estructure. Any menu has a descriptive text, 
 * a parent menu (except for the root element) and a permission that indicates when a user can access
 * to this item. 
 * 
 * A menu is fully configured in a xml configuration file and it basic structure is something like:
 * 
 * <pre>
 * {@code
 * <?xml version='1.0' ?>
 * <!DOCTYPE entity SYSTEM "menu.dtd">
 * <menu>
 *     <menu-list text="xxxx" perm="xxxx">
 *             <menu-item text="xxxx" perm="xxxx">
 *             ...
 *             </menu-item>
 *     </menu-list>
 *     <menu-list>
 *     ...
 *     </menu-list>
 * </menu>
 * }
 * </pre>
 * @author jpaoletti
 * */
public abstract class Menu extends PMCoreObject{
    /**Descriptive text*/
    private  String text;
    /**Parent in menu tree. Only null in the root element*/
    private  MenuList parent;
    /**Permission needed to access to this item. It also hides any child */
    private  String permission;
    
    /**
     * @param parent the parent to set
     */
    public void setParent(MenuList parent) {
        this.parent = parent;
    }
    /**
     * @return the parent
     */
    public MenuList getParent() {
        return parent;
    }
    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
    /**
     * @return the text
     */
    public String getText() {
        return text;
    }
    /**
     * @param permission the permission to set
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }
    /**
     * @return the permission
     */
    public String getPermission() {
        return permission;
    }
}