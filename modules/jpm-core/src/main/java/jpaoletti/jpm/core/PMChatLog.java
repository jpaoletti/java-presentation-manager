package jpaoletti.jpm.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author jpaoletti
 */
public class PMChatLog extends Observable {

    private List<String> lines;

    public PMChatLog() {
        this.lines = new ArrayList<String>();
    }

    public void println(final String line){
        lines.add(line);
        notifyObservers();
    }

    public List<String> getLines() {
        return lines;
    }
}
