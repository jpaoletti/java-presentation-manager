package jpaoletti.jpm;

import jpaoletti.jpm.core.PresentationManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jpaoletti
 */
public class InitTest {

    public InitTest() {
    }

    @Test
    public void jPMInitialization() throws Exception {
        PresentationManager.pm = new PresentationManager();
        final PresentationManager pm = PresentationManager.getPm();
        assertTrue("jPM Initialization process",pm.initialize("jpm-config.xml"));
        assertEquals("We have 1 entity and its asociated",
                2, pm.getEntities().size());
        assertEquals("We have 1 test location",
                1, pm.getLocations().size());
        assertEquals("We have 1 external converters test file",
                1, pm.getExternalConverters().size());
        assertEquals("We have 4 external converters in the converter file",
                4, pm.getExternalConverters().get(0).getConverters().size());
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
}
