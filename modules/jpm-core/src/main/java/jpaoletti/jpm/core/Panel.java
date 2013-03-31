package jpaoletti.jpm.core;

import java.util.ArrayList;
import java.util.List;
import jpaoletti.jpm.security.core.PMSecurityUser;

/**
 * A grouped set of fields.
 *
 * @author jpaoletti
 */
public class Panel {

    private String icon;
    private String title;
    private String fields;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Fields contained in this panel.
     */
    public List<Field> getFields(Entity entity, String operationId, PMSecurityUser user) {
        final String[] fs = getFields().split("[ ]");
        final List<Field> result = new ArrayList<Field>();
        for (String string : fs) {
            final Field field = entity.getFieldById(string);
            if (field != null) {
                if (operationId == null || field.shouldDisplay(operationId, user)) {
                    result.add(field);
                }
            }
        }
        return result;
    }
}
