package jpaoletti.jpm.ui;

import java.util.ArrayList;
import jpaoletti.jpm.model.ParentClass;
import jpaoletti.jpm.model.WeakClass;

/**
 *
 * @author jpaoletti
 */
public class DataAccessParentClass extends DataAccessTest {

    @Override
    protected void fill() {
        list = new ArrayList<Object>();
        int top = random(5, 30);
        for (int i = 0; i < top; i++) {
            ParentClass o = new ParentClass();
            o.setId(new Long(i));
            o.setDescription(String.format("Parent Class %d", i));
            o.setWeaks(new ArrayList<WeakClass>());
            int x = random(1, 10);
            for (int j = 0; j < x + 1; j++) {
                WeakClass weak = new WeakClass();
                weak.setParent(o);
                weak.setDescription("Weak " + j + " of " + o.getId());
                o.getWeaks().add(weak);
            }
            list.add(o);
        }

    }
}
