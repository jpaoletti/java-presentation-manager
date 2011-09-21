package jpaoletti.jpm.parser;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.util.ArrayList;
import jpaoletti.jpm.converter.Converter;
import jpaoletti.jpm.converter.Converters;
import jpaoletti.jpm.converter.ExternalConverter;
import jpaoletti.jpm.core.PresentationManager;

/**
 * This is not a recursive class! This is an xstream converter for the
 * pm.core.converter.Converter class.
 * 
 * @author jpaoletti
 */
public class ConverterConverter implements com.thoughtworks.xstream.converters.Converter {

    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext mc) {
        final Converters converters = (Converters) o;
        writer.startNode("converters");
        for (Converter c : converters.getConverters()) {
            writer.startNode("converter");
            mc.convertAnother(c);
            writer.endNode();
        }
        for (ExternalConverter c : converters.getExternalConverters()) {
            writer.startNode("econverter");
            mc.convertAnother(c);
            writer.endNode();
        }
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext uc) {
        final Converters converters = new Converters();
        converters.setConverters(new ArrayList<Converter>());
        converters.setExternalConverters(new ArrayList<ExternalConverter>());
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            if (reader.getNodeName().equals("converter")) {
                final String clazz = reader.getAttribute("class");
                try {
                    final Converter c = (Converter) uc.convertAnother(converters, Class.forName(clazz));
                    converters.getConverters().add(c);
                } catch (Exception ex) {
                    PresentationManager.getPm().warn("Converter not found: " + clazz);
                }
            } else if (reader.getNodeName().equals("econverter")) {
                ExternalConverter c = (ExternalConverter) uc.convertAnother(converters, ExternalConverter.class);
                converters.getExternalConverters().add(c);
            }
            reader.moveUp();
        }
        return converters;
    }

    @Override
    public boolean canConvert(Class type) {
        return type.equals(Converters.class);
    }
}
