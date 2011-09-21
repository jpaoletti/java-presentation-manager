package jpaoletti.jpm.converter;

import jpaoletti.jpm.core.PMContext;

/**Converter for strings <br>
 * <pre>
 * {@code
 * <converter class="jpaoletti.jpm.converter.ShowStringConverter">
 *     <operationId>show</operationId>
 *     <properties>
 *          <property name="prefix" value="" />
 *          <property name="suffix" value="" />
 *     </properties>
 * </converter>
 * }
 * </pre>
 * @author jpaoletti
 * */
public class ShowStringConverter extends Converter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        throw new IgnoreConvertionException("");
    }

    @Override
    public String visualize(Object obj) throws ConverterException {
        String prefix = getConfig("prefix");
        String suffix = getConfig("suffix");
        String res = obj != null ? obj.toString() : "";
        if (!res.equals("")) {
            if (prefix != null) {
                res = prefix + res;
            }
            if (suffix != null) {
                res = res + suffix;
            }
        }
        return res;

    }

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        final Object o = getValue(ctx.getEntityInstance(), ctx.getField());
        return visualize(o);
    }
}
