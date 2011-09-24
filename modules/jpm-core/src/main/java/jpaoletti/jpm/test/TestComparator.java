package jpaoletti.jpm.test;

import java.util.Comparator;
import jpaoletti.jpm.core.PresentationManager;

/**
 *
 * @author jpaoletti
 */
public class TestComparator  implements Comparator{

    private String field;
    private Boolean asc;

    public TestComparator(String field, Boolean asc) {
        this.field = field;
        this.asc = asc;
    }

    @Override
    public int compare(Object t, Object t1) {
        Comparable o = (Comparable) PresentationManager.getPm().get(t, field);
        Comparable o1 = (Comparable)PresentationManager.getPm().get(t1, field);
        if(o==null && o1==null) return 0;
        if(o==null && asc) return -1;
        if(o==null && !asc) return 1;
        if(o1==null && asc) return 1;
        if(o1==null && !asc) return -1;
        if(asc) return o.compareTo(o1);
        else return o1.compareTo(o);
    }

    public Boolean getAsc() {
        return asc;
    }

    public void setAsc(Boolean asc) {
        this.asc = asc;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}
