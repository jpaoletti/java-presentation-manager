package jpaoletti.jpm.struts;

import org.apache.struts.action.ActionForward;

/**
 *
 * @author jpaoletti
 */
public class NoActionForward extends PMForwardException {

    public NoActionForward() {
        super((ActionForward) null);
    }
}
