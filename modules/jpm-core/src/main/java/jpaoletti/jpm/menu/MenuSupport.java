package jpaoletti.jpm.menu;

import java.util.List;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.PresentationManager;

/**
 * A helper class to get the associated menu of a user. It builds the full menu
 * and makes a rebuild without the options the user has not permission to see. 
 * 
 * @author jpaoletti
 * */
public class MenuSupport {

    /**Builds the menu for the user.
     * @param permissions List of permissions
     * @return The filtered menu associated to the permissions of the user.
     * @throws PMException
     * */
    public static Menu getMenu(List<String> permissions) throws PMException {
        try {
            MenuBuilder mb = new MenuBuilder(PresentationManager.getPm().getMenu());
            Menu menu = cleanWithoutPerms(mb.getMenu(), permissions);
            return menu;
        } catch (Exception e) {
            throw new PMException("pm_core.cant.load.menu");
        }
    }

    private static Menu cleanWithoutPerms(Menu menu, List<String> permissions) {
        if (menu.getPermission() == null || menu.getPermission().trim().compareTo("") == 0 || permissions.contains(menu.getPermission())) {
            if (menu instanceof MenuItem) {
                return menu;
            } else {
                MenuList ml = new MenuList();
                ml.setText(menu.getText());
                ml.setPermission(menu.getPermission());
                ml.setParent(menu.getParent());
                for (Menu m : ((MenuList) menu).getSubmenus()) {
                    Menu m2 = cleanWithoutPerms(m, permissions);
                    if (m2 != null) {
                        ml.add(m2);
                    }
                }
                return ml;
            }
        } else {
            return null;
        }
    }
}
