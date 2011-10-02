package org.jpos.ee.jpm;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PersistenceManager;
import org.hibernate.Transaction;
import org.jpos.ee.DB;

/**
 *
 * @author jpaoletti
 */
public class JPOSPersistenceManager implements PersistenceManager<DB> {

    private DB db;

    @Override
    public DB getConnection() {
        return db;
    }

    @Override
    public void init(DB db) throws Exception {
        this.db = db;
    }

    @Override
    public void finish(PMContext ctx) throws Exception {
        db.close();
    }

    @Override
    public Object startTransaction(PMContext ctx) throws Exception {
        return getConnection().beginTransaction();
    }

    @Override
    public void commit(PMContext ctx, Object transaction) throws Exception {
        ((Transaction) transaction).commit();
    }

    @Override
    public void rollback(PMContext ctx, Object transaction) throws Exception {
        ((Transaction) transaction).rollback();
    }
}
