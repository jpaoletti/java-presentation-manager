package jpaoletti.jpm.core;

/**
 * This interface encapsulate a transaction behaviour
 *
 * @author jpaoletti
 */
public interface PersistenceManager<T> {

    /**
     * Create a new connection
     */
    public T newConnection();

    public T getConnection();

    /**
     * Initialize persistance
     *
     * @param ctx The context
     * @throws Exception
     */
    public void init(T o) throws Exception;

    /**
     * Finalize persistance
     *
     * @param ctx The context
     * @throws Exception
     */
    public void finish(PMContext ctx) throws Exception;

    /**
     * Starts a transaction
     *
     * @param ctx The context
     * @return Transaction object
     * @throws Exception
     */
    public Object startTransaction(PMContext ctx) throws Exception;

    /**
     * Commit the given transaction
     *
     * @param ctx The context
     * @param transaction The transaction
     * @throws Exception
     */
    public void commit(PMContext ctx, Object transaction) throws Exception;

    /**
     * Rollback the given transaction
     *
     * @param ctx The context
     * @param transaction The transaction
     * @throws Exception
     */
    public void rollback(PMContext ctx, Object transaction) throws Exception;
}
