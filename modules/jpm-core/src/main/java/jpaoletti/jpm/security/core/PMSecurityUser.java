package jpaoletti.jpm.security.core;

import java.util.ArrayList;
import java.util.List;
import jpaoletti.jpm.core.MD5;

public class PMSecurityUser {

    private String username;
    private String password;
    private String name;
    private List<PMSecurityUserGroup> groups;
    private List<PMSecurityPermission> permissions;
    private boolean deleted;
    private boolean active;
    private String email;
    private boolean changePassword;

    public PMSecurityUser() {
        init();
    }

    private void init() {
        setGroups(new ArrayList<PMSecurityUserGroup>());
        setPermissions(new ArrayList<PMSecurityPermission>());
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the changePassword
     */
    public boolean isChangePassword() {
        return changePassword;
    }

    /**
     * @param changePassword the changePassword to set
     */
    public void setChangePassword(boolean changePassword) {
        this.changePassword = changePassword;
    }

    /**
     * @param groups the groups to set
     */
    public void setGroups(List<PMSecurityUserGroup> groups) {
        this.groups = groups;
    }

    /**
     * @return the groups
     */
    public List<PMSecurityUserGroup> getGroups() {
        return groups;
    }

    public boolean hasPermission(String permission) {
        if (permission == null) {
            return true;
        }
        //First we check personal permissions
        for (PMSecurityPermission p : getPermissions()) {
            if (p.getName().compareTo(permission) == 0) {
                return true;
            }
        }
        //Finally, we check group permissions
        for (PMSecurityUserGroup g : getGroups()) {
            if (g.hasPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if the user belongs to the group
     */
    public boolean belongsTo(String groupname) {
        if (groupname == null) {
            return false;
        }
        for (PMSecurityUserGroup g : getGroups()) {
            if (groupname.equalsIgnoreCase(g.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(List<PMSecurityPermission> permissions) {
        this.permissions = permissions;
    }

    /**
     * @return the permissions
     */
    public List<PMSecurityPermission> getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return name;
    }

    public List<String> getPermissionList() {
        List<String> result = new ArrayList<String>();
        for (PMSecurityPermission perm : getPermissions()) {
            result.add(perm.getName());
        }
        for (PMSecurityUserGroup group : getGroups()) {
            for (PMSecurityPermission perm : group.getPermissions()) {
                if (!result.contains(perm.getName())) {
                    result.add(perm.getName());
                }
            }
        }
        return result;
    }

    /**
     * Get a gravatar image
     */
    public String getGravatar() {
        if (getEmail() == null) {
            return "http://www.gravatar.com/avatar/00000000000000000000000000000000";
        } else {
            final MD5 md5 = new MD5();
            final String hash = md5.calcMD5(getEmail());
            return "http://www.gravatar.com/avatar/" + hash;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof PMSecurityUser)) {
            return false;
        }
        final PMSecurityUser other = (PMSecurityUser) obj;
        if ((this.getUsername() == null) ? (other.getUsername() != null) : !this.getUsername().equals(other.getUsername())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.getUsername() != null ? this.getUsername().hashCode() : 0);
        return hash;
    }
}
