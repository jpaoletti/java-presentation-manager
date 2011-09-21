package jpaoletti.jpm.hibernate.security;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "sec_perms")
public class SECPermission implements Serializable {

    public static final String LOGIN = "login";
    public static final String USER_ADMIN = "useradmin";
    private static final long serialVersionUID = 1234401492617341582L;
    public static final String SYSCONFIG = null;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;

    public SECPermission() {
        super();
        this.name = "";
    }

    public SECPermission(String name) {
        super();
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SECPermission other = (SECPermission) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
}
