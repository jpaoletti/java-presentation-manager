package jpaoletti.jpm.test;

import java.math.BigDecimal;
import java.util.*;
import jpaoletti.jpm.core.*;

/**
 * Test data access.
 * 
 * @author jpaoletti
 */
public class DataAccessTest extends AbstractDataAccess {

    protected List<Object> list;

    protected void fill(PMContext ctx) {
        final String clazz = ctx.getEntity().getClazz();
        list = new ArrayList<Object>();
        if (clazz.equals(SimpleClass.class.getName())) {
            int top = random(5, 30);
            for (int i = 0; i < top; i++) {
                SimpleClass o = new SimpleClass();
                o.setId(new Long(i));
                o.setDescription(String.format("Simple Class %d", i));
                list.add(o);
            }
        } else if (clazz.equals(ComplexClass1.class.getName())) {
            int top = random(5, 30);
            for (int i = 0; i < top; i++) {
                ComplexClass1 o = new ComplexClass1();
                o.setId(new Long(i));
                o.setDescription(String.format("Complex Class I %d", i));
                o.setActive((i % 2 == 0) ? Boolean.TRUE : Boolean.FALSE);
                o.setAmount(new BigDecimal(Math.random()));
                o.setDate(new Date());
                o.setDatetime(new Date());
                o.setKey("pm.test.key." + i);
                o.setPassword("password");
                o.setSize(new Long(i * 2000));
                list.add(o);
            }
        } else if (clazz.equals(ComplexClass2.class.getName())) {
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
        } else if (clazz.equals(ParentClass.class.getName())) {
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

    protected int random(int from, int to) {
        return from + (int) (Math.random() * (to - from) + 0.5);
    }

    @Override
    public Object getItem(PMContext ctx, String property, String value) throws PMException {
        for (Object object : getList(ctx)) {
            final Object actualValue = PresentationManager.getPm().get(object, property);
            if (actualValue != null && actualValue.toString().equals(value)) {
                return object;
            }
        }
        return null;
    }

    @Override
    public List<?> list(PMContext ctx, EntityFilter filter, ListFilter lfilter, ListSort sort, Integer from, Integer count) throws PMException {
        if (list == null) {
            fill(ctx);
        }
        List result = new ArrayList(list);
        if (sort != null) {
            Collections.sort(result, new TestComparator(sort.getFieldId(), sort.isAsc()));
        }
        int f = (from == null) ? 0 : from;
        int c = (int) ((count == null) ? count(ctx) : count);
        System.out.println(String.format("count: %d ; c: %d, f: %d", count(ctx), c, f));
        if (count(ctx) < c) {
            return result;
        }
        if (count(ctx) < f) {
            return null;
        }
        int fpc = f + c;
        final List subList = result.subList(f, (int) ((fpc > count(ctx)) ? count(ctx) : fpc));
        if (filter == null) {
            return subList;
        } else {
            final List finalList = new ArrayList<Object>();
            for (Map.Entry entry : filter.getFilterValues().entrySet()) {
                //TODO apply a filter
            }
            return subList;
        }
    }

    @Override
    public Long count(PMContext ctx) throws PMException {
        return new Long(list.size());
    }

    @Override
    public void delete(PMContext ctx, Object object) throws PMException {
        list.remove(object);
    }

    @Override
    public void update(PMContext ctx, Object instance) throws PMException {
    }

    @Override
    public void add(PMContext ctx, Object instance) throws PMException {
        list.add(instance);
    }

    @Override
    public Object refresh(PMContext ctx, Object o) throws PMException {
        return o;
    }

    @Override
    public EntityFilter createFilter(PMContext ctx) throws PMException {
        return new EntityFilter();
    }

    public List<?> getList(PMContext ctx) {
        return list;
    }
}
