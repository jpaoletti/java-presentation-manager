package jpaoletti.jpm;

import java.io.InputStream;
import java.util.Properties;
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
        final InputStream is = getClass().getResourceAsStream("/jpm-config.xml");
        final Properties properties = new Properties();
        properties.loadFromXML(is);
        PresentationManager.pm = new PresentationManager();
        final PresentationManager pm = PresentationManager.getPm();
        assertTrue(pm.initialize(properties));
        assertEquals(2, pm.getEntities().size());
        assertEquals(1, pm.getLocations().size());
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
}
