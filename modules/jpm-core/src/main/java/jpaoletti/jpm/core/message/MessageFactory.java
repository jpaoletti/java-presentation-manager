package jpaoletti.jpm.core.message;

import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.Field;

/**
 * Factory for messages
 *
 * @author jpaoletti
 * @since 13/09/2011
 * @version v1.1
 *
 */
public class MessageFactory {

    /**
     * Create a system scoped information message
     */
    public static Message info(String key, String... args) {
        return message(MessageType.INFO, key, args);
    }

    /**
     * Create a system scoped warning message
     */
    public static Message warn(String key, String... args) {
        return message(MessageType.WARN, key, args);
    }

    /**
     * Create a system scoped error message
     */
    public static Message error(String key, String... args) {
        return message(MessageType.ERROR, key, args);
    }

    /**
     * Create an entity scoped information message
     */
    public static Message info(Entity entity, String key, String... args) {
        return message(MessageType.INFO, entity, key, args);
    }

    /**
     * Create an entity scoped warning message
     */
    public static Message warn(Entity entity, String key, String... args) {
        return message(MessageType.WARN, entity, key, args);
    }

    /**
     * Create an entity scoped error message
     */
    public static Message error(Entity entity, String key, String... args) {
        return message(MessageType.ERROR, entity, key, args);
    }

    /**
     * Create a field scoped information message
     */
    public static Message info(Entity entity, Field field, String key, String... args) {
        return message(MessageType.INFO, entity, field, key, args);
    }

    /**
     * Create a field scoped warning message
     */
    public static Message warn(Entity entity, Field field, String key, String... args) {
        return message(MessageType.WARN, entity, field, key, args);
    }

    /**
     * Create a field scoped error message
     */
    public static Message error(Entity entity, Field field, String key, String... args) {
        return message(MessageType.ERROR, entity, field, key, args);
    }

    private static Message message(MessageType type, String key, String... args) {
        final Message message = new Message();
        message.setType(type);
        message.setKey(key);
        message.setArgs(args);
        return message;
    }

    private static Message message(MessageType type, Entity entity, String key, String... args) {
        final Message message = message(type, key, args);
        message.setEntity(entity);
        return message;
    }

    private static Message message(MessageType type, Entity entity, Field field, String key, String... args) {
        final Message message = message(type, key, args);
        message.setEntity(entity);
        message.setField(field);
        return message;
    }
}
