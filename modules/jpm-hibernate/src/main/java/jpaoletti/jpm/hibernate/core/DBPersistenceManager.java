package jpaoletti.jpm.hibernate.core;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.PersistenceManager;
import jpaoletti.jpm.core.PresentationManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DBPersistenceManager implements PersistenceManager<Session> {

    private Session session;

    @Override
    public void commit(PMContext ctx, Object transaction) throws Exception {
        ((Transaction) transaction).commit();
    }

    @Override
    public void finish(PMContext ctx) {
        try {
            getConnection().close();
        } catch (Exception e) {
        }
    }

    @Override
    public void init(Session o) throws Exception {
        try {
            this.session = o;
        } catch (Exception e) {
            PresentationManager.getPm().error(e);
            throw new PMException(e);
        }
    }

    @Override
    public void rollback(PMContext ctx, Object transaction) throws Exception {
        ((Transaction) transaction).rollback();
        getConnection().close();
        init(getConnection());
        //TODO Need some checks
    }

    @Override
    public Object startTransaction(PMContext ctx) throws Exception {
        return getConnection().beginTransaction();
    }

    @Override
    public Session getConnection() {
        return session;
    }
}