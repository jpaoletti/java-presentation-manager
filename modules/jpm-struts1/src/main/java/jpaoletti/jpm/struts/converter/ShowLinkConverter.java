package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.struts.PMEntitySupport;

/**
 * Link converter shows a link with the property set in "display" as the text
 * of the link and a reference to another operation (set with "operation" poperty)
 * with an identified referenciation to the given "entity" and the given
 * identified "property".
 *
 * <br>
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.ShowLinkConverter" operations="show list">
 *     <properties>
 *         <property name="entity"    value="other-entity" />
 *         <property name="property"  value="other-entity-id" />
 *         <property name="display"   value="other-entity-showable-prop" />
 *         <property name="operation" value="some-operation-of-other-entity" />
 *     </properties>
 * </converter>
 * }
 * </pre>
 * @author J.Paoletti
 *
 */
public class ShowLinkConverter extends DefaultStrutsConverter {

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        final String property = getConfig("property");
        final String display = getConfig("display");
        final Object otherObject = ctx.getPresentationManager().get(ctx.getEntityInstance(), ctx.getField().getProperty());
        ctx.put("display", PMEntitySupport.toHtml(ctx.getPresentationManager().getAsString(otherObject, display)));
        ctx.put("identified", property + ":" + ctx.getPresentationManager().getAsString(otherObject, property));
        ctx.put("other_entity", getConfig("entity"));
        ctx.put("other_operation", getConfig("operation", "show"));
        return super.visualize("link_converter.jsp?");
    }
}
