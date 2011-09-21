package jpaoletti.jpm.core;

import java.util.*;
import jpaoletti.jpm.converter.*;
import jpaoletti.jpm.core.monitor.Monitor;
import jpaoletti.jpm.menu.*;
import jpaoletti.jpm.parser.*;
import jpaoletti.jpm.security.core.PMSecurityConnector;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author jpaoletti
 */
public class PresentationManager extends Observable {

    /**  Hash value for parameter encrypt  */
    public static String HASH = "abcde54321poiuy96356abcde54321poiuy96356";
    /** Singleton */
    public static PresentationManager pm;
    private static Long sessionIdSeed = 0L;
    private Properties cfg;
    private static final String TAB = "    ";
    private static final String ERR = " ==>";
    private Map<Object, Entity> entities;
    private Map<String, MenuItemLocation> locations;
    private Map<Object, Monitor> monitors;
    private List<ExternalConverters> externalConverters;
    private String persistenceManager;
    private boolean error;
    private final Map<String, PMSession> sessions = new HashMap<String, PMSession>();
    private Timer sessionChecker;
    private PMSecurityConnector securityConnector;

    /**
     * Initialize the Presentation Manager
     * @param cfg Configuration from bean
     * @param log Logger from bean
     * @param service Bean
     * @return
     */
    public boolean initialize(Properties cfg) {
        notifyObservers();
        this.cfg = cfg;
        error = false;

        final StringBuilder evt = new StringBuilder();
        evt.append("Presentation Manager activated");
        try {
            evt.append(TAB + "<configuration>");

            try {
                Class.forName(getDefaultDataAccess());
                logItem(evt, "Default Data Access", getDefaultDataAccess(), "*");
            } catch (Exception e) {
                logItem(evt, "Default Data Access", getDefaultDataAccess(), "?");
            }

            logItem(evt, "Template", getTemplate(), "*");
            logItem(evt, "Menu", getMenu(), "*");
            logItem(evt, "Application version", getAppversion(), "*");
            logItem(evt, "Title", getTitle(), "*");
            logItem(evt, "Subtitle", getSubtitle(), "*");
            logItem(evt, "Contact", getContact(), "*");
            logItem(evt, "Login Required", Boolean.toString(isLoginRequired()), "*");
            logItem(evt, "Default Converter", getDefaultConverterClass(), "*");

            persistenceManager = cfg.getProperty("persistence-manager", "jpaoletti.jpm.core.PersistenceManagerVoid");
            try {
                newInstance(persistenceManager);
                logItem(evt, "Persistance Manager", persistenceManager, "*");
            } catch (Exception e) {
                error = true;
                logItem(evt, "Persistance Manager", persistenceManager, "?");
            }
            evt.append(TAB + "<configuration>\n");

            loadEntities(evt);
            loadMonitors(evt);
            loadConverters(evt);
            loadLocations(evt);
            createSessionChecker();
        } catch (Exception exception) {
            error(exception);
            error = true;
        }
        if (error) {
            evt.append("error: One or more errors were found. Unable to start jPM");
        }
        info(evt);
        return !error;
    }

    public String getTitle() {
        return cfg.getProperty("title", "pm.title");
    }

    public String getTemplate() {
        return cfg.getProperty("template", "default");
    }

    public boolean isDebug() {
        return "true".equals(cfg.getProperty("debug"));
    }

    public String getAppversion() {
        return cfg.getProperty("appversion", "1.0.0");
    }

    /**
     * If debug flag is active, create a debug information log
     * 
     * @param invoquer The invoquer of the debug
     * @param o Object to log
     */
    public void debug(Object invoquer, Object o) {
        if (!isDebug()) {
            return;
        }
        System.out.println("[" + invoquer.getClass().getName() + "]");
        System.out.println(o);
    }

    protected String getDefaultConverterClass() {
        return getCfg().getProperty("default-converter");
    }

