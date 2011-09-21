package jpaoletti.jpm.util;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Resource helper
 * 
 * @author jpaoletti
 */
public class ResourceManager {

    public static InputStream getInputStream(final String filename) throws FileNotFoundException {
        final InputStream is = ResourceManager.class.getClassLoader().getResourceAsStream(filename);
        if (is == null) {
            throw new FileNotFoundException(filename);
        }
        return is;
    }
}
