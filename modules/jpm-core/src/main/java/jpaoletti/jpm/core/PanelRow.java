package jpaoletti.jpm.core;

import java.util.ArrayList;

/**
 * A panel row.
 *
 * @author jpaoletti
 */
public class PanelRow {

    private ArrayList<Panel> panels;

    public ArrayList<Panel> getPanels() {
        return panels;
    }

    public void setPanels(ArrayList<Panel> panels) {
        this.panels = panels;
    }
}
