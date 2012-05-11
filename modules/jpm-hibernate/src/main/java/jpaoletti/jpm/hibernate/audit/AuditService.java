package jpaoletti.jpm.hibernate.audit;

import java.util.Date;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.audit.SimpleAudit;
import org.hibernate.Session;

/**
 *
 * @author jpaoletti
 */
public class AuditService extends SimpleAudit {

    @Override
    public void register(PMContext ctx, Integer level, String operationId, String observations) {
        try {
            final AuditRecord record = new AuditRecord();
            record.setDatetime(new Date());
            if (ctx.isUserOnLine()) {
                record.setUsername(ctx.getUser().getUsername());
            }

            if (ctx.hasEntity()) {
                record.setEntity(ctx.getEntity().getId());
            }

            if (operationId != null) {
                record.setOperation(operationId);
            }

            if (ctx.getSelected() != null && ctx.getSelected().getInstanceId() != null) {
                record.setItem(ctx.getSelected().getInstanceId().getValue());
            }
            record.setObservations(observations);

            final Object tx = ctx.getPersistenceManager().startTransaction(ctx);
            ((Session) ctx.getPersistenceManager().getConnection()).save(record);
            ctx.getPersistenceManager().commit(ctx, tx);
        } catch (Exception ex) {
            ctx.getPresentationManager().error(ex);
        }
    }
}
