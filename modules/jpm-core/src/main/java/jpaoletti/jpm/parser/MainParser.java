package jpaoletti.jpm.parser;

import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.util.Properties;
import jpaoletti.jpm.util.Property;

/**
 * Main configuration file parser
 *
 * @author jpaoletti
 * @since 22/09/2011
 * @version 1.0
 *
 */
public class MainParser extends ParserSupport {

    public MainParser(PresentationManager pm) {
        super(pm);
    }
    
    @Override
    protected void init() {
        super.init();
        getXstream().alias ("presentation-manager", Properties.class);
        getXstream().alias ("property", Property.class);
        getXstream().addImplicitCollection(Properties.class, "properties", Property.class);
        getXstream().useAttributeFor(Property.class, "name");
        getXstream().useAttributeFor(Property.class, "value");
    }

    @Override
    protected Object newObject() {
        return new Properties();
    }
}
