package jpaoletti.jpm.struts;

import jpaoletti.jpm.menu.MenuItem;

/**
 * Location for internal links that uses loadPage javascript function. This 
 * version take the entity id and make a link  to a list operation
 *
 * @author jpaoletti
 */
public class MenuItemLocationList extends MenuItemLocationStruts {

    @Override
    protected String buildLink(MenuItem item, Object... params) {
        return "list.do?pmid=" + item.getLocationValue();
    }
}
