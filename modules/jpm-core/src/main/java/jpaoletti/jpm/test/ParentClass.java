package jpaoletti.jpm.test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpaoletti
 */
public class ParentClass extends SimpleClass {
    private List<WeakClass> weaks;

    public List<WeakClass> getWeaks() {
        if(weaks==null) weaks=new ArrayList<WeakClass>();
        return weaks;
    }

    public void setWeaks(List<WeakClass> weaks) {
        this.weaks = weaks;
    }


    @Override
    public String toString() {
        return "ParentClass{" + "id=" + getId() + '}';
    }
    
}
