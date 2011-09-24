package jpaoletti.jpm.security.core;

import java.util.Collections;
import java.util.List;

/**
 * This security connector acceps any user as ok
 * 
 * @author jpaoletti
 */
public class PMVoidSecurityConnector extends PMSecurityAbstractConnector {

    @Override
    public PMSecurityUser authenticate(String username, String password) throws PMSecurityException {
        return getUser(username);
    }

    @Override
    public PMSecurityUser getUser(String username) throws PMSecurityException {
        final PMSecurityUser user = new PMSecurityUser();
        user.setUsername(username);
        user.setName(username);
        user.setActive(true);
        user.setDeleted(false);
        user.setChangePassword(false);
        return user;
    }

    @Override
    public List<PMSecurityUser> getUsers() throws PMSecurityException {
        return Collections.EMPTY_LIST;
    }

    @Override
    public void addUser(PMSecurityUser user) throws PMSecurityException {
    }

    @Override
    public void updateUser(PMSecurityUser user) throws PMSecurityException {
    }

    @Override
    public PMSecurityUserGroup getGroup(String id) throws PMSecurityException {
        return null;
    }

    @Override
    public List<PMSecurityUserGroup> getGroups() throws PMSecurityException {
        return Collections.EMPTY_LIST;
    }

    @Override
    public void addGroup(PMSecurityUserGroup group) throws PMSecurityException {
    }

    @Override
    public void updateGroup(PMSecurityUserGroup group) throws PMSecurityException {
    }

    @Override
    public void removeGroup(PMSecurityUserGroup group) throws PMSecurityException {
    }

    @Override
    public List<PMSecurityPermission> getPermissions() throws PMSecurityException {
        return Collections.EMPTY_LIST;
    }
}
