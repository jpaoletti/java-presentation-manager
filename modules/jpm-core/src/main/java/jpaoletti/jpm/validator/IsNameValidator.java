package jpaoletti.jpm.validator;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.message.MessageFactory;

/**
 * Validate that the field value is a valid name, so it cant use special
 * characters. Properties are: msg: the message to show when there is an invalid
 * character. This should be a key for messages properties file
 *
 * @author jpaoletti
 */
public class IsNameValidator extends ValidatorSupport {

    /**
     * The validate method
     */
    @Override
    public ValidationResult validate(PMContext ctx) {
        final ValidationResult res = new ValidationResult();
        final String fieldvalue = (String) ctx.getFieldValue();
        res.setSuccessful(true);
        if (!isName(fieldvalue)) {
            res.setSuccessful(false);
            res.getMessages().add(MessageFactory.error(ctx.getEntity(), ctx.getField(), get("msg", "")));
        }
        return res;
    }

    protected boolean isName(String fieldvalue) {
        return true; //TODO
    }
}
