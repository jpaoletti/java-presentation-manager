package jpaoletti.jpm.ui;

import java.util.List;
import jpaoletti.jpm.core.*;
import jpaoletti.jpm.model.ParentClass;
import jpaoletti.jpm.model.WeakClass;

/**
 *
 * @author jpaoletti
 */
public class DataAccessWeakClass extends DataAccessTest {

    @Override
    protected void fill() {
    }

    @Override
    public List<?> list(PMContext ctx, EntityFilter filter, ListSort sort, Integer from, Integer count) throws PMException {
        ParentClass pc = getParent(ctx);
        List<?> weaklist = pc.getWeaks();
        int f = (from == null) ? 0 : from;
        int c = (int) ((count == null) ? count(ctx) : count);
        System.out.println(String.format("count: %d ; c: %d, f: %d", count(ctx), c, f));
        if (count(ctx) < c) {
            return weaklist;
        }
        if (count(ctx) < f) {
            return null;
        }
        int fpc = f + c;
        return weaklist.subList(f, (int) ((fpc > count(ctx)) ? count(ctx) : fpc));
    }

    @Override
    public Long count(PMContext ctx) throws PMException {
        ParentClass pc = getParent(ctx);
        return new Long(pc.getWeaks().size());
    }

    @Override
    public void add(PMContext ctx, Object instance) throws PMException {
        ParentClass pc = getParent(ctx);
        pc.getWeaks().add((WeakClass) instance);
    }

    @Override
    public void delete(PMContext ctx, Object object) throws PMException {
        ParentClass pc = getParent(ctx);
        pc.getWeaks().remove((WeakClass) object);
    }

    protected ParentClass getParent(PMContext ctx) throws PMException {
        ParentClass pc = (ParentClass) ctx.getEntityContainer().getOwner().getSelected().getInstance();
        return pc;
    }
}
