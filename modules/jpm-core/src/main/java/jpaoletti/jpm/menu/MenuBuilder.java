package jpaoletti.jpm.menu;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.util.ResourceManager;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A parser class to parse the pm.menu.xml configuration file.
 *
 * @author jpaoletti
 *
 */
public class MenuBuilder extends DefaultHandler {

    private MenuList menu;
    private MenuItem item;
    private String conf;

    /**
     * The constructor of the parser. It needs the configuration file name and
     * the PMService.
     *
     * @param conf The relative filename of the pm.menu.xml file
     *
     */
    public MenuBuilder(String conf) {
        this.conf = conf;
        initMenu();
    }

    private void initMenu() {
        menu = parseConfig(conf);
    }

    private MenuList parseConfig(String conf) {
        final SAXParserFactory dbf = SAXParserFactory.newInstance();
        try {
            final SAXParser db = dbf.newSAXParser();
            db.parse(ResourceManager.getInputStream(conf), this);
            return menu;
        } catch (Exception e) {
            PresentationManager.getPm().error(e);
        }
        return null;
    }

    /**
     *
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        menu = new MenuList();
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
        final String text = attributes.getValue("text");
        final String perm = attributes.getValue("perm");
        final String _location = attributes.getValue("location");
        final String _locationValue = attributes.getValue("location-value");
        if (qName.compareTo("menu-list") == 0) {
            processList(text, perm);
        } else if (qName.compareTo("menu-item") == 0) {
            processItem(text, perm);
            if (_location != null) {
                item.setLocation(PresentationManager.getPm().getLocation(_location));
            }
            if (_locationValue != null) {
                item.setLocationValue(_locationValue);
            }
        }
        if (qName.compareTo("location") == 0) {
            item.parseLocation(attributes.getValue("id"), attributes.getValue("value"));
        }
    }

    /**
     *
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.compareTo("menu-list") == 0) {
            if (menu.getParent() != null) {
                menu = menu.getParent();
            }
        }
    }

    private void processItem(String text, String perm) {
        MenuItem m = new MenuItem(text);
        m.setPermission(perm);
        item = m;
        menu.add(m);
    }

    private void processList(String name, String perm) {
        MenuList subm = new MenuList();
        subm.setText(name);
        subm.setPermission(perm);
        menu.add(subm);
        menu = subm;
    }

    /**
     * Getter for menu
     *
     * @return The MenuList
     */
    public MenuList getMenu() {
        return menu;
    }

    /**
     * Setter for menu
     *
     * @param menu
     */
    public void setMenu(MenuList menu) {
        this.menu = menu;
    }
}
