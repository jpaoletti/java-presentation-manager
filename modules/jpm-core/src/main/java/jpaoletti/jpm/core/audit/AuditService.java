package jpaoletti.jpm.core.audit;

import jpaoletti.jpm.core.PMContext;

/**
 * Audit system.
 *
 * @author jpaoletti
 */
public interface AuditService {

    public void register(PMContext ctx, Integer level, String operationId, String observations);

    public Integer getLevel();

    public void setLevel(Integer level);
}
