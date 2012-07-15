package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;

/**
 * Hack to split forms in two columns. Only for bootstrap version.
 *
 * To use this converter this two methods must be placed at represented class:
 *
 * public String getTwocolumn() {return "ignore";} public void
 * setTwocolumn(String ignore) {}
 *
 * <converter class="jpaoletti.jpm.struts.converter.TwoColumnConverter">
 *     <properties>
 *         <property name="count" value="11" />
 *     </properties>
 * </converter>
 *
 * @author jpaoletti
 */
public class TwoColumnConverter extends DefaultStrutsConverter {

    @Override
    public Object visualize(PMContext ctx) throws ConverterException {
        final String count1 = getConfig("count", "5");
        return super.visualize("twocolumn.jsp?count1=" + count1 + "&class1=" + getConfig("class1", "span6") + "&class2=" + getConfig("class2", "span6"));
    }
}
