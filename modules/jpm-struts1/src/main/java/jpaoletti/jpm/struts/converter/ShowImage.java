package jpaoletti.jpm.struts.converter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.security.core.PMSecurityUser;

/**
 * This converter takes a byte[] and turn it into a img. At the moment it
 * allows only one image per entity to be shown
 * 
 * @author jpaoletti
 */
public class ShowImage extends DefaultStrutsConverter {

    @Override
    public Object visualize(PMContext ctx) throws ConverterException {
        final String filename = createTmpFile(ctx);
        ctx.put(PM_VOID_TEXT, "<img src='/pm/cache/" + filename + "' alt='x' />");
        return "void.jsp?";

    }

    protected String createTmpFile(PMContext ctx) throws ConverterException {
        FileOutputStream fos = null;
        try {
            final PMSecurityUser user = ctx.getUser();
            byte[] value = (byte[]) ctx.getFieldValue();
            if(value==null) return null;
            final String filename = user.getUsername() + "." + getConfig("type", "png");
            final String dir = "webapps/pm/cache/";
            final File directory = new File(dir);
            directory.mkdirs();
            final File tmpfile = new File(dir + filename);
            fos = new FileOutputStream(tmpfile);
            fos.write(value);
            return filename;
        } catch (Exception ex) {
            throw new ConverterException(ex);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                throw new ConverterException(ex);
            }
        }
    }
}
