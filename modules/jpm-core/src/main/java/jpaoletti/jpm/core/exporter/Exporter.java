package jpaoletti.jpm.core.exporter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * An exporter is a way to generate an output from a data access list;
 *
 * @author jpaoletti
 * @since 25/11/2011
 * @version 1.0.1
 *
 */
public interface Exporter {
    public String getId();

    public void export(
            final List<List<String>> list,
            final List<String> headers,
            final OutputStream out) throws IOException;
}
