package jpaoletti.jpm.validator;

import java.util.regex.Pattern;

/**
 * Validates an e-mail address. A null or empty mail is considered valid.
 *
 * @author jpaoletti
 * @since 05/03/2012
 * @version 1.3.0
 *
 */
public class MailValidator extends IsNameValidator {

    private static final Pattern rfc2822 = Pattern.compile(
            "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");

    @Override
    protected boolean isName(String fieldvalue) {
        return (fieldvalue == null) || "".equals(fieldvalue) || rfc2822.matcher(fieldvalue).matches();
    }
}