    private void loadMonitors(StringBuilder evt) {
        PMParser parser = new MonitorParser();
        evt.append(TAB + "<monitors>\n");
        Map<Object, Monitor> result = new HashMap<Object, Monitor>();
        String[] ss = getAll("monitor");
        for (Integer i = 0; i < ss.length; i++) {
            try {
                Monitor m = (Monitor) parser.parseFile(ss[i]);
                result.put(m.getId(), m);
                result.put(i, m);
                m.getSource().init();
                Thread thread = new Thread(m);
                m.setThread(thread);
                thread.start();
                logItem(evt, m.getId(), m.getSource().getClass().getName(), "*");
            } catch (Exception exception) {
                error(exception);
                logItem(evt, ss[i], null, "!");
            }
        }
        monitors = result;
        evt.append(TAB + "</monitors>\n");
    }

    /**
     * Formatting helper for startup
     * @param evt The event
     * @param s1 Text
     * @param s2 Extra description
     * @param symbol Status symbol
     */
    public static void logItem(StringBuilder sb, String s1, String s2, String symbol) {
        sb.append(String.format("%s%s(%s) %-25s %s", TAB, TAB, symbol, s1, (s2 != null) ? s2 : ""));
    }

    private void loadLocations(StringBuilder sb) {
        sb.append(TAB + "<locations>");
        MenuItemLocationsParser parser = new MenuItemLocationsParser("cfg/pm.locations.xml");
        locations = parser.getLocations();
        if (locations == null || locations.isEmpty()) {
            sb.append(TAB + TAB + ERR + "No location defined!\n");
            error = true;
        }
        if (parser.hasError()) {
            error = true;
        }
        sb.append(TAB + "</locations>\n");
    }

    private void loadEntities(StringBuilder sb) {
        EntityParser parser = new EntityParser();
        sb.append(TAB + "<entities>\n");
        if (entities == null) {
            entities = new HashMap<Object, Entity>();
        } else {
            entities.clear();
        }
        String[] ss = getAll("entity");
        for (Integer i = 0; i < ss.length; i++) {
            try {
                Entity e = (Entity) parser.parseFile(ss[i]);
                try {
                    Class.forName(e.getClazz());
                    entities.put(e.getId(), e);
                    entities.put(i, e);
                    if (e.isWeak()) {
                        logItem(sb, e.getId(), e.getClazz(), "\u00b7");
                    } else {
                        logItem(sb, e.getId(), e.getClazz(), "*");
                    }

                } catch (ClassNotFoundException cnte) {
                    logItem(sb, e.getId(), e.getClazz(), "?");
                    error = true;
                }
            } catch (Exception exception) {
                error(exception);
                logItem(sb, ss[i], "???", "!");
                error = true;
            }
        }
        sb.append(TAB + "</entities>\n");
    }

