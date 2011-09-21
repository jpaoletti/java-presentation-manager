package jpaoletti.jpm.model;

/**
 *
 * @author jpaoletti
 */
public class WeakClass {
    private ParentClass parent;
    private String description;

    public ParentClass getParent() {
        return parent;
    }

    public void setParent(ParentClass parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
