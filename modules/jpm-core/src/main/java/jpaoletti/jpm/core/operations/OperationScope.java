package jpaoletti.jpm.core.operations;

/**
 * Operation scopes
 * 
 * @author jpaoletti
 */
public enum OperationScope {

    GENERAL("general"), ITEM("item"), SELECTED("selected");
    private String name;

    private OperationScope(String name) {
        this.name = name;
    }

    public boolean is(String name) {
        return getName().equals(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
