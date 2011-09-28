package jpaoletti.jpm.struts;

/**
 * Helper class to build web based links for menu
 * 
 * @author jpaoletti
 */
public class MenuItemContext {
    private String prefix;
    private String sufix;
    private String value;
    /**
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }
    /**
     * @param sufix the sufix to set
     */
    public void setSufix(String sufix) {
        this.sufix = sufix;
    }
    /**
     * @return the sufix
     */
    public String getSufix() {
        return sufix;
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

}
