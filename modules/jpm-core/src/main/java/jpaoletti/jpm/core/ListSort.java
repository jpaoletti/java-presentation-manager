package jpaoletti.jpm.core;

/**
 * Entity list order
 *
 * @author jpaoletti
 */
public class ListSort {

    private String fieldId;
    private SortDirection direction;

    public ListSort(String fieldId, SortDirection direction) {
        this.fieldId = fieldId;
        this.direction = direction;
    }

    public boolean isDesc() {
        return getDirection().equals(SortDirection.DESC);
    }

    public boolean isAsc() {
        return getDirection().equals(SortDirection.ASC);
    }

    public boolean isSorted() {
        return getFieldId() != null;
    }

    public SortDirection getDirection() {
        return direction;
    }

    public void setDirection(SortDirection direction) {
        this.direction = direction;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public static enum SortDirection {

        ASC, DESC;
    }
}
