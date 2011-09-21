package jpaoletti.jpm.model;

import java.util.List;

/**
 *
 * @author jpaoletti
 */
public class ComplexClass2 extends SimpleClass{
    private SimpleClass simpleClass;
    private List<SimpleClass> simpleClasses;

    public SimpleClass getSimpleClass() {
        return simpleClass;
    }

    public void setSimpleClass(SimpleClass simpleClass) {
        this.simpleClass = simpleClass;
    }

    public List<SimpleClass> getSimpleClasses() {
        return simpleClasses;
    }

    public void setSimpleClasses(List<SimpleClass> simpleClasses) {
        this.simpleClasses = simpleClasses;
    }
}
