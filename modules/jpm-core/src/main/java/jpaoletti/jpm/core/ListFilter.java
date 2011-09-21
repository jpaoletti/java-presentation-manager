package jpaoletti.jpm.core;

/**
 * An interface that filters the data shown by an entity. Each data access has
 * his own creation of this interface.
 *
 * @author jpaoletti
 * */
public interface ListFilter {

    /**
     * Returns an specific implementation of a filter given by and understood by
     * each data access.
     * 
     * @param ctx The context
     * @return the filter object
     */
    public Object getListFilter(PMContext ctx);
}
