package jpaoletti.jpm;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.util.ResourceManager;

/**
 * Context listener for jPM initialization
 *
 * @author jpaoletti
 * @since 21/09/2011
 * @version 1.0
 *
 */
public class JPMContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            final InputStream is = ResourceManager.getInputStream("jpm-config.xml");
            final Properties properties = new Properties();
            properties.loadFromXML(is);
            PresentationManager.pm = new PresentationManager();
            final PresentationManager pm = PresentationManager.getPm();
            pm.initialize(properties);
        } catch (Exception ex) {
            Logger.getLogger(JPMContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
