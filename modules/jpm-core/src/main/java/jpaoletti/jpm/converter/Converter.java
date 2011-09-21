package jpaoletti.jpm.converter;

import java.util.Properties;

import jpaoletti.jpm.core.EntityInstanceWrapper;
import jpaoletti.jpm.core.Field;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMCoreObject;
import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.util.Utils;

/**A converter is an object associated to a field that determine the way the field value
 * will be visualized and build from a visual representation to a value for a given 
 * operation.
 * <pre>
 * {@code
 * <field>
 *     ...
 *     <converters>
 *         <converter class="some.converter.Class" debug="true" operations="oper1 oper2">
 *            <!-- A properties object to get some extra configurations -->
 *            <properties>
 *                <property name="propname" value="propvalue" />
 *            </properties>
 *         </converter>
 *     </converters>
 * </field>
 * }
 * </pre>
 * @author jpaoletti
 **/
public class Converter extends PMCoreObject {

    private String operations;
    private Properties properties;
    private Boolean validate;

    /**This method transforms the given value into an object to visualize it
     * @param ctx The context.
     *         Field:             ctx.getField();
     *         F.Value:           ctx.getFieldValue();
     *         Inst.Wrapper       ctx.getEntityInstanceWrapper();
     *         Entity:            ctx.getEntity();
     *         Entity Instance:   ctx.getEntityInstance();
     *         Operation:         ctx.getOperation();
     * @return The string representation of the object 
     * @throws ConverterException*/
    public Object visualize(PMContext ctx) throws ConverterException {
        throw new IgnoreConvertionException();
    }

    /**This method takes a specific format of the object from the visualization (usually a string) and
     * transforms it in the required object.
     * @param ctx The context.
     *         Field:             ctx.getField();
     *         F.Value:           ctx.getFieldValue();
     *         Inst.Wrapper       ctx.getEntityInstanceWrapper();
     *         Entity:            ctx.getEntity();
     *         Entity Instance:   ctx.getEntityInstance();
     *         Operation:         ctx.getOperation();
     * @return The value to be set in the entity instance.
     * @throws ConverterException 
     * */
    public Object build(PMContext ctx) throws ConverterException {
        throw new IgnoreConvertionException();
    }

    /**Getter for a specific property with a default value in case its not defined.
     * Only works for string.
     * @param name Property name
     * @param def Default value
     * @return Property value only if its a string */
    public String getConfig(String name, String def) {
        debug("Converter.getConfig(" + name + "," + def + ")");
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

    /**Getter for the value
     * @param einstance The entity instance
     * @param field The field
     * @return The field value on the entity instance*/
    protected Object getValue(Object einstance, Field field) {
        return getNestedProperty(einstance, field.getProperty());
    }

    /**Getter for the value
     * @param einstance The entity instance wrapper
     * @param field The field
     * @return The field value on the entity instance*/
    protected Object getValue(EntityInstanceWrapper einstance, Field field) {
        return getValue(einstance.getInstance(), field);
    }

    /**Getter for a nested property in the given object.
     * @param obj The object
     * @param propertyName The name of the property to get
     * @return The property value */
    public Object getNestedProperty(Object obj, String propertyName) {
        if (obj != null && propertyName != null) {
            return PresentationManager.getPm().get(obj, propertyName);
        }
        return null;
    }

    /**
     * @return the properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * @param operations the operations to set
     */
    public void setOperations(String operations) {
        this.operations = operations;
    }

    /**
     * @return the operations
     */
    public String getOperations() {
        if (operations == null) {
            return "all";
        }
        return operations;
    }

    /**Visualization with some standard properties.
     * @param obj The object to visualize
     * @param extra Some extra text. Not used at the moment.
     * @return The string representation of the given object
     * @throws ConverterException when an exception occurs trying to convert.
     */
    public String visualize(Object obj) throws ConverterException {
        Integer pad = 0;
        String padc = getConfig("pad-count", "0");
        try {
            pad = Integer.parseInt(padc);
        } catch (Exception e) {
        }
        char padch = getConfig("pad-char", " ").charAt(0);
        String padd = getConfig("pad-direction", "left");

        String prefix = getConfig("prefix");
        String suffix = getConfig("suffix");
        String res = obj != null ? obj.toString() : "";
        if (padd.compareToIgnoreCase("left") == 0) {
            res = Utils.padleft(res, pad, padch);
        } else {
            res = Utils.padright(res, pad, padch);
        }
        if (prefix != null) {
            res = prefix + res;
        }
        if (suffix != null) {
            res = res + suffix;
        }
        return res;
    }

    public Boolean getValidate() {
        if (validate == null) {
            return true;
        }
        return validate;
    }

    public void setValidate(Boolean validate) {
        this.validate = validate;
    }
}
