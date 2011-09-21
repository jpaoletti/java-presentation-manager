package jpaoletti.jpm.validator;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.message.MessageFactory;

/**Validate the length of the string.
 * max-length: maximum length of the string
 * max-length-msg: message to show if the name is too long
 * min-length: minimum length of the string
 * min-length-msg:  message to show if the name is too short
 * 
 * @author jpaoletti
 */
public class LengthValidator extends ValidatorSupport {

    /**The validate method*/
    @Override
    public ValidationResult validate(PMContext ctx) {
        final ValidationResult res = new ValidationResult();
        final Object object = ctx.getFieldValue();
        final String fieldId = ctx.getField().getId();

        if (object instanceof String) {
            String fieldvalue = (String) object;
            res.setSuccessful(true);
            Integer len = fieldvalue.length();
            Integer maxl = getInt("max-length");
            if (maxl != null) {
                if (len > maxl) {
                    res.setSuccessful(false);
                    res.getMessages().add(MessageFactory.error(
                            ctx.getEntity(), ctx.getField(),
                            get("max-length-msg", "pm_core.validator.toolong"),
                            fieldId, len.toString(), maxl.toString()));
                }
            }
            Integer minl = getInt("min-length");
            if (minl != null) {
                if (len < minl) {
                    res.setSuccessful(false);
                    res.getMessages().add(MessageFactory.error(
                            ctx.getEntity(), ctx.getField(),
                            get("min-length-msg", "pm_core.validator.tooshort"),
                            fieldId, len.toString(), minl.toString()));
                }
            }
        } else {
            res.setSuccessful(false);
            res.getMessages().add(MessageFactory.error(
                    ctx.getEntity(), ctx.getField(),
                    "pm_core.validator.fieldnotstring",
                    fieldId));
        }
        return res;
    }
}
