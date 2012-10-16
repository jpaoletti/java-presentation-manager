package jpaoletti.jpm.core;

/**
 * A field configuration item for one or more operations. Works also as a
 * shorcut for econverters.
 *
 * <pre>
 * {@code
 * <field-config operations="" perm="" econverter="" />
 * }
 * </pre>
 *
 * @author jpaoletti
 */
public class FieldOperationConfig extends PMCoreObject {

    private String operations;
    private String perm;
    private String econverter;

    public boolean includes(String operationId) {
        if (getOperations() == null) {
            return true;
        }
        if (getOperations().equalsIgnoreCase("all")) {
            return true;
        }
        final String[] split = getOperations().split("[ ]");
        for (String string : split) {
            if (string.equalsIgnoreCase(operationId)) {
                return true;
            }
        }
        return false;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    public String getEconverter() {
        return econverter;
    }

    public void setEconverter(String econverter) {
        this.econverter = econverter;
    }
}
