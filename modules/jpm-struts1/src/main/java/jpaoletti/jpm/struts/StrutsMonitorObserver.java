package jpaoletti.jpm.struts;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import jpaoletti.jpm.core.monitor.Monitor;
import jpaoletti.jpm.core.monitor.MonitorObserver;

/**
 * This class is an observer of the monitor specific for Web implementations. As
 * a Web interface cannot be called, then we don't know when to stop observing.
 * So this observer implements a timer. If the getLines method is not called
 * since 3 times the delay monitor parameter, we asume that this observer is no
 * longer needed and delete from observers list.
 *
 * @author jpaoletti
 *
 *
 */
public class StrutsMonitorObserver extends MonitorObserver {

    private Timer timer;

    /**
     * Constructor
     *
     * @param monitor The monitor to observe
     */
    public StrutsMonitorObserver(Monitor monitor) {
        super(monitor);
        schedule();
    }

    private void schedule() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                getMonitor().deleteObserver(self());
            }
        };
        timer.schedule(task, getMonitor().getDelay() * 3);
    }

    private StrutsMonitorObserver self() {
        return this;
    }

    @Override
    public synchronized List<String> getLines() {
        timer.cancel();
        timer.purge();
        schedule();
        List<String> lines = super.getLines();
        return lines;
    }
}
