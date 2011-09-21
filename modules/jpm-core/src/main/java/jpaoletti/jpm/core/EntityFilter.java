package jpaoletti.jpm.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityFilter extends PMCoreObject {

    private FilterBehavior behavior;
    private Map<String, FilterOperation> filterOperations;
    private Map<String, List<Object>> filterValues;

    /**
     * Default constructor
     */
    public EntityFilter() {
        behavior = FilterBehavior.AND;
        filterOperations = new HashMap<String, FilterOperation>();
        filterValues = new HashMap<String, List<Object>>();
    }

    public boolean isOperation(String fid, FilterOperation oper) {
        return getFilterOperations().get(fid) == oper;
    }

    public void process(Entity entity) {
    }

    public void clear() {
    }

    public void addFilter(String fieldId, List<Object> values, FilterOperation operation) {
        filterOperations.put(fieldId, operation);
        filterValues.put(fieldId, values);
    }

    public void addFilter(String fieldId, Object value, FilterOperation operation) {
        filterOperations.put(fieldId, operation);
        final List<Object> values = new ArrayList<Object>();
        values.add(value);
        filterValues.put(fieldId, values);
    }

    public Map<String, FilterOperation> getFilterOperations() {
        return filterOperations;
    }

    public Map<String, List<Object>> getFilterValues() {
        return filterValues;
    }

    public void setFilterOperations(Map<String, FilterOperation> filterOperations) {
        this.filterOperations = filterOperations;
    }

    public FilterOperation getFilterOperation(String id) {
        final FilterOperation result = getFilterOperations().get(id);
        if (result != null) {
            return result;
        } else {
            return FilterOperation.LIKE;
        }
    }

    public FilterBehavior getBehavior() {
        return behavior;
    }

    public void setBehavior(FilterBehavior behavior) {
        this.behavior = behavior;
    }
}
