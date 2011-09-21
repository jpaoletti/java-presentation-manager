package jpaoletti.jpm.menu;

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
    private StringBuilder sb;

    /**
     * Constructor for the parser
     *
     * @param evt Event for log
     * @param conf Configuration filename
     */
    public MenuItemLocationsParser(String conf) {
        this.setConf(conf);
        init();
    }

    private void init() {
        locations = new HashMap<String, MenuItemLocation>();
        error = false;
        sb =  new StringBuilder();
        parseConfig();
    }

    private void parseConfig() {
        try {
            SAXParserFactory dbf = SAXParserFactory.newInstance();
            SAXParser db = dbf.newSAXParser();
            db.parse(conf, this);
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
                PresentationManager.logItem(sb, id, clazz, "*");
            } catch (Exception e) {
                PresentationManager.logItem(sb, id, clazz, "!");
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
