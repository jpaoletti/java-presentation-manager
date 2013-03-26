package jpaoletti.jpm.struts.converter;

import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.struts.PMStrutsContext;

public class EditStringConverter extends StrutsEditConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        final PMStrutsContext c = (PMStrutsContext) ctx;
        final Object value = ctx.getFieldValue();
        if (withNull() && (value == null || value.equals(""))) {
            return null;
        } else {
            return (value != null) ? value.toString() : null;
        }
    }

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        Object p = ctx.getFieldValue();
        if (p == null) {
            p = getValue(ctx.getEntityInstance(), ctx.getField());
        }
        final String value = normalize((p == null) ? "" : p.toString());
        ctx.setFieldValue(value);
        return super.visualize("string_converter.jsp?ml=" + getConfig("max-length"));
    }

    public String normalize(String s) {
        StringBuilder str = new StringBuilder();

        int len = (s != null) ? s.length() : 0;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '<':
                    str.append("&lt;");
                    break;
                case '>':
                    str.append("&gt;");
                    break;
                case '&':
                    str.append("&amp;");
                    break;
                /*
                 case '"': 
                 str.append("&quot;");
                 break;
                 */
                case '\'':
                    str.append("&apos;");
                    break;
                case '\r':
                case '\n':
                    str.append("&#x");
                    str.append(Integer.toHexString((int) (ch & 0xFF)));
                    str.append(';');
                    break;
                default:
                    if (ch < 0x20) {
                        str.append("&#x");
                        str.append(Integer.toHexString((int) (ch & 0xFF)));
                        str.append(';');
                    } else if (ch > 0xff00) {
                        str.append((char) (ch & 0xFF));
                    } else {
                        str.append(ch);
                    }
            }
        }
        return (str.toString());
    }

    protected boolean withNull() {
        return getConfig("with-null", "false").equalsIgnoreCase("true");
    }
}
