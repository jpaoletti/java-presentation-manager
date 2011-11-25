package jpaoletti.jpm.core.exporter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Implementation of Exporter for csv files generation
 *
 * @author jpaoletti
 * @since 25/11/2011
 * @version 1.0.1
 *
 */
public class ExporterCSV implements Exporter {

    public static final String BREAK = "\n";
    public static final String SEPARATOR = ";";

    @Override
    public void export(List<List<String>> list, List<String> headers, OutputStream out) throws IOException {
        for (String header : headers) {
            out.write(header.getBytes());
            out.write(SEPARATOR.getBytes());
        }
        out.write(BREAK.getBytes());
        for (List<String> row : list) {
            for (String cell : row) {
                out.write(cell.getBytes());
                out.write(SEPARATOR.getBytes());
            }
            out.write(BREAK.getBytes());
        }
    }

    @Override
    public String getId() {
        return "csv";
    }
}
