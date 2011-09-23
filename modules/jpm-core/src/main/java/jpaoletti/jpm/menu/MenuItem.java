package jpaoletti.jpm.menu;

import jpaoletti.jpm.core.PresentationManager;

/**
 * This class represents the leafs of the menu tree. 
 *
 * @author jpaoletti
 */
public class MenuItem extends Menu{
    
    /**Indicates the location of the destiny. 
     * @see MenuItemLocation */
    private MenuItemLocation location;

    /**Indicates the location value of the destiny. 
     * @see MenuItemLocation */
    private String location_value;

    /**
     * Default constructor with name
     * @param text The text of the menu item
     */
    public MenuItem(String text) {
        setText(text);
    }

    /**
     * @param location the location to set
     */
    public void setLocation(MenuItemLocation location) {
        this.location = location;
    }

    /**
     * @return the location
     */
    public MenuItemLocation getLocation() {
        return location;
    }

    /**
     * @param location_value the location_value to set
     */
    public void setLocation_value(String location_value) {
        this.location_value = location_value;
    }

    /**
     * @return the location_value
     */
    public String getLocation_value() {
        return location_value;
    }

    /**Recover from the service the location object and set it and the value to
     * this item.
     * @param location The id to look into the conficuration file pm.locations.xml
     * @param value The location value*/
    public void parseLocation(String location, String value) {
        setLocation_value(value);
        setLocation(PresentationManager.getPm().getLocation(location));
    }    
}