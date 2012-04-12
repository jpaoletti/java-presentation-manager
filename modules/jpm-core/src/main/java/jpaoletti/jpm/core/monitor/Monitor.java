package jpaoletti.jpm.core.monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import jpaoletti.jpm.core.PresentationManager;

/**
 * A monitor that watch something showing his status.
 *
 * @author jpaoletti
 *
 */
public class Monitor extends Observable implements Runnable {

    private Thread thread;
    //The id of the monitor. Must be unique
    private String id;
    //The source of the monitor information
    private MonitorSource source;
    //A formatter for each line generated by monitor.
    private MonitorFormatter formatter;
    // Delay between monitor refreshes in milliseconds
    private Integer delay;
    //Maximum number of lines displayed at a time
    private Integer max;
    //Clean up after each refresh
    private Boolean cleanup;
    // Ignore actual and always get everything
    private Boolean all;
    private PresentationManager pm;

    /**
     * Default constructor
     */
    public Monitor(PresentationManager pm) {
        super();
        this.pm = pm;
    }

    /**
     * Inherited from observable
     *
     * @param o
     */
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        //Interrupts the sleeping Monitor
        if (countObservers() == 1) {
            thread.interrupt();
        }
    }
    private Object actual = null;

    /**
     * Implemented from runnable
     */
    @Override
    public void run() {
        while (true) {
            if (countObservers() == 0) {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                }
            } else {
                startWatching();
                while (countObservers() > 0) {
                    getNewLines();
                    try {
                        Thread.sleep(getDelay());
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    /**
     * Start watching a monitor
     */
    public void startWatching() {
        try {
            MonitorLine line = getSource().getLastLine();
            actual = (line != null) ? line.getId() : null;
            if (line != null) {
                notifyObservers(getFormatter().format(line));
            }
        } catch (Exception e) {
            notifyObservers(e);
        }
    }

    /**
     * Looks for new lines
     */
    public void getNewLines() {
        List<String> result = new ArrayList<String>();
        try {
            List<MonitorLine> lines;
            if (getAll()) {
                lines = getSource().getLinesFrom(null);
            } else {
                lines = getSource().getLinesFrom(actual);
            }
            if (lines.size() > 0) {
                for (MonitorLine line : lines) {
                    result.add(getFormatter().format(line));
                }
                actual = lines.get(lines.size() - 1).getId();
                setChanged();
                notifyObservers(result);
            }
        } catch (Exception e) {
            notifyObservers(e);
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the source
     */
    public MonitorSource getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(MonitorSource source) {
        this.source = source;
    }

    /**
     * @return the formatter
     */
    public MonitorFormatter getFormatter() {
        return formatter;
    }

    /**
     * @param formatter the formatter to set
     */
    public void setFormatter(MonitorFormatter formatter) {
        this.formatter = formatter;
    }

    /**
     * @param delay the delay to set
     */
    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    /**
     * @return the delay
     */
    public Integer getDelay() {
        if (delay == null) {
            return 5000;
        }
        return delay;
    }

    /**
     * @param max the max to set
     */
    public void setMax(Integer max) {
        this.max = max;
    }

    /**
     * @return the max
     */
    public Integer getMax() {
        if (max == null) {
            return 100;
        }
        return max;
    }

    /**
     * @param cleanup the cleanup to set
     */
    public void setCleanup(Boolean cleanup) {
        this.cleanup = cleanup;
    }

    /**
     * @return the cleanup
     */
    public Boolean getCleanup() {
        if (cleanup == null) {
            return false;
        }
        return cleanup;
    }

    /**
     * @param all the all to set
     */
    public void setAll(Boolean all) {
        this.all = all;
    }

    /**
     * @return the all
     */
    public Boolean getAll() {
        if (all == null) {
            return false;
        }
        return all;
    }

    /**
     * @param thread the thread to set
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }

    /**
     * @return the thread
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * Returns the internationalized entity title
     */
    public String getTitle() {
        return pm.message("pm.monitor." + getId());
    }
}
