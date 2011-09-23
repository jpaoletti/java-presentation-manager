package jpaoletti.jpm;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import jpaoletti.jpm.core.PresentationManager;

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
            PresentationManager.start("jpm-config.xml");
        } catch (Exception ex) {
            Logger.getLogger(JPMContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
