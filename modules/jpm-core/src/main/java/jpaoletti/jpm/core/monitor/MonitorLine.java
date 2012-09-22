package jpaoletti.jpm.core.monitor;

/**
 * A monitor line. Its composed by an id to identificate the line and a content.
 *
 * @author jpaoletti
 *
 *
 */
public class MonitorLine {

    private Object id;
    private Object value;

    public MonitorLine(Object id, Object value) {
        this.id = id;
        this.value = value;
    }

    /**
     * setter for the id
     *
     * @param id
     */
    public void setId(Object id) {
        this.id = id;
    }

    /**
     * getter for the id
     *
     * @return The id
     */
    public Object getId() {
        return id;
    }

    /**
     * setter for the value
     *
     * @param value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * getter for the value
     *
     * @return The value
     */
    public Object getValue() {
        return value;
    }
}
