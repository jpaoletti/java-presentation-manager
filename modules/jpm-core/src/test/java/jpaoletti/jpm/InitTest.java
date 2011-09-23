package jpaoletti.jpm;

import java.util.List;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.Field;
import jpaoletti.jpm.core.Operation;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.model.JPMTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jpaoletti
 */
public class InitTest {

    private List<JPMTest> list;
    private JPMTest item1;

    public InitTest() {
    }

    @Test
    public void jPMInitialization() throws Exception {
        final PresentationManager pm = getPm();

        assertTrue("jPM Initialization process",
                pm.initialize("jpm-config.xml"));
        assertEquals("We must have 1 entity and its asociated",
                2, pm.getEntities().size());
        assertEquals("We must have 1 test location",
                1, pm.getLocations().size());
        assertEquals("We must have 1 external converters test file",
                1, pm.getExternalConverters().size());
        assertEquals("We must have 4 external converters in the converter file",
                4, pm.getExternalConverters().get(0).getConverters().size());
        assertEquals("We must have 4 fields in test entity",
                4, getTestEntity().getAllFields().size());
    }

    @Test
    public void dataAccess() throws Exception {
        list = (List<JPMTest>) getTestEntity().getList(null);
        assertNotNull("List of JPM Test must not be null",
                list);
        assertEquals("We must have 3 test items",
                3, list.size());
        item1 = list.get(0);
        assertEquals("First item must have 1 as id",
                (Long) 1L, item1.getId());
    }

    @Test
    public void genericConverter() throws Exception {
        dataAccess();
        final Field field = getTestEntity().getFieldById("integer");
        assertNotNull("Field 'integer' must exist in test entity",
                field);
        final Operation operation = getTestEntity().getOperations().getOperation("show");
        assertNotNull("Operation 'show' must be defined in test entity",
                operation);
        final PMContext ctx = new PMContext();
        ctx.setEntityInstance(item1);
        final String res = (String) field.visualize(ctx, operation, getTestEntity());
        assertEquals("Converted item must be 'PRE 1 SUF'",
                res, "PRE 1 SUF");
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    protected PresentationManager getPm() throws Exception {
        if (PresentationManager.getPm() == null) {
            PresentationManager.start("jpm-config.xml");
        }
        return PresentationManager.getPm();
    }

    protected Entity getTestEntity() throws Exception {
        return getPm().getEntity("jpmtest");
    }
}
