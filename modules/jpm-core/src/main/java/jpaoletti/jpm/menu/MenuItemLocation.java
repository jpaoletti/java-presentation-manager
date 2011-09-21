package jpaoletti.jpm.menu;

/**
 * This simple interface represents a MenuItem location, that is "where should 
 * this item points in the menu?" This is an abstraction of a location, and must
 * be implemented on each PM implementation.
 *   
 * <pre>
 * {@code
 *         <menu-item text="xxxx" perm="xxxx">
 *             <location id="some_location_id" value="some_value" />
 *             ...
 *         </menu-item>
 * }
 * </pre>
 * 
 * Each implementation of this interface must be defined in a location file with the id and class
 * that implements this interface. The idea is that each location builds its own "pointer" on each
 * menu leaf.
 * 
 * The configuration file for locations has this general form:
 * 
 * <pre>
 * {@code
 * <?xml version='1.0' ?>
 * <!DOCTYPE entity SYSTEM "location.dtd">
 * <locations>
 *     <location id="yyyyy" class="jpaoletti.jpm.yyyy.MenuItemLocationXxxxx"/>
 *     <location id="xxxxx" class="jpaoletti.jpm.xxxx.MenuItemLocationYyyyy"/>
 *     ...
 * </locations>
 * }
 * </pre> 
 * 
 * @author jpaoletti
 * */
public interface MenuItemLocation {

    /**
     * Builds a custom representation of the goal of a menu item
     * @param item The menu item
     * @param params Generic parameters.
     * @return The representation of the link
     */
    public Object build(MenuItem item, Object... params);
}
