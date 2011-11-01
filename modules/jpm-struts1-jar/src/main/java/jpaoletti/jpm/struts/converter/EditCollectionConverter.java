package jpaoletti.jpm.struts.converter;

import java.util.Collection;
import java.util.List;

import javax.naming.ConfigurationException;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.struts.PMStrutsContext;

/**
 * Converter for a collection of objects of another strong entity
 * 
 * @author jpaoletti
 */
@Deprecated
public class EditCollectionConverter extends AbstractCollectionConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        try {
            final PMStrutsContext c = (PMStrutsContext) ctx;
            Collection<Object> result = getCollection(ctx);
            List<?> list = recoverList(c, getConfig("entity"), true);

            result.clear();
            String s = (String) ctx.getFieldValue();
            if (s.trim().compareTo("") == 0) {
                return result;
            }
            String[] ss = s.split(";");
            if (ss.length > 0) {
                for (int i = 0; i < ss.length; i++) {
                    Integer x = Integer.parseInt(ss[i]);
                    if (includeRepeated(ctx) || !result.contains(list.get(x))) {
                        result.add(list.get(x));
                    }
                }
            }
            return result;
        } catch (ConverterException e2) {
            throw e2;
        } catch (Exception e1) {
            ctx.getPresentationManager().error(e1);
            throw new ConverterException("pm.struts.converter.cant.convert.collection");
        }
    }

    /**
     * Return the collection from the entity instance (or a new one if its null)
     * 
     * @param ctx The context
     * @return The collection to edit
     * @throws ConverterException
     * @throws ConfigurationException
     */
    protected Collection<Object> getCollection(PMContext ctx) throws ConverterException {
        final String collection_class = getConfig("collection-class");
        if (collection_class == null) {
            throw new ConverterException("pm.struts.converter.class.mustbedefined");
        }
        final Object instance = ctx.getEntityInstance();
        Collection<Object> result = null;
        result = (Collection<Object>) getValue(instance, ctx.getField());
        if (result == null) {
            result = (Collection<Object>) ctx.getPresentationManager().newInstance(collection_class);
        }
        return result;
    }

    private boolean includeRepeated(PMContext ctx) {
        return getConfig("include-repeated", "false").equals("true");
    }
}
