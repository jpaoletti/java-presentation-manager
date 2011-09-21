package jpaoletti.jpm.hibernate.security.db;

import java.util.ArrayList;
import java.util.List;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.exception.ConnectionNotFoundException;
import jpaoletti.jpm.hibernate.security.*;
import jpaoletti.jpm.security.core.*;
import org.hibernate.Session;

import org.hibernate.criterion.Restrictions;

/**
 * Security connector that uses an hibernate session and a database support
 * for authentication.
 *
 */
public class PMSecurityDBConnector extends PMSecurityAbstractConnector {

    /**
     * Get hibernate session from the context
     */
    protected Session getDb() {
        return (Session) getCtx().getPersistenceManager().getConnection();
    }

    @Override
    public PMSecurityUser getUser(String username) throws PMSecurityException {
        try {
            final SECUser dbuser = getDBUser(username);
            if (dbuser == null) {
                throw new UserNotFoundException();
            }
            return convert(dbuser);
        } catch (ConnectionNotFoundException ex) {
            throw new PMSecurityException(ex);
        }
    }

    public SECUser getDBUser(String username) throws PMSecurityException, ConnectionNotFoundException {
        final Session db = getDb();
        SECUser u = null;
        try {
            u = (SECUser) db.createCriteria(SECUser.class).add(Restrictions.eq("nick", username)).uniqueResult();
            if (u != null) {
                db.refresh(u);
            }
        } catch (Exception e) {
            throw new PMSecurityException(e);
        }
        return u;
    }

    @Override
    public List<PMSecurityUser> getUsers() throws PMSecurityException {
        final List<PMSecurityUser> result = new ArrayList<PMSecurityUser>();
        try {
            final List<SECUser> users = getDb().createCriteria(SECUser.class).list();
            for (SECUser u : users) {
                result.add(convert(u));
            }
        } catch (PMSecurityException e) {
            throw e;
        } catch (Exception e) {
            throw new PMSecurityException(e);
        }
        return result;
    }

    @Override
    public void addUser(PMSecurityUser user) throws PMSecurityException {
        try {
            if (getDBUser(user.getUsername().toLowerCase()) != null) {
                throw new UserAlreadyExistException();
            }
            checkUserRules(user.getUsername(), user.getPassword());
            final SECUser secuser = unconvert(null, user);
            secuser.setPassword(encrypt(user.getPassword()));
            getDb().save(secuser);
        } catch (PMSecurityException e) {
            throw e;
        } catch (Exception e) {
            throw new PMSecurityException(e);
        }
    }

    @Override
    public void updateUser(PMSecurityUser user) throws PMSecurityException {
        try {
            checkUserRules(user.getUsername(), user.getPassword());
            SECUser secuser = getDBUser(user.getUsername());
            secuser = unconvert(secuser, user);
            getDb().update(secuser);
        } catch (PMSecurityException e) {
            throw e;
        } catch (Exception e) {
            throw new PMSecurityException(e);
        }
    }

    @Override
    public PMSecurityUserGroup getGroup(String groupname) throws PMSecurityException {
        return convert(getDBGroup(groupname));
    }

    public SECUserGroup getDBGroup(String groupname) throws PMSecurityException {
        try {
            return (SECUserGroup) getDb().createCriteria(SECUserGroup.class).add(Restrictions.eq("name", groupname)).uniqueResult();
        } catch (Exception e) {
            throw new PMSecurityException(e);
        }
    }

    @Override
    public List<PMSecurityUserGroup> getGroups() throws PMSecurityException {
        final List<PMSecurityUserGroup> groups = new ArrayList<PMSecurityUserGroup>();
        final List<SECUserGroup> ug = getDb().createCriteria(SECUserGroup.class).list();
        for (SECUserGroup g : ug) {
            groups.add(convert(g));
        }
        return groups;
    }

    @Override
    public void addGroup(PMSecurityUserGroup group) throws PMSecurityException {
        try {
            if (getDBGroup(group.getName()) != null) {
                throw new GroupAlreadyExistException();
            }

            final SECUserGroup secuserg = unconvert(null, group);

            getDb().save(secuserg);
        } catch (PMSecurityException e) {
            throw e;
        } catch (Exception e) {
            throw new PMSecurityException(e);
        }
    }

