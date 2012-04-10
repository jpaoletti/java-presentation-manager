package jpaoletti.jpm.parser;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import jpaoletti.jpm.converter.Converter;
import jpaoletti.jpm.converter.ConverterWrapper;
import jpaoletti.jpm.core.PresentationManager;

/**
 *
 * @author jpaoletti
 */
public class EConverterConverter implements com.thoughtworks.xstream.converters.Converter {

    private PresentationManager pm;

    public EConverterConverter(PresentationManager pm) {
        this.pm = pm;
    }

    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext mc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext uc) {
        final ConverterWrapper result = new ConverterWrapper();
        result.setId(reader.getAttribute("id"));
        reader.moveDown();
        final String clazz = reader.getAttribute("class");
        try {
            final Converter c = (Converter) uc.convertAnother(result, Class.forName(clazz));
            result.setConverter(c);
        } catch (Exception ex) {
            pm.warn("External converter not found: " + clazz);
        } finally {
            reader.moveUp();
        }
        return result;
    }

    @Override
    public boolean canConvert(Class type) {
        return type.equals(ConverterWrapper.class);
    }
}
