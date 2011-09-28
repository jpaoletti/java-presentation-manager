package jpaoletti.jpm.struts.converter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.converter.IgnoreConvertionException;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.util.Utils;

public class ShowISODumpConverter extends StrutsEditConverter {

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        throw new IgnoreConvertionException("");
    }

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        byte[] p = (byte[]) getValue(ctx.getEntityInstance(), ctx.getField());
        if (p != null) {
            try {
                String string = Utils.hexdump(p); //new String(p);
                return super.visualize("isodump_converter.jsp?value=" + URLEncoder.encode(string, "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                throw new ConverterException(ex);
            }
        } else {
            return super.visualize("isodump_converter.jsp?value=-");
        }
    }
}
