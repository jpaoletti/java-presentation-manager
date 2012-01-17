package jpaoletti.jpm;

import jpaoletti.jpm.util.StringEncrypter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jpaoletti
 */
public class StringEncryptTest {

    public StringEncryptTest() {
    }

    @Test
    public void encondeDecode() throws Exception {
        final StringEncrypter enc = new StringEncrypter();
        final String url = "this is a string to encrypt and decrypt";
        final String eurl = enc.encrypt(url);
        System.out.println(eurl);
        final String durl = enc.decrypt(eurl);
        System.out.println(durl);
        assertEquals(url, durl);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
}
