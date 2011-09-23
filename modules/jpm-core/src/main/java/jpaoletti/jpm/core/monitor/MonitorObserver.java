package jpaoletti.jpm.core.monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import jpaoletti.jpm.core.PresentationManager;

/** This class is an observer of the monitor.
 * 
 * @author jpaoletti 
 * 
 *
 */
public class MonitorObserver implements Observer {

    private Monitor monitor;
    private List<String> lines;

    /**
     *
     * @param monitor
     */
    public MonitorObserver(Monitor monitor) {
        super();
        this.monitor = monitor;
        monitor.addObserver(this);
        this.lines = new ArrayList<String>();
    }

    /**
     * Inherited from Observer
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            lines.add((String) arg);
        }
        if (arg instanceof List<?>) {
            lines.addAll((List<String>) arg);
        }
        if (arg instanceof Exception) {
            Exception e = (Exception) arg;
            PresentationManager.getPm().error(e);
            lines.add(e.getMessage());
        }
    }

    /**
     * Setter for monitor
     * @param monitor
     */
    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    /**
     * Getter for monitor
     * @return The monitor
     */
    public Monitor getMonitor() {
        return monitor;
    }

    /**
     * @param lines the lines to set
     */
    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    /**
     * @return the lines and clear them for next time
     */
    public List<String> getLines() {
        List<String> res = new ArrayList<String>();
        res.addAll(lines);
        lines.clear();
        return res;
    }
}
