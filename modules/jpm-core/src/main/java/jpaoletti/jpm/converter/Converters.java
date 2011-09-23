package jpaoletti.jpm.converter;

import java.util.List;
import jpaoletti.jpm.core.PMCoreObject;
import jpaoletti.jpm.core.PresentationManager;

/**Collection of converters*/
public class Converters extends PMCoreObject {

    private List<Converter> converters;
    private List<ExternalConverter> externalConverters;

    /**
     * Looks for an aproppiate converter for the given operation id.
     * @param operId The operation id
     * @return The first converter that matches this operation.
     */
    public Converter getConverterForOperation(String operId) {
        if (getConverters() != null) {
            for (Converter converter : getConverters()) {
                if (check(converter, converter.getOperations(), operId)) {
                    return converter;
                }
            }
        }
        if (getExternalConverters() != null) {
            for (ExternalConverter ecs : getExternalConverters()) {
                final Converter c = PresentationManager.getPm().findExternalConverter(ecs.getId());
                if (c != null && check(c, (ecs.getOperations() == null) ? c.getOperations() : ecs.getOperations(), operId)) {
                    //TODO Add override of properties.
                    return c;
                }
            }
        }
        return null;
    }

    protected boolean check(Converter converter, String operations, String operId) {
        return converter != null
                && (operations.trim().equalsIgnoreCase("all")
                || operations.contains(operId));
    }

    public List<ExternalConverter> getExternalConverters() {
        return externalConverters;
    }

    public void setExternalConverters(List<ExternalConverter> externalConverters) {
        this.externalConverters = externalConverters;
    }

    /**
     * @param converters the converters to set
     */
    public void setConverters(List<Converter> converters) {
        this.converters = converters;
    }

    /**
     * @return the converters
     */
    public List<Converter> getConverters() {
        return converters;
    }
}
