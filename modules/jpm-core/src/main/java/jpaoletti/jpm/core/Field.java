package jpaoletti.jpm.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import jpaoletti.jpm.converter.Converter;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.converter.Converters;
import jpaoletti.jpm.converter.GenericConverter;
import jpaoletti.jpm.security.core.PMSecurityUser;
import jpaoletti.jpm.validator.Validator;
import org.apache.commons.lang.reflect.FieldUtils;

/**
 * A Field represents a property of the represented entity.
 *
 * <h2>Simple entity configuration file</h2>
 * <pre>
 * {@code
 * <field id="id" display="all | some_operations" align="right | left | center" width="xxxx" />
 *     ....
 * </field>
 * }
 * </pre>
 *
 * @author jpaoletti
 *
 */
public class Field extends PMCoreObject {

    /**
     * The id of the field, must be unique in the entity
     */
    private String id;
    /**
     * The property to be accesed on the entity instance objects. There must be
     * a getter and a setter for this name on the represented entity. When null,
     * default is the field id
     */
    private String property;
    /**
     * The width of the field value
     */
    private String width;
    private String display;
    private ArrayList<Validator> validators;
    private Converters converters;
    private String defaultValue;
    private String align; //left right center
    private Entity entity;
    private List<FieldOperationConfig> configs;

    /**
     * Default constructor
     */
    public Field() {
        super();
        align = "left";
        defaultValue = "";
    }

    /**
     * Visualize the field using the given operation and entity
     *
     * @param ctx the context
     * @param operation The operation
     * @param entity The entity
     * @return The string visualization
     * @throws PMException
     */
    public Object visualize(PMContext ctx, Operation operation, Entity entity) throws PMException {
        debug("Converting [" + operation.getId() + "]" + entity.getId() + "." + getId());
        try {
            Converter c = null;
            if (getConverters() != null) {
                c = getConverter(operation.getId());
            }
            if (c == null) {
                c = getDefaultConverter();
            }
            final Object instance = ctx.getEntityInstance();
            ctx.setField(this);
            // We only set the field value if instance is not null.
            // Some operations may use this value without an instance.
            if (instance != null) {
                ctx.setEntityInstanceWrapper(ctx.buildInstanceWrapper(instance));
                ctx.setFieldValue(getPm().get(instance, getProperty()));
            }
            return c.visualize(ctx);
        } catch (ConverterException e) {
            throw e;
        } catch (Exception e) {
            getPm().error(e);
            throw new ConverterException("Unable to convert " + entity.getId() + "." + getProperty());
        }
    }

    /**
     * Return the default converter if none is defined
     *
     * @return The converter
     */
    public Converter getDefaultConverter() {
        if (getPm().getDefaultConverter() == null) {
            Converter c = new GenericConverter();
            Properties properties = new Properties();
            properties.put("filename", "converters/show.tostring.converter");
            c.setProperties(properties);
            return c;
        } else {
            return getPm().getDefaultConverter();
        }
    }

    /**
     * Visualize the field using the given operation and context entity
     *
     * @param ctx the context
     * @param operation The operation
     * @return The String visualization
     * @throws PMException
     */
    public Object visualize(PMContext ctx, Operation operation) throws PMException {
        return visualize(ctx, operation, ctx.getEntity());
    }

    /**
     * Visualize the field using the context operation and entity
     *
     * @param ctx The context
     * @return a String with the visualization
     * @throws PMException
     */
    public Object visualize(PMContext ctx) throws PMException {
        return visualize(ctx, ctx.getOperation());
    }

    /**
     *
     * @return
     */
    public ArrayList<Validator> getValidators() {
        return validators;
    }

