package jpaoletti.jpm.hibernate;

import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PersistenceManager;
import jpaoletti.jpm.core.PresentationManager;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jpaoletti
 */
public class HibernateTest {

    private Session session;

    public HibernateTest() {
    }

    @Test
    public void connection() throws Exception {
        final String sid = "1";
        PresentationManager.getPm().registerSession(sid);
        final Entity entity = getTestEntity();
        final PMContext ctx = new PMContext(sid);
        ctx.setEntityContainer(ctx.getEntityContainer("test1"));
        final PersistenceManager mgr = ctx.getPersistenceManager();
        mgr.init(getSession());

        assertEquals("There must be 2 items in test1 table",
                2, entity.getList(ctx).size());
        assertNotNull("Row with id 1 must exist",
                entity.getDataAccess().getItem(ctx, "id", "1"));
        assertNotNull("Row with description T1 must exist",
                entity.getDataAccess().getItem(ctx, "description", "T1"));

        final Object tx = mgr.startTransaction(null);
        try {
            mgr.commit(null, tx);
        } catch (Exception e) {
            mgr.rollback(null, tx);
        }

        mgr.finish(null);
    }

    @Before
    public void setUp() {
        session = HibernateUtil.getSession();
    }

    @After
    public void tearDown() {
        if (session.isOpen()) {
            session.close();
        }
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        PresentationManager.start("jpm-config.xml");
    }

    public Session getSession() {
        return session;
    }

    private Entity getTestEntity() {
        return PresentationManager.getPm().getEntity("test1");
    }
}
