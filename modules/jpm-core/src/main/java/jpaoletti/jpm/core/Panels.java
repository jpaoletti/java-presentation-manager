package jpaoletti.jpm.core;

import java.util.ArrayList;

/**
 * A group of panel rows.
 *
 * @author jpaoletti
 */
public class Panels {

    private ArrayList<PanelRow> rows;

    public ArrayList<PanelRow> getRows() {
        return rows;
    }

    public void setRows(ArrayList<PanelRow> rows) {
        this.rows = rows;
    }
}
