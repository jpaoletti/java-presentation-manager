package jpaoletti.jpm.core;

/***/
public class Highlight {

    /***/
    private String field;
    /***/
    private String value;
    @Deprecated
    private String color;
    /**instance: all the item. property: only the value */
    private String scope;
    /**
     * Implementation specific definition for styling
     */
    private String style;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public boolean isInstance() {
        return "instance".equals(getScope());
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return the scope. 'instance' for the whole item and 'property' for just
     * the instance property. instance by default
     */
    public String getScope() {
        if (scope == null) {
            return "instance";
        }
        return scope;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param field the field to set
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @return the field
     */
    public String getField() {
        return field;
    }
}
