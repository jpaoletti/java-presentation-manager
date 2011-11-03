package jpaoletti.jpm.test;

import java.util.ArrayList;
import java.util.List;
import jpaoletti.jpm.core.*;

/**
 *
 * @author jpaoletti
 */
public class DataAccessComplexClass2 extends DataAccessTest {

    @Override
    protected void fill() {
        try {
            List<?> childs = PresentationManager.getPm().getEntity("simpleclass").getList(new PMContext(), null);
            list = new ArrayList<Object>();
            int top = random(5, 30);
            for (int i = 0; i < top; i++) {
                ComplexClass2 o = new ComplexClass2();
                o.setId(new Long(i));
                o.setDescription(String.format("Complex Class II %d", i));
                o.setSimpleClass((SimpleClass) childs.get(random(0, childs.size() - 1)));
                o.setSimpleClass2((SimpleClass) childs.get(random(0, childs.size() - 1)));
                o.setSimpleClasses(new ArrayList<SimpleClass>());
                int x = random(1, childs.size() - 2);
                for (int j = x - 1; j < x + 1; j++) {
                    o.getSimpleClasses().add((SimpleClass) childs.get(j));
                }
                list.add(o);
            }
        } catch (PMException ex) {
            PresentationManager.getPm().error(ex);
        }
    }
}
