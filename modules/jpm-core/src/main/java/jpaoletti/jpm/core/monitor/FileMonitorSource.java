package jpaoletti.jpm.core.monitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A monitor source that takes information from a file
 *
 * @author jpaoletti
 *
 */
public class FileMonitorSource extends MonitorSource {

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
                result.add(new MonitorLine(currentLineNo, line));
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
    public List<MonitorLine> getLastLine(Integer count) throws Exception {
        String line;
        final List<MonitorLine> result = new ArrayList<MonitorLine>();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(getFilename()));
            int i = 0;
            line = in.readLine();
            while (line != null) {
                result.add(new MonitorLine(i, line));
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
        if (result.size() <= count) {
            return result;
        } else {
            return result.subList(result.size() - count, result.size());
        }
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
