package jpaoletti.jpm.converter;

import jpaoletti.jpm.core.*;

/**
 * Wrapper for a converter to be defined in external converters definition
 * file.
 *
 * @author jpaoletti
 */
public class ConverterWrapper extends PMCoreObject {

    private String id;
    private Converter converter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConverterWrapper other = (ConverterWrapper) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

}
