package jpaoletti.jpm.core.monitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jpaoletti.jpm.core.PersistenceManager;

/**
 * A monitor source that takes information from a file
 *
 * @author jpaoletti
 *
 */
public class FileMonitorSource extends MonitorSource {

    public FileMonitorSource(PersistenceManager persistenceManager) {
        super(persistenceManager);
    }
    private String filename;

    /**
     * Get the file lines since the actual until the last.
     *
     * @param actual Actual line identification
     * @return The list of lines
     * @throws Exception
     */
    @Override
    public List<MonitorLine> getLinesFrom(Object actual) throws Exception {
        //TODO Enhance line retrieve to get last lines directly
        String line;
        Integer currentLineNo = 0;
        final List<MonitorLine> result = new ArrayList<MonitorLine>();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(getFilename()));

            Integer startLine = (actual == null) ? 0 : (Integer) actual;
            //read to startLine
            while (currentLineNo < startLine + 1) {
                if (in.readLine() == null) {
                    throw new IOException("File too small");
                }
                currentLineNo++;
            }
            //read until endLine
            line = in.readLine();
            while (line != null) {
                MonitorLine l = new MonitorLine();
                l.setId(currentLineNo);
                l.setValue(line);
                result.add(l);
                currentLineNo++;
                line = in.readLine();
            }
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ignore) {
            }
        }
        return result;
    }

    /**
     * Return the last file line
     *
     * @return The line
     * @throws Exception
     */
    @Override
    public MonitorLine getLastLine() throws Exception {
        String line = null;
        MonitorLine result = new MonitorLine();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(getFilename()));
            int i = 0;
            line = in.readLine();
            while (line != null) {
                result.setId(i);
                result.setValue(line);
                i++;
                line = in.readLine();
            }
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ignore) {
            }
        }
        return result;
    }

    /**
     * Retrieve the filename
     */
    @Override
    public void init() {
        setFilename(getConfig("filename"));
    }

    /**
     *
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     *
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }
}
