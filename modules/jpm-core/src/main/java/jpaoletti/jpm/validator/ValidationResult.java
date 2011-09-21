package jpaoletti.jpm.validator;

import java.util.ArrayList;
import java.util.List;

import jpaoletti.jpm.core.message.Message;

/**
 * The result of a validation.
 */
public class ValidationResult {

    /**True when the validation was successful*/
    private boolean successful;
    /**Error messages. The key is the field id and the value is the error message*/
    private List<Message> messages;

    /**Default constructor*/
    public ValidationResult() {
        super();
        this.messages = new ArrayList<Message>();
    }

    /**
     * @param successful the successful to set
     */
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    /**
     * @return the successful
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * @return the messages
     */
    public List<Message> getMessages() {
        return messages;
    }
}
