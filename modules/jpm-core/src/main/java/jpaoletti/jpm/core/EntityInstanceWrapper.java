package jpaoletti.jpm.core;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates a list of instances of an entity. This usually contains
 * only one instances but a list is supported for complex operations with
 * several instances.
 *
 * @author jpaoletti
 */
public class EntityInstanceWrapper {

    private List<Object> instances;

    /**
     * Default constructor with an empty list of instances
     */
    public EntityInstanceWrapper() {
        instances = new ArrayList<Object>();
    }

    /**
     * Constructor with a list of instances including the given object.
     *
     * @param o The object to insert in the instances list
     */
    public EntityInstanceWrapper(Object o) {
        instances = new ArrayList<Object>();
        this.instances.add(o);
    }

    /**
     *
     * @param instance
     */
    public void setInstance(Object instance) {
        if (instances.isEmpty()) {
            instances.add(instance);
        } else {
            this.instances.set(0, instance);
        }
    }

    /**
     * Getter for the first instance
     * @return The instance
     */
    public Object getInstance() {
        return this.instances.get(0);
    }

    /**
     * Return the instance at the given index
     *
     * @param i The index
     * @return The instance
     */
    public Object getInstance(int i) {
        return this.instances.get(i);
    }

    /**
     * Getter for the list of instances
     * @return The instances
     */
    public List<Object> getInstances() {
        return this.instances;
    }

    /**
     * Add a new instance to the list
     * 
     * @param o
     */
    public void add(Object o) {
        instances.add(o);
    }
}
