package jpaoletti.jpm.parser;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.JDomDriver;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import jpaoletti.jpm.util.ResourceManager;

/**
 *
 * @author jpaoletti
 */
public abstract class ParserSupport implements PMParser {

    private XStream xstream;

    protected void init() {
        xstream = new XStream(new JDomDriver());
    }

    @Override
    public Object parseFile(String filename) throws Exception {
        init();
        final InputStream is = ResourceManager.getInputStream(filename);
        return xstream.fromXML(new InputStreamReader(is), newObject());
    }

    @Override
    public void saveToFile(Object object, String filename) throws Exception {
        init();
        //todo finish
        xstream.toXML(object);
    }

    public XStream getXstream() {
        return xstream;
    }

    protected abstract Object newObject();
}
