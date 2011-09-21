package jpaoletti.jpm.converter;

import java.util.Properties;
import jpaoletti.jpm.core.*;

/**
 * This class represent an external converter, that is a converter defined
 * once in a definition file and usable via id in any entity.
 *
 * &lt;econverter id="someid" operations="list show" /&gt;
 * 
 * @author jpaoletti
 */
public class ExternalConverter extends PMCoreObject{
    private String id;
    private String operations;
    private Properties properties;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExternalConverter other = (ExternalConverter) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

}
