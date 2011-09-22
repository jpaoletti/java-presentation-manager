package jpaoletti.jpm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * List of properties.
 *
 * @author jpaoletti
 * @since 22/09/2011
 * @version 1.0
 *
 */
public class Properties {

    private List<Property> properties;

    public String getProperty(String name) {
        for (Property property : properties) {
            if (property.getName().equals(name)) {
                return property.getValue();
            }
        }
        return null;
    }

    public String getProperty(String name, String def) {
        final String s = getProperty(name);
        if (s == null) {
            return def;
        } else {
            return s;
        }
    }

    public Integer getInt(String name, Integer def) {
        final String s = getProperty(name);
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * Return all values for the given name
     */
    public List<String> getAll(String name) {
        final List<String> all = new ArrayList<String>();
        for (Property property : properties) {
            if (property.getName().equals(name)) {
                all.add(property.getValue());
            }
        }
        return all;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
