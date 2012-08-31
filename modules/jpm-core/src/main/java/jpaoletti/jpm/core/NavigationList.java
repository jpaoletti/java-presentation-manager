package jpaoletti.jpm.core;

import java.util.ArrayList;
import jpaoletti.jpm.core.NavigationList.NavigationListItem;

/**
 * A list for quick navigation.
 *
 * @author jpaoletti
 */
public class NavigationList extends ArrayList<NavigationListItem> {

    public void reset() {
        this.clear();
    }

    public void update(EntityContainer entityContainer, OperationCommand opCmd, Operation operation) {
        try {
            if (operation != null) {
                cut(entityContainer.getId(), operation.getId());
                EntityContainer e = entityContainer;
                boolean reset = true;
                while (e != null) {
                    if (includesEntity(e.getId())) {
                        reset = false;
                        break;
                    }
                    e = e.getOwner();
                }
                if (reset) {
                    reset();
                }
                if (isGeneralScoped(operation)) {
                    addItem(entityContainer, operation, PresentationManager.getMessage("pm.core.navigationlist.general",
                            entityContainer.getEntity().getTitle(),
                            operation.getTitle()));
                } else {
                    final Object i = entityContainer.getSelected().getInstance();
                    final NavigationListItem item = addItem(entityContainer, operation, PresentationManager.getMessage("pm.core.navigationlist.item",
                            entityContainer.getEntity().getTitle(),
                            operation.getTitle(),
                            i != null ? i.toString() : "?"));
                    if (i != null) {
                        item.setSelectedId(entityContainer.getSelected().getInstanceId());
                    }
                }
            }
        } catch (Exception ex) {
            PresentationManager.getPm().warn(ex);
        }
    }

    protected boolean isGeneralScoped(Operation operation) {
        return operation.getScope().equals(Operation.SCOPE_GENERAL);
    }

    protected void cut(String entityId, String operationId) {
        int idx = -1;
        for (NavigationListItem item : this) {
            if (item.getEntityContainer().getId().equals(entityId) && item.getOperation().getId().equals(operationId)) {
                idx = indexOf(item);
                break;
            }
        }
        if (idx != -1) {
            removeRange(idx, size());
        }
    }

    protected boolean includesEntity(String entityId) {
        for (NavigationListItem item : this) {
            if (item.getEntityContainer().getId().equals(entityId)) {
                return true;
            }
        }
        return false;
    }

    public NavigationListItem addItem(EntityContainer entityContainer, Operation operation, String title) {
        //The new item replaces any item scoped operation that is not owner ot the new one.
        final NavigationListItem item = new NavigationListItem(entityContainer, operation, title);
        final NavigationListItem lastItem = getLastItem();
        if (lastItem != null) {
            if (!lastItem.getOperation().isNavigable() || (!isGeneralScoped(lastItem.getOperation()) && (entityContainer.getOwner() == null || !lastItem.getEntityContainer().equals(entityContainer.getOwner())))) {
                this.remove(lastItem);
            }
        }
        this.add(item);
        return item;
    }

    public NavigationListItem getLastItem() {
        try {
            return get(size() - 1);
        } catch (Exception e) {
            return null;
        }
    }

    public static class NavigationListItem {

        private EntityContainer entityContainer;
        private Operation operation;
        private InstanceId selectedId;
        private String title;

        public NavigationListItem(EntityContainer entityContainer, Operation operation, String title) {
            this.entityContainer = entityContainer;

            this.operation = operation;
            this.title = title;
        }

        public InstanceId getSelectedId() {
            return selectedId;
        }

        public void setSelectedId(InstanceId selectedId) {
            this.selectedId = selectedId;
        }

        public EntityContainer getEntityContainer() {
            return entityContainer;
        }

        public void setEntityContainer(EntityContainer entityContainer) {
            this.entityContainer = entityContainer;
        }

        public Operation getOperation() {
            return operation;
        }

        public void setOperation(Operation operation) {
            this.operation = operation;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