    /**
     * Return the list of weak entities of the given entity.
     * @param e The strong entity
     * @return The list of weak entities
     */
    protected List<Entity> weakEntities(Entity e) {
        List<Entity> res = new ArrayList<Entity>();
        for (Entity entity : getEntities().values()) {
            if (entity.getOwner() != null && entity.getOwner().getEntityId().compareTo(e.getId()) == 0) {
                res.add(entity);
            }
        }
        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    /**
     * Return the entity of the given id
     * @param id Entity id
     * @return The entity
     */
    public Entity getEntity(String id) {
        Entity e = getEntities().get(id);
        if (e == null) {
            return null;
        }
        if (e.getExtendz() != null && e.getExtendzEntity() == null) {
            e.setExtendzEntity(this.getEntity(e.getExtendz()));
        }
        return e;
    }

    /**
     * Return the location of the given id
     * @param id The location id
     * @return The MenuItemLocation
     */
    public MenuItemLocation getLocation(String id) {
        return locations.get(id);
    }

    /**
     * Return the monitor of the given id
     * @param id The monitor id
     * @return The monitor
     */
    public Monitor getMonitor(String id) {
        return getMonitors().get(id);
    }

    /**Create and fill a new Entity Container
     * @param id Entity id
     * @return The container
     */
    public EntityContainer newEntityContainer(String id) {
        Entity e = lookupEntity(id);
        if (e == null) {
            return null;
        }
        e.setWeaks(weakEntities(e));
        return new EntityContainer(e, HASH);
    }

    /**Looks for an Entity with the given id*/
    private Entity lookupEntity(String sid) {
        for (Integer i = 0; i < getEntities().size(); i++) {
            Entity e = getEntities().get(i);
            if (e != null && sid.compareTo(EntityContainer.buildId(HASH, e.getId())) == 0) {
                return getEntity(e.getId());
            }
        }
        return null;
    }


    /* Getters */
    /**
     * Getter for contact
     * @return
     */
    public String getContact() {
        return cfg.getProperty("contact", "");
    }

    /**
     * Getter for default data access
     * @return
     */
    public String getDefaultDataAccess() {
        return cfg.getProperty("default-data-access", "jpaoletti.jpm.core.DataAccessVoid");
    }

    /**
     * Getter for entities map
     * @return
     */
    public Map<Object, Entity> getEntities() {
        return entities;
    }

    /**
     * Getter for location map
     * @return
     */
    public Map<String, MenuItemLocation> getLocations() {
        return locations;
    }

    /**
     * Getter for login required
     * @return
     */
    public boolean isLoginRequired() {
        return "true".equalsIgnoreCase(cfg.getProperty("login-required", "true"));
    }

    /**
     * Getter for monitor map
     * @return
     */
    public Map<Object, Monitor> getMonitors() {
        return monitors;
    }

    /**
     * Create a new instance of the persistance manager
     * @return
     */
    public PersistenceManager newPersistenceManager() {
        return (PersistenceManager) newInstance(persistenceManager);
    }

    /**
     * Getter for singleton pm
     * @return
     */
    public static PresentationManager getPm() {
        return pm;
    }

    /* Loggin helpers*/
    /**
     * Generate an info entry on the local logger
     * @param o Object to log
     */
    public void info(Object o) {
        System.out.println(o);
    }

    /**Generate a warn entry on the local logger
     * @param o Object to log
     */
    public void warn(Object o) {
        System.out.println(o);
    }

    /**Generate an error entry on the local logger
     * @param o Object to log
     */
    public void error(Object o) {
        System.err.println(o);
    }

    /* Helpers for bean management */
    /**Getter for an object property value as String
     * @param obj The object
     * @param propertyName The property
     * @return The value of the property of the object as string
     * */
    public String getAsString(Object obj, String propertyName) {
        Object o = get(obj, propertyName);
        if (o != null) {
            return o.toString();
        } else {
            return "";
        }
    }

    /**
     * Getter for an object property value
     * @param obj The object
     * @param propertyName The property
     * @return The value of the property of the object
     *
     */
    public Object get(Object obj, String propertyName) {
        try {
            if (obj != null && propertyName != null) {
                return PropertyUtils.getNestedProperty(obj, propertyName);
            }
        } catch (NullPointerException e) {
        } catch (Exception e) {
            // Now I don't like it.
            error(e);
            return "-undefined-";
        }
        return null;
    }

    /**Setter for an object property value
     * @param obj The object
     * @param name The property name
     * @param value The value to set
     * */
    public void set(Object obj, String name, Object value) {
        try {
            PropertyUtils.setNestedProperty(obj, name, value);
        } catch (Exception e) {
            error(e);
        }
    }

    /**
     * Creates a new instance object of the given class.
     * @param clazz The Class of the new Object
     * @return The new Object or null on any error.
     */
    public Object newInstance(String clazz) {
        try {
            return Class.forName(clazz).newInstance();
        } catch (Exception e) {
            error(e);
            return null;
        }
    }

    private void loadConverters(StringBuilder evt) {
        PMParser parser = new ExternalConverterParser();
        evt.append(TAB + "<external-converters>\n");
        externalConverters = new ArrayList<ExternalConverters>();
        String[] ss = getAll("external-converters");
        for (Integer i = 0; i < ss.length; i++) {
            try {
                ExternalConverters ec = (ExternalConverters) parser.parseFile(ss[i]);
                externalConverters.add(ec);
                logItem(evt, ss[i], null, "*");
            } catch (Exception exception) {
                error(exception);
                logItem(evt, ss[i], null, "!");
            }
        }
        evt.append(TAB + "</external-converters>\n");
    }

    public Converter findExternalConverter(String id) {
        for (ExternalConverters ecs : externalConverters) {
            ConverterWrapper w = ecs.getWrapper(id);
            if (w != null) {
                return w.getConverter();
            }
        }
        return null;
    }

    /**
     * Creates a new session with the given id. If null is used, an automatic
     * session id will be generated
     *
     * @param sessionId The new session id. Must be unique.
     * @throws PMException on already defined session
     * @return New session
     */
    public PMSession registerSession(String sessionId) {
        synchronized (sessions) {
            if (sessionId != null) {
                if (!sessions.containsKey(sessionId)) {
                    sessions.put(sessionId, new PMSession(sessionId));
                }
                return getSession(sessionId);
            } else {
                return registerSession(newSessionId());
            }
        }
    }

    /**
     * Return the session for the given id
     * @param sessionId The id of the wanted session
     * @return The session
     */
    public PMSession getSession(String sessionId) {
        final PMSession s = sessions.get(sessionId);
        if (s != null) {
            s.setLastAccess(new Date());
        }
        return s;
    }

    /**
     * Getter for the session map.
     * @return Sessions
     */
    public Map<String, PMSession> getSessions() {
        return sessions;
    }

    /**
     * Removes the given id session 
     */
    public void removeSession(String sessionId) {
        sessions.remove(sessionId);
    }

    public Properties getCfg() {
        return cfg;
    }

    public String getSubtitle() {
        return cfg.getProperty("subtitle", "pm.subtitle");
    }

    private void createSessionChecker() {
        final Long timeout = Long.parseLong(cfg.getProperty("session-timeout", "3600")) * 1000;
        final int interval = Integer.parseInt(cfg.getProperty("session-check-interval", "300")) * 1000;
        if (sessionChecker != null) {
            sessionChecker.cancel();
        }
        sessionChecker = new Timer();
        sessionChecker.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                synchronized (sessions) {
                    List<String> toRemove = new ArrayList<String>();
                    for (Map.Entry<String, PMSession> entry : sessions.entrySet()) {
                        if (entry.getValue().getLastAccess().getTime() + timeout < System.currentTimeMillis()) {
                            toRemove.add(entry.getKey());
                        }
                    }
                    for (String session : toRemove) {
                        removeSession(session);
                    }
                }
            }
        }, 0, interval);
    }

    public String getCopyright() {
        return cfg.getProperty("copyright", "jpaoletti");
    }

    public String getMenu() {
        return cfg.getProperty("menu", "cfg/pm.menu.xml");
    }

    public boolean isHideableHeader() {
        return "true".equalsIgnoreCase(cfg.getProperty("hideable-header", "false"));
    }

    public static synchronized String newSessionId() {
        sessionIdSeed++;
        return sessionIdSeed.toString();
    }

    /**
     * Returns the internacionalized string for the given key
     */
    public static String getMessage(String key, Object... params) {
        if (key == null) {
            return null;
        }
        try {
            final ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResource"); //TODO
            String string = bundle.getString(key);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    String param = (params[i] == null) ? "" : params[i].toString();
                    string = string.replaceAll("\\{" + i + "\\}", param);
                }
            }
            return string;
        } catch (Exception e) {
            return key;
        }
    }

    public Converter getDefaultConverter() {
        try {
            if (getDefaultConverterClass() != null && !"".equals(getDefaultConverterClass().trim())) {
                return (Converter) newInstance(getDefaultConverterClass());
            }
        } catch (Exception e) {
        }
        return null;
    }

    public PMSession getSessionByUser(final String username) {
        for (PMSession s : sessions.values()) {
            if (s.getUser() != null && s.getUser().getUsername().equals(username)) {
                return s;
            }
        }
        return null;
    }

    public PMSecurityConnector getSecurityConnector() {
        return securityConnector;
    }

    protected String[] getAll(String name) {
        String[] ret;
        final Object obj = cfg.get(name);
        if (obj instanceof String[]) {
            ret = (String[]) obj;
        } else if (obj instanceof String) {
            ret = new String[1];
            ret[0] = (String) obj;
        } else {
            ret = new String[0];
        }
        return ret;
    }
}
