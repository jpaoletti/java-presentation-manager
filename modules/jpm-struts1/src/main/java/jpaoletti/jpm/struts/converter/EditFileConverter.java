package jpaoletti.jpm.struts.converter;

import java.io.File;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.converter.IgnoreConvertionException;
import jpaoletti.jpm.core.PMContext;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * class="jpaoletti.jpm.struts.converter.EditFileConverter"
 *
 * Properties:
 *
 * * mode: bytes / file
 *
 * * filename
 *
 * * not-null
 *
 * * measure: b(ytes) / k(bytes) / m(bytes)
 *
 * @author jpaoletti
 */
public class EditFileConverter extends DefaultStrutsConverter {

    @Override
    public Object visualize(PMContext ctx) throws ConverterException {
        Object file = ctx.getFieldValue();
        if (file == null) {
            file = getValue(ctx.getEntityInstance(), ctx.getField());
        }
        final String mode = getConfig("mode", "file");
        if (mode.equals("file")) {
            if (file != null) {
                return super.visualize("file.jsp?text=" + ((File) file).getName());
            } else {
                return super.visualize("file.jsp?text=" + ctx.getPresentationManager().message("file.converter.null.file.text"));
            }
        } else if (mode.equals("bytes")) {
            final byte[] bytes = (byte[]) file;
            if (file != null && bytes.length > 0) {
                final String measure = getConfig("measure", "k");
                String len;
                switch (measure.charAt(0)) {
                    case 'b':
                        len = bytes.length + " bytes";
                        break;
                    case 'm':
                        len = (bytes.length / 1024 / 1024) + " Mb";
                        break;
                    case 'k':
                        ;
                    default:
                        len = (bytes.length / 1024) + "Kb";
                }
                return super.visualize("file.jsp?delete=true&text=" + ctx.getPresentationManager().message("file.converter.bytes.text", len) + "&accept=" + getConfig("accept", ""));
            } else {
                return super.visualize("file.jsp?delete=false&text=" + ctx.getPresentationManager().message("file.converter.null.file.text") + "&accept=" + getConfig("accept", ""));
            }
        } else {
            throw new ConverterException("file.converter.misconfigured.mode");
        }
    }

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        try {
            if (!(ctx.getFieldValue() instanceof FileItem)) {
                throw new ConverterException("file.converter.error.not.file");
            }
            final FileItem item = (FileItem) ctx.getFieldValue();
            if (!ctx.getParameter("f_" + ctx.getField().getId() + "_delete", "false").equals("true")) {
                //If not deleting and item is empty, ignore conversion to keep actual
                if (item == null || item.getSize() == 0) {
                    throw new IgnoreConvertionException();
                } else {
                    final String mode = getConfig("mode", "file");
                    if (mode.equals("file")) {
                        final String filename = getConfig("filename");
                        if (filename == null) {
                            throw new ConverterException("file.converter.misconfigured.filename");
                        }
                        final File file = new File(filename);
                        item.write(file);
                        return file;
                    } else if (mode.equals("bytes")) {
                        return item.get();
                    } else {
                        throw new ConverterException("file.converter.misconfigured.mode");
                    }
                }
            } else {
                if (getConfig("not-null", "false").equals("true")) {
                    throw new ConverterException("file.converter.error.null");
                } else {
                    return null;
                }
            }
        } catch (IgnoreConvertionException e) {
            throw e;
        } catch (ConverterException e) {
            throw e;
        } catch (Exception e) {
            throw new ConverterException("file.converter.error");
        }
    }
}
