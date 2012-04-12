package jpaoletti.jpm.converter;

import jpaoletti.jpm.core.PresentationManager;

/**
 * A class converter is a vinculation of an external converter and a java class
 * so that the default converter for the class is the given external converter.
 *
 * @author jpaoletti
 */
public class ClassConverter {

    private PresentationManager pm;
    private String econverter;
    private String className;
    private String operations;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEconverter() {
        return econverter;
    }

    public void setEconverter(String econverter) {
        this.econverter = econverter;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public PresentationManager getPm() {
        return pm;
    }

    void setPm(PresentationManager pm) {
        this.pm = pm;
    }
}
