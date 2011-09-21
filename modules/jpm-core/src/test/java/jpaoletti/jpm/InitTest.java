package jpaoletti.jpm;

import java.io.InputStream;
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
    public void jPMInitialization() {
        final InputStream is = getClass().getResourceAsStream("/jpm-config.xml");
        
        PresentationManager.pm = new PresentationManager();
        PresentationManager.getPm().initialize(null);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
}
