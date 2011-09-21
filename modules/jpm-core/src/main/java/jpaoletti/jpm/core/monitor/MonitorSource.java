package jpaoletti.jpm.core.monitor;

import java.util.List;
import java.util.Properties;
import jpaoletti.jpm.core.PersistenceManager;

/** A monitor source is where the monitor takes the information
 * 
 * @author jpaoletti
 * 
 * */
public abstract class MonitorSource {

    private PersistenceManager persistenceManager;
    private Properties properties;

    public MonitorSource(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    public PersistenceManager getPersistenceManager() {
        return persistenceManager;
    }

    public void setPersistenceManager(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    /**
     * Initializates the monitor
     */
    public abstract void init();

    /**
     * Returns a list of line from the actual object
     * 
     * @param actual Actual line identification
     * @return The list of lines
     * @throws Exception
     */
    public abstract List<MonitorLine> getLinesFrom(Object actual) throws Exception;

    /**
     * Returns the last line of the monitor
     * @return  The line
     * @throws Exception
     */
    public abstract MonitorLine getLastLine() throws Exception;

    /**
     * Setter for properties
     * @param properties
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * Getter for properties
     * @return The properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**Getter for a specific property with a default value in case its not defined. 
     * Only works for string.
     * @param name Property name
     * @param def Default value
     * @return Property value only if its a string */
    public String getConfig(String name, String def) {
        if (properties != null) {
            Object obj = properties.get(name);
            if (obj instanceof String) {
                return obj.toString();
            }
        }
        return def;
    }

    /**Getter for any property in the properties object
     * @param name The property name
     * @return The property value */
    public String getConfig(String name) {
        return getConfig(name, null);
    }
}
