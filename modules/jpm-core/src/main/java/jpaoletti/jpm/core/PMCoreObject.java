package jpaoletti.jpm.core;

/**
 * This is the superclass of all the core objects of Presentation Manager and it provides some
 * helpers.
 * @author jpaoletti
 *
 */
public abstract class PMCoreObject implements PMCoreConstants {

    private Boolean debug;

    /**
     * Display a debug information on PM log if debug flag is active
     *
     * @param s String information
     */
    public void debug(String s) {
        if (getDebug()) {
            PresentationManager.pm.debug(this, s);
        }
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    /**
     * @return the debug
     */
    public Boolean getDebug() {
        if (debug == null) {
            return false;
        }
        return debug;
    }

    /**
     * Return the presentation manager singleton
     *
     * @return The Presentation Manager
     */
    protected PresentationManager getPresentationManager() {
        return PresentationManager.getPm();
    }
}
