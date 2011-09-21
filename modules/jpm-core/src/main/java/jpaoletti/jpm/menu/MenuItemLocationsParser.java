package jpaoletti.jpm.menu;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jpaoletti.jpm.core.PresentationManager;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**A parser class for the pm.locations.xml configuration file.
 * 
 * @author jpaoletti
 */
public final class MenuItemLocationsParser extends DefaultHandler {

    private static final String TAB = "    ";
    private String conf;
    private Map<String, MenuItemLocation> locations;
    private boolean error = false;
    private StringBuilder log;

    /**
     * Constructor for the parser
     *
     * @param evt Event for log
     * @param conf Configuration filename
     */
    public MenuItemLocationsParser(StringBuilder log, String conf) {
        this.setConf(conf);
        init(log);
    }

    private void init(StringBuilder log) {
        locations = new HashMap<String, MenuItemLocation>();
        error = false;
        parseConfig();
    }

    private void parseConfig() {
        try {
            final SAXParserFactory dbf = SAXParserFactory.newInstance();
            final SAXParser db = dbf.newSAXParser();
            final InputStream is = getClass().getClassLoader().getResourceAsStream(conf);
            db.parse(is, this);
        } catch (Exception e) {
            PresentationManager.pm.error(e);
        }
    }

    /**
     *
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.compareTo("location") == 0) {
            String id = attributes.getValue("id");
            String clazz = attributes.getValue("class");
            try {
                locations.put(id, (MenuItemLocation) PresentationManager.pm.newInstance(clazz));
                PresentationManager.logItem(log, id, clazz, "*");
            } catch (Exception e) {
                PresentationManager.logItem(log, id, clazz, "!");
                error = true;
            }
        }
    }

    /**
     * @param conf the conf to set
     */
    public void setConf(String conf) {
        this.conf = conf;
    }

    /**
     * @return the conf
     */
    public String getConf() {
        return conf;
    }

    /**
     * @return the locations
     */
    public Map<String, MenuItemLocation> getLocations() {
        return locations;
    }

    /**
     * Indicates if there was an error dduring excecution
     * 
     * @return true if an error ocurred
     */
    public boolean hasError() {
        return error;
    }
}
