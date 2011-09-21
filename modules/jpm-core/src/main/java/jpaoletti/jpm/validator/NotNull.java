package jpaoletti.jpm.validator;

import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.message.MessageFactory;

/**Validate that the specified field is not null
 * 
 * <h2>Simple entity configuration file</h2>
 * <pre>
 * {@code
 * <field>
 *     <id>some_id</id>
 *     ....
 *     <validator class="jpaoletti.jpm.validator.NotNull" />
 * </field>
 * }
 * </pre>
 * @author jpaoletti
 * */
public class NotNull extends ValidatorSupport {

    /**The validation method*/
    @Override
    public ValidationResult validate(PMContext ctx) {
        final ValidationResult res = new ValidationResult();
        final Object fieldvalue = ctx.getFieldValue();
        res.setSuccessful(fieldvalue != null);
        if (!res.isSuccessful()) {
            res.getMessages().add(MessageFactory.error(
                    ctx.getEntity(), ctx.getField(),
                    get("msg", "void"),
                    ctx.getField().getId()));
        }
        return res;
    }
}