    /**
     *
     * @param validators
     */
    public void setValidators(ArrayList<Validator> validators) {
        this.validators = validators;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param display
     */
    public void setDisplay(String display) {
        this.display = display;
    }

    public List<FieldOperationConfig> getConfigs() {
        if (configs == null) {
            configs = new ArrayList<FieldOperationConfig>();
        }
        return configs;
    }

    public void setConfigs(List<FieldOperationConfig> configs) {
        this.configs = configs;
    }

    /**
     * A (separated by blanks) list of operation ids where this field will be
     * displayed
     *
     * @return The list
     */
    public String getDisplay() {
        if (display == null || display.trim().equals("")) {
            return "all";
        }
        return display;
    }

    /**
     * Indicates if the field is shown in the given operation id
     *
     * @param operationId The Operation id
     * @return true if field is displayed on the operation
     */
    public boolean shouldDisplay(String operationId, PMSecurityUser user) {
        if (operationId == null) {
            return false;
        }
        //First we check permissions
        for (FieldOperationConfig config : getConfigs()) {
            if (config.includes(operationId)) {
                if (config.getPerm() != null && user != null && !user.hasPermission(config.getPerm())) {
                    return false;
                }
            }
        }
        if (getDisplay().equalsIgnoreCase("all")) {
            return true;
        }
        final String[] split = getDisplay().split("[ ]");
        for (String string : split) {
            if (string.equalsIgnoreCase(operationId)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param defaultValue
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     *
     * @return
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     *
     * @param align
     */
    public void setAlign(String align) {
        this.align = align;
    }

    /**
     *
     * @return
     */
    public String getAlign() {
        return align;
    }

    /**
     *
     * @param width
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     *
     * @return
     */
    public String getWidth() {
        if (width == null) {
            return "";
        }
        return width;
    }

    /**
     * @param converters the converters to set
     */
    public void setConverters(Converters converters) {
        this.converters = converters;
    }

    /**
     * @return the converters
     */
    public Converters getConverters() {
        if (converters == null) {
            converters = new Converters();
        }
        return converters;
    }

    /**
     * String representation of the field
     *
     * @return
     */
    @Override
    public String toString() {
        return id;
    }

    /**
     * @return the property of the entity instance object that can be accesed by
     * getter and setter. Default value is the field id
     */
    public String getProperty() {
        String r = property;
        if (r == null || r.trim().equals("")) {
            r = id;
        }
        return r;
    }

    /**
     * Setter for property
     *
     * @param property
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * Returns the internationalized field title
     */
    public String getTitle() {
        final String key = String.format("pm.field.%s.%s", getEntity().getId(), getId());
        final String message = getPm().message(key);
        if (key.equals(message)) {
            final Entity extendzEntity = getEntity().getExtendzEntity();
            if (extendzEntity != null && extendzEntity.getFieldById(getId()) != null) {
                return extendzEntity.getFieldById(getId()).getTitle();
            }
        }
        return message;
    }

    /**
     * Returns the internationalized field tooltip
     */
    public String getTooltip() {
        final String key = "pm.field." + getEntity().getId() + "." + getId() + ".tooltip";
        if (key == null) {
            return null;
        }
        final String message = getPm().message(key);
        if (key.equals(message)) {
            return null;
        }
        return message;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Find the right converter for this field on the given operation.
     *
     * @param operation
     *
     * @return a converter
     */
    public Converter getConverter(String operation) {
        //First we check "covnerters" list
        Converter c = getConverters().getConverterForOperation(operation);
        if (c == null) {
            // if not found, we check configs
            for (FieldOperationConfig config : getConfigs()) {
                if (config.includes(operation)) {
                    c = getPm().findExternalConverter(config.getEconverter());
                    break;
                }
            }
        }
        if (c == null) {
            // If not found, we check class level converters
            final String _property = getProperty();
            try {
                final String[] _properties = _property.split("[.]");
                Class<?> clazz = Class.forName(getEntity().getClazz());
                for (int i = 0; i < _properties.length - 1; i++) {
                    clazz = FieldUtils.getField(clazz, _properties[i], true).getType();
                }
                final String className = FieldUtils.getField(clazz, _properties[_properties.length - 1], true).getType().getName();
                c = getPm().getClassConverters().getConverter(operation, className);
            } catch (Exception ex) {
                getPm().info(String.format("Unable to introspect field '%s' on entity '%s'", _property, getEntity().getId()));
            }
        }
        return c;
    }
}
