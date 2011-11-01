package jpaoletti.jpm.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import jpaoletti.jpm.core.operations.OperationScope;

/**
 * Just a container for a list of operations and some helpers.
 * @author jpaoletti 
 *
 */
public class Operations extends PMCoreObject {

    /**The operation list*/
    private List<Operation> operations;

    /**Returns the operation of the given id or a new default operation
     * @param id The id
     * @return The operation*/
    public Operation getOperation(String id) {
        for (Iterator<Operation> it = operations.iterator(); it.hasNext();) {
            Operation oper = it.next();
            if (oper.getId().compareTo(id) == 0) {
                return oper;
            }
        }
        return newDefaultOperation(id);
    }

    /**
     * A new Operation with the given id
     * @param id The operation id
     * @return The new operation
     */
    private Operation newDefaultOperation(String id) {
        Operation op = new Operation();
        op.setId(id);
        op.setEnabled(true);
        return op;
    }

    /**
     * Returns the Operations for a given operation. That is the operations that are different to
     * the given one, enabled and visible in it.
     * @param operation The operation
     * @return The operations
     */
    public Operations getOperationsFor(final PMContext ctx, Object instance, Operation operation) throws PMException {
        final Operations result = new Operations();
        final List<Operation> r = new ArrayList<Operation>();
        for (Operation op : getOperations()) {
            if (op.isDisplayed(operation.getId()) && op.isEnabled() && !op.equals(operation)) {
                if (op.getCondition() == null || op.getCondition().check(ctx, instance, op, operation.getId())) {
                    if (instance != null || OperationScope.GENERAL.is(op.getScope()) || OperationScope.SELECTED.is(op.getScope())) {
                        r.add(op);
                    }
                }
            }
        }
        result.setOperations(r);
        return result;
    }

    public Operations getItemOperations() {
        return getOperationsForScope(OperationScope.ITEM);
    }

    public Operations getGeneralOperations() {
        return getOperationsForScope(OperationScope.GENERAL);
    }

    /**
     * Returns an Operations object for the given scope
     * @param scopes The scopes
     * @return The Operations
     */
    public Operations getOperationsForScope(OperationScope... scopes) {
        final Operations result = new Operations();
        final List<Operation> r = new ArrayList<Operation>();
        if (getOperations() != null) {
            for (Operation op : getOperations()) {
                if (op.getScope() != null) {
                    String s = op.getScope().trim();
                    for (int i = 0; i < scopes.length; i++) {
                        OperationScope scope = scopes[i];
                        if (scope.is(s)) {
                            r.add(op);
                            break;
                        }
                    }
                }
            }
            result.setOperations(r);
        }
        return result;
    }

    /**
     * @return the operations
     */
    public List<Operation> getOperations() {
        return operations;
    }

    /**
     * @param operations the operations to set
     */
    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    /**
     * Returns the number of operations in the list
     * 
     * @return
     */
    public int count() {
        if (getOperations() == null) {
            return 0;
        } else {
            return getOperations().size();
        }
    }
}
