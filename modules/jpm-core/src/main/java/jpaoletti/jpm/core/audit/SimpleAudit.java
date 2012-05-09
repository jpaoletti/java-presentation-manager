package jpaoletti.jpm.core.audit;

import java.util.Date;
import jpaoletti.jpm.core.PMContext;

/**
 *
 * @author jpaoletti
 */
public class SimpleAudit implements AuditService {

    private Integer level;

    @Override
    public void register(PMContext ctx, Integer level, String operationId, String observations) {
        if (getLevel() >= level) {
            try {
                ctx.getPresentationManager().info(String.format("AUDIT: {%s} - User[%s];Entity[%s];Operation[%s];Item[%s] - %s",
                        new Date().toString(),
                        (ctx.isUserOnLine()) ? ctx.getUser().getUsername() : '-',
                        (ctx.hasEntity()) ? ctx.getEntity().getId() : "-",
                        (operationId != null) ? operationId : '-',
                        (ctx.getSelected() != null) ? ctx.getSelected().getInstanceId().getValue() : '-',
                        observations));
            } catch (Exception ex) {
                ctx.getPresentationManager().error(ex);
            }
        }
    }

    @Override
    public Integer getLevel() {
        return level;
    }

    @Override
    public void setLevel(Integer level) {
        this.level = level;
    }
}
