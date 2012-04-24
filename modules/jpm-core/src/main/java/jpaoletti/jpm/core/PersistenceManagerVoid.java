package jpaoletti.jpm.core;

/**
 * This class replace the old service property "ignoreDB". Just use this class
 * in "persistence-manager"
 *
 * @author jpaoletti
 *
 */
public class PersistenceManagerVoid implements PersistenceManager<Object> {

    /**
     * Inherited. Just do nothing
     *
     * @param ctx
     * @param transaction
     * @throws Exception
     */
    @Override
    public void commit(PMContext ctx, Object transaction) throws Exception {
    }

    /**
     * Inherited. Just do nothing
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void finish(PMContext ctx) throws Exception {
    }

    /**
     * Inherited. Just do nothing
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void init(Object connection) throws Exception {
    }

    /**
     * Inherited. Just do nothing
     *
     * @param ctx
     * @param transaction
     * @throws Exception
     */
    @Override
    public void rollback(PMContext ctx, Object transaction) throws Exception {
    }

    /**
     * Inherited. Just do nothing
     *
     * @param ctx
     * @return
     * @throws Exception
     */
    @Override
    public Object startTransaction(PMContext ctx) throws Exception {
        return null;
    }

    @Override
    public Object getConnection() {
        return null;
    }

    @Override
    public Object newConnection() {
        return null;
    }
}