    @Override
    public void updateGroup(PMSecurityUserGroup group) throws PMSecurityException {
        try {
            final Session db = getDb();
            SECUserGroup secuserg = getDBGroup(group.getName());
            db.refresh(secuserg);
            secuserg = unconvert(secuserg, group);
            db.update(secuserg);
        } catch (PMException ex) {
            throw new PMSecurityException(ex);
        }

    }

    @Override
    public List<PMSecurityPermission> getPermissions() throws PMSecurityException {
        final List<PMSecurityPermission> perms = new ArrayList<PMSecurityPermission>();
        final List<SECPermission> ps = getDb().createCriteria(SECPermission.class).list();
        for (SECPermission p : ps) {
            perms.add(convert(p));
        }
        return perms;
    }

    /*Converters*/
    protected PMSecurityUser convert(SECUser u) throws PMSecurityException {
        final PMSecurityUser user = new PMSecurityUser();
        load(u, user);
        return user;
    }

    protected void load(SECUser u, PMSecurityUser user) throws PMSecurityException {
        user.setActive(u.isActive());
        user.setChangePassword(u.isChangePassword());
        user.setDeleted(u.isDeleted());
        user.setEmail(u.getEmail());
        user.setName(u.getName());
        user.setPassword(u.getPassword());
        user.setUsername(u.getNick());
        for (SECUserGroup g : u.getGroups()) {
            user.getGroups().add(convert(g));
        }
    }

    protected PMSecurityUserGroup convert(SECUserGroup g) {
        if (g == null) {
            return null;
        }
        final PMSecurityUserGroup group = new PMSecurityUserGroup();
        group.setActive(g.isActive());
        group.setDescription(g.getDescription());
        group.setName(g.getName());
        for (SECPermission p : g.getPermissions()) {
            group.getPermissions().add(convert(p));
        }

        return group;
    }

    protected PMSecurityPermission convert(SECPermission p) {
        if (p == null) {
            return null;
        }
        final PMSecurityPermission perm = new PMSecurityPermission();
        perm.setDescription(p.getDescription());
        perm.setName(p.getName());
        return perm;
    }

    protected SECUser unconvert(SECUser secuser, PMSecurityUser u) throws PMSecurityException {
        if (u == null) {
            return null;
        }
        SECUser user = secuser;
        if (secuser == null) {
            user = new SECUser();
        }
        unload(u, secuser, user);
        return user;
    }

    protected void unload(PMSecurityUser u, SECUser secuser, SECUser output) throws PMSecurityException {
        output.getGroups().clear();
        output.setActive(u.isActive());
        output.setChangePassword(u.isChangePassword());
        output.setDeleted(u.isDeleted());
        output.setEmail(u.getEmail());
        output.setName(u.getName());
        output.setPassword(u.getPassword());
        if (secuser == null) {
            output.setNick(u.getUsername().toLowerCase());
        }
        for (PMSecurityUserGroup g : u.getGroups()) {
            output.getGroups().add(getDBGroup(g.getName()));
        }
    }

    protected SECUserGroup unconvert(SECUserGroup secgroup, PMSecurityUserGroup g) throws PMException {
        if (g == null) {
            return null;
        }
        SECUserGroup group = secgroup;
        if (secgroup == null) {
            group = new SECUserGroup();
        }
        group.setActive(g.isActive());
        group.setDescription(g.getDescription());
        if (secgroup == null) {
            group.setName(g.getName());
        }
        for (PMSecurityPermission p : g.getPermissions()) {
            group.getPermissions().add(getDBPerm(p.getName()));
        }
        return group;
    }

    protected SECPermission getDBPerm(String name) throws PMException {
        final Session db = getDb();
        SECPermission p = null;
        try {
            p = (SECPermission) db.createCriteria(SECPermission.class).add(Restrictions.eq("name", name)).uniqueResult();
            if (p != null) {
                db.refresh(p);
            }
        } catch (Exception e) {
            throw new PMSecurityException(e);
        }
        return p;
    }

    protected SECPermission unconvert(PMSecurityPermission p) {
        if (p == null) {
            return null;
        }
        final SECPermission perm = new SECPermission();
        perm.setDescription(p.getDescription());
        perm.setName(p.getName());
        return perm;
    }

    @Override
    public void removeGroup(PMSecurityUserGroup group) throws PMSecurityException {
        getDb().delete(getDBGroup(group.getName()));
    }
}
