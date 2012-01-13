package jpaoletti.jpm.core;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import jpaoletti.jpm.menu.Menu;
import jpaoletti.jpm.security.core.PMSecurityUser;
import jpaoletti.jpm.util.StringEncrypter;

/**
 *
 * @author jpaoletti
 */
public class PMSession extends PMContext {

    private String sessionId;
    private PMSecurityUser user;
    private Menu menu;
    private final Map<String, EntityContainer> containers = new HashMap<String, EntityContainer>();
    private Date lastAccess;
    private PMChatLog chatLog;
    private StringEncrypter stringEncrypter;

    public PMSession(String id) {
        this.sessionId = id;
        this.chatLog = new PMChatLog();
        this.stringEncrypter = new StringEncrypter(id);
    }

    public Collection<EntityContainer> getContainers() {
        return containers.values();
    }

    public void setContainer(String entityId, EntityContainer container) {
        containers.put(entityId, container);
    }

    public EntityContainer getContainer(String entityId) {
        return containers.get(entityId);
    }

    @Override
    public PMSecurityUser getUser() {
        return user;
    }

    public void setUser(PMSecurityUser user) {
        this.user = user;
    }

    public String getId() {
        return sessionId;
    }

    public void setId(String id) {
        this.sessionId = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public PMChatLog getChatLog() {
        return chatLog;
    }

    /**
     * Getter for the string encripter for this session
     * 
     * @return enripter
     */
    public StringEncrypter getStringEncrypter() {
        return stringEncrypter;
    }

    public void setStringEncrypter(StringEncrypter stringEncrypter) {
        this.stringEncrypter = stringEncrypter;
    }
}
