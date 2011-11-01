package jpaoletti.jpm.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import jpaoletti.jpm.core.*;

/**
 *
 * @author jpaoletti
 */
public class DataAccessPMSession extends AbstractDataAccess {

    @Override
    public Object getItem(PMContext ctx, String property, String value) throws PMException {
        return PresentationManager.getPm().getSession(value);
    }

    @Override
    public List<?> list(PMContext ctx, EntityFilter filter, ListSort sort, Integer from, Integer count) throws PMException {
        final List<PMSession> res = new ArrayList<PMSession>();
        for (Entry<String, PMSession> entry : PresentationManager.getPm().getSessions().entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    @Override
    public Long count(PMContext ctx) throws PMException {
        return new Long(PresentationManager.getPm().getSessions().size());
    }

    @Override
    public void delete(PMContext ctx, Object object) throws PMException {
        final PMSession session = (PMSession) object;
        PresentationManager.getPm().removeSession(session.getId());
    }

    @Override
    public void update(PMContext ctx, Object instance) throws PMException {
    }

    @Override
    public void add(PMContext ctx, Object instance) throws PMException {
    }

    @Override
    public Object refresh(PMContext ctx, Object o) throws PMException {
        return o;
    }

    @Override
    public EntityFilter createFilter(PMContext ctx) throws PMException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
