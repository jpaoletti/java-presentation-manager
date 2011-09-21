package jpaoletti.jpm.security.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PMSecurityUserGroup implements Serializable {

    private static final long serialVersionUID = 8383915466636478529L;
    private String id;
    private String name;
    private String description;
    private boolean active;
    private List<PMSecurityPermission> permissions;

    public PMSecurityUserGroup() {
        this.permissions = new ArrayList<PMSecurityPermission>();
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PMSecurityUserGroup other = (PMSecurityUserGroup) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    public PMSecurityPermission getPermission(String name) {
        for (PMSecurityPermission p : getPermissions()) {
            if (name != null && p != null && name.compareTo(p.getName()) == 0) {
                return p;
            }
        }
        return null;
    }

    public boolean hasPermission(String permName) {
        return getPermission(permName) != null;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
}
