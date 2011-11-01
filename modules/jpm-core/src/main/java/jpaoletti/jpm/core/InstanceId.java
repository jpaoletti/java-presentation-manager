package jpaoletti.jpm.core;

/**
 * Entity instance identification. This is a 2-tuple (Integer, Object). If the 
 * Integer is defined, it represent the index of the item based on the actual 
 * list of the entity. If object is defined, it is the value of the 
 * entity.idField value of the instance. 
 * 
 * @author jpaoletti
 */
public class InstanceId {

    private Integer index;
    private Object id;

    public InstanceId(Integer index) {
        if (index == null) {
            throw new IllegalArgumentException();
        }
        this.index = index;
    }

    public InstanceId(Object id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * String representation of this instance id
     */
    public String getValue() {
        if (getIndex() != null) {
            return getIndex().toString();
        } else {
            return getId().toString();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InstanceId other = (InstanceId) obj;
        if (this.getIndex() == null && other.getIndex() != null) {
            return false;
        }
        if (other.getIndex() == null && this.getIndex() != null) {
            return false;
        }
        if (this.getId() == null && other.getId() != null) {
            return false;
        }
        if (other.getId() == null && this.getId() != null) {
            return false;
        }

        if (this.getId() != null && !this.getId().equals(other.getId()) && !this.getId().toString().equals(other.getId().toString())) {
            return false;
        }
        if (this.getIndex() != null && !this.getIndex().equals(other.getIndex()) && !this.getIndex().toString().equals(other.getIndex().toString())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.getIndex() != null ? this.getIndex().hashCode() : 0);
        hash = 19 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }
}
