package jpaoletti.jpm;

import jpaoletti.jpm.security.core.BCrypt;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test for password encryption.
 * 
 * @author jpaoletti
 */
public class BCryptTest {

    public static final String PWD = "This is a password test";

    @Test
    public void encrypt() {
        String h = BCrypt.hashpw(PWD, BCrypt.gensalt(10));
        assertTrue(BCrypt.checkpw(PWD, h));
    }
}
