package jpaoletti.jpm.ui.validators;

import java.util.List;
import jpaoletti.jpm.core.*;
import jpaoletti.jpm.core.message.Message;
import jpaoletti.jpm.core.message.MessageFactory;
import jpaoletti.jpm.test.SimpleClass;
import jpaoletti.jpm.validator.*;

/**
 *
 * @author jpaoletti
 */
public class SimpleClassUniqueIdValidator extends ValidatorSupport {

    @Override
    public ValidationResult validate(PMContext ctx) {
        final ValidationResult result = new ValidationResult();
        final Message error = MessageFactory.error(ctx.getEntity(), get("msg", "pm.validator.simple.class.uniqueid"));
        result.setSuccessful(true);
        try {
            final SimpleClass sc = (SimpleClass) ctx.getSelected().getInstance();
            final List<SimpleClass> list = (List<SimpleClass>) ctx.getEntity().getList(ctx);
            for (SimpleClass simpleClass : list) {
                if (simpleClass.getId().equals(sc.getId())) {
                    result.setSuccessful(false);
                    result.getMessages().add(error);
                }
            }
        } catch (PMException ex) {
            ctx.getPresentationManager().error(ex);
            result.setSuccessful(false);
            result.getMessages().add(error);
        }
        return result;
    }
}
