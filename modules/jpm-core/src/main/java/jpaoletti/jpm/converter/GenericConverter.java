package jpaoletti.jpm.converter;

import java.io.InputStream;

import jpaoletti.jpm.core.PMContext;

import bsh.BshClassManager;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.UtilEvalError;
import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.util.ResourceManager;

/**
 * A generic converter that uses a beanbash based xml for excecution.
 * 
 * @author jpaoletti
 */
public class GenericConverter extends Converter {

    private String filename;
    private Interpreter bsh;
    private String content;

    @Override
    public String visualize(PMContext ctx) throws ConverterException {
        try {
            final Interpreter bash = getBsh();
            final Object o = getValue(ctx.getEntityInstanceWrapper(), ctx.getField());
            bash.set("value", o);
            bash.set("converter", this);
            debug("Generic Converter Visualize value: " + o);
            String res = getConfig("null-value", "-");
            if (o != null) {
                String result = bash.eval(content + "\n" + "visualize();").toString();
                res = visualize(result);
                if ("IgnoreConvertionException".equals(res)) {
                    throw new IgnoreConvertionException("");
                }
            }
            final Converter c = ctx.getField().getDefaultConverter();
            if (c != null && !(c instanceof GenericConverter)) {
                ctx.setFieldValue(res);
                return (String) c.visualize(ctx);
            } else {
                return res;
            }
        } catch (EvalError e) {
            PresentationManager.getPm().error("BSH Interpreter Evaluation: " + e);
        }
        return null;
    }

    @Override
    public Object build(PMContext ctx) throws ConverterException {
        try {
            Interpreter bash = getBsh();
            bash.set("value", ctx.getFieldValue());
            bash.set("converter", this);
            final Object res = bash.eval(content + "\n" + "build();");
            if ("IgnoreConvertionException".equals(res)) {
                throw new IgnoreConvertionException("");
            }
            return res;
        } catch (EvalError e) {
            PresentationManager.getPm().error("BSH Interpreter Evaluation Error: " + e);
        }
        return null;
    }

    /**
     *
     */
    public GenericConverter() {
        super();
    }

    private Interpreter getBsh() {
        if (bsh == null) {
            try {
                this.filename = getConfig("filename");
                readFile(filename);
                bsh = initBSH();
            } catch (Exception e) {
                PresentationManager.getPm().error("BSH Interpreter Creation: " + e);
            }
        }
        return bsh;
    }

    /**
     * Reads the content of the converter file
     *
     * @param filename The file
     * @throws ConverterException
     */
    public void readFile(String filename) throws ConverterException {
        try {
            content = "";
            final InputStream input = ResourceManager.getInputStream(filename);
            while (input.available() > 0) {
                content = content + (char) input.read();
            }
            input.close();
        } catch (Exception e) {
            throw new ConverterException(e);
        }
    }

    /**
     *
     * @return a descriptive string
     */
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        if (filename != null) {
            sb.append('[');
            sb.append(filename);
            sb.append(']');
        }
        return sb.toString();
    }

    private Interpreter initBSH() throws UtilEvalError, EvalError {
        Interpreter bash = new Interpreter();
        BshClassManager bcm = bash.getClassManager();
        //TODO
        //bcm.setClassPath(getPm().getLoader().getURLs());
        //bcm.setClassLoader(getPm().getService().getServer().getLoader());
        bash.set("qbean", this);
        return bash;
    }
}
