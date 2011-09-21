package jpaoletti.jpm.core.operations;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.PMSession;
import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.menu.Menu;
import jpaoletti.jpm.menu.MenuSupport;
import jpaoletti.jpm.security.core.InvalidPasswordException;
import jpaoletti.jpm.security.core.InvalidUserException;
import jpaoletti.jpm.security.core.PMSecurityConnector;
import jpaoletti.jpm.security.core.PMSecurityException;
import jpaoletti.jpm.security.core.PMSecurityUser;
import jpaoletti.jpm.security.core.UserAlreadyLogged;
import jpaoletti.jpm.security.core.UserNotFoundException;

/**
 *
 * @author jpaoletti
 */
public class LoginOperation extends OperationCommandSupport {

    public LoginOperation(String operationId) {
        super(operationId);
    }

    @Override
    protected boolean prepare(PMContext ctx) throws PMException {
        return true;
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        final PresentationManager pm = ctx.getPresentationManager();
        final PMSession session = pm.registerSession(null);
        ctx.setSessionId(session.getId());
        if (pm.isLoginRequired()) {
            try {
                final PMSecurityUser u = authenticate(ctx);
                session.setUser(u);
                session.setMenu(loadMenu(session, u));
            } catch (UserNotFoundException e) {
                pm.removeSession(session.getId());
                throw new PMException("pm_security.user.not.found");
            } catch (UserAlreadyLogged e) {
                pm.removeSession(session.getId());
                throw new PMException("pm_security.user.already.logged");
            } catch (InvalidPasswordException e) {
                pm.removeSession(session.getId());
                throw new PMException("pm_security.password.invalid");
            } catch (Exception e) {
                pm.error(e);
                pm.removeSession(session.getId());
                throw new PMException("pm_core.unespected.error");
            }
        } else {
            final PMSecurityUser user = new PMSecurityUser();
            user.setName(" ");
            session.setUser(user);
            session.setMenu(loadMenu(session, user));
        }
    }

    private Menu loadMenu(PMSession session, PMSecurityUser u) throws PMException {
        final Menu menu = MenuSupport.getMenu(u.getPermissionList());
        session.put("user", u);
        session.put("menu", menu);
        return menu;
    }

    /**
     * @param ctx The context with all the parameters
     * @return The user
     */
    private PMSecurityUser authenticate(PMContext ctx) throws PMSecurityException {
        PMSecurityUser u = null;
        final Object username = ctx.getParameter("username");
        final Object password = ctx.getParameter("password");
        if (username == null || password == null) {
            throw new InvalidUserException();
        }
        u = getConnector(ctx).authenticate(
                username.toString(),
                password.toString());
        return u;
    }

    private PMSecurityConnector getConnector(PMContext ctx) {
        return PresentationManager.getPm().getSecurityConnector();
    }

    @Override
    protected boolean checkUser() {
        return false;
    }
}
