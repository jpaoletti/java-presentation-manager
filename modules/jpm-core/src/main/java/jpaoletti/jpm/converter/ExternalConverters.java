package jpaoletti.jpm.converter;

import java.util.List;

import jpaoletti.jpm.core.PMCoreObject;

/**
 * Collection of external converters
 * 
 * @author jpaoletti
 */
public class ExternalConverters extends PMCoreObject{
    private List<ConverterWrapper> converters;

    public ConverterWrapper getWrapper(String id){
        for (ConverterWrapper cw : converters) {
            if(cw.getId().equalsIgnoreCase(id))
                return cw;
        }
        return null;
    }

    public List<ConverterWrapper> getConverters() {
        return converters;
    }

    public void setConverters(List<ConverterWrapper> converters) {
        this.converters = converters;
    }
    
}