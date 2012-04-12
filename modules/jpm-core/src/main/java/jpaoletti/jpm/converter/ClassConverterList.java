package jpaoletti.jpm.converter;

import java.util.ArrayList;
import java.util.List;
import jpaoletti.jpm.core.PresentationManager;

/**
 * A wrapper for a list of classConverter
 *
 * @author jpaoletti
 */
public class ClassConverterList {

    private List<ClassConverter> classConverters;
    private PresentationManager pm;

    public ClassConverterList(PresentationManager pm) {
        this.classConverters = new ArrayList<ClassConverter>();
        this.pm = pm;
    }

    public PresentationManager getPm() {
        return pm;
    }

    public List<ClassConverter> getClassConverters() {
        return classConverters;
    }

    public void setClassConverters(List<ClassConverter> classConverters) {
        this.classConverters = classConverters;
    }

    /**
     * Set PM to each item of the list
     */
    public void spreadPm() {
        for (ClassConverter classConverter : classConverters) {
            classConverter.setPm(getPm());
        }
    }

    public Converter getConverter(String operation, String className) {
        for (ClassConverter classConverter : classConverters) {
            if (classConverter.getOperations().contains(operation)
                    && classConverter.getClassName().equals(className)) {
                return getPm().getExternalConverter(classConverter.getEconverter());
            }
        }
        return null;
    }
}
