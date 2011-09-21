package jpaoletti.jpm.hibernate.security;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "sec_groups")
public class SECUserGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String name;
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creation;
    @Type(type = "yes_no")
    private boolean active;
    @ManyToMany
    @JoinTable(name = "sec_group_perms", joinColumns =
    @JoinColumn(name = "sec_group"), inverseJoinColumns =
    @JoinColumn(name = "sec_perm"))
    private Set<SECPermission> permissions;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SECUserGroup other = (SECUserGroup) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    public SECUserGroup() {
        super();
        permissions = new LinkedHashSet<SECPermission>();
    }

    public SECPermission getPermission(String name) {
        for (SECPermission p : getPermissions()) {
            if (name != null && p != null && name.compareTo(p.getName()) == 0) {
                return p;
            }
        }
        return null;
    }

    public boolean hasPermission(String permName) {
        return getPermission(permName) != null;
    }

    public void grant(SECPermission perm) {
        getPermissions().add(perm);
    }

    public void revoke(String perm) {
        getPermissions().remove(getPermission(perm));
    }

    public void revokeAll() {
        getPermissions().clear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Set<SECPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<SECPermission> permissions) {
        this.permissions = permissions;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
