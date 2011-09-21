package jpaoletti.jpm.validator;

import jpaoletti.jpm.core.PMContext;

/**This interface represents any validation that can be made to an entity instance or
 * to a specific field of an instance. Any operation may have one or more validators.  
 * 
 * <h2>Simple entity configuration file</h2>
 * <pre>
 * {@code
 * <field>
 *     <id>some_id</id>
 *     ....
 *     <validator class="jpaoletti.jpm.validator.SomeValidator" />
 * </field>
 * }
 * </pre>
 * @author jpaoletti
 * */
public interface Validator {
    /**
     * @param ctx The context
     * @return {@link ValidationResult}
     */
    public ValidationResult validate(PMContext ctx); 

}
