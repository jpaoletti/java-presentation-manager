package jpaoletti.jpm.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * This is a list with an index displacement. That means that any index received or
 * sent in functions has a displacement. For example if displacement is 10 then
 * get(10) will return 0 item, get(11) will return 1 item and so on.
 *
 * @param <T>
 */
public class DisplacedList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 455698140739954729L;
    private Integer displacement;

    private void init() {
        displacement = null;
    }

    /**
     *  Inherited from ArrayList
     * @param index
     * @param element
     */
    @Override
    public void add(int index, T element) {
        if (displacement != null) {
            super.add(index - displacement, element);
        } else {
            super.add(index, element);
        }
    }

    /**
     * Inherited from ArrayList
     * @param o
     * @return
     */
    @Override
    public int lastIndexOf(Object o) {
        if (displacement != null) {
            return super.lastIndexOf(o) + displacement;
        }
        return super.lastIndexOf(o);
    }

    /**
     * Inherited from ArrayList
     * @param index
     * @param element
     * @return
     */
    @Override
    public T set(int index, T element) {
        if (displacement != null) {
            return super.set(index - displacement, element);
        }
        return super.set(index, element);
    }

    /**
     * Inherited from ArrayList
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if (displacement != null) {
            return super.get(index - displacement);
        }
        return super.get(index);
    }

    /**
     * Inherited from ArrayList
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {
        if (displacement != null) {
            return super.indexOf(o) + displacement;
        }
        return super.indexOf(o);
    }

    /**
     * Inherited from ArrayList
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        if (displacement != null) {
            return super.get(index - displacement);
        }
        return super.remove(index);
    }

    /**
     * Default constructor
     */
    public DisplacedList() {
        super();
        init();
    }

    /**
     * Inherited from ArrayList
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new DisplacedIterator<T>(this);
    }

    /**
     * Constructor with an initial displacement
     * @param displacement
     */
    public DisplacedList(Integer displacement) {
        super();
        this.displacement = displacement;
    }

    /**
     * Inherited from ArrayList
     * @param c
     */
    public DisplacedList(Collection<? extends T> c) {
        super(c);
        init();
    }

    /**
     *
     * @param initialCapacity
     */
    public DisplacedList(int initialCapacity) {
        super(initialCapacity);
        init();
    }

    /**
     * getter for displacement
     * 
     * @return
     */
    public Integer getDisplacement() {
        return displacement;
    }

    /**
     * Setter for displacement
     * 
     * @param displacement
     */
    public void setDisplacement(Integer displacement) {
        this.displacement = displacement;
    }
}
