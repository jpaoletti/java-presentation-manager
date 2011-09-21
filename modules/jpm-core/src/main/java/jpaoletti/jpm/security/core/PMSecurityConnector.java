package jpaoletti.jpm.security.core;

import java.util.List;

import jpaoletti.jpm.core.PMContext;

public interface PMSecurityConnector {

    public void setContext(PMContext ctx);

    public PMSecurityUser authenticate(String username, String password) throws PMSecurityException;

    public void changePassword(String username, String password, String newpassword) throws PMSecurityException;

    public PMSecurityUser getUser(String username) throws PMSecurityException;

    public List<PMSecurityUser> getUsers() throws PMSecurityException;

    public void addUser(PMSecurityUser user) throws PMSecurityException;

    public void updateUser(PMSecurityUser user) throws PMSecurityException;

    public void removeUser(PMSecurityUser object) throws PMSecurityException;

    public PMSecurityUserGroup getGroup(String id) throws PMSecurityException;

    public List<PMSecurityUserGroup> getGroups() throws PMSecurityException;

    public void addGroup(PMSecurityUserGroup group) throws PMSecurityException;

    public void updateGroup(PMSecurityUserGroup group) throws PMSecurityException;

    public void removeGroup(PMSecurityUserGroup group) throws PMSecurityException;

    public List<PMSecurityPermission> getPermissions() throws PMSecurityException;
}
