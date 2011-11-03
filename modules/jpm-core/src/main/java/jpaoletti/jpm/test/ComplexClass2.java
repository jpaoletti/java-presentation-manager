package jpaoletti.jpm.test;

import java.util.List;

/**
 *
 * @author jpaoletti
 */
public class ComplexClass2 extends SimpleClass {

    private SimpleClass simpleClass;
    private SimpleClass simpleClass2;
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

    public SimpleClass getSimpleClass2() {
        return simpleClass2;
    }

    public void setSimpleClass2(SimpleClass simpleClass2) {
        this.simpleClass2 = simpleClass2;
    }
}
