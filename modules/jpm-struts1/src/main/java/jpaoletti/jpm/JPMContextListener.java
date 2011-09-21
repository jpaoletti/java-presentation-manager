package jpaoletti.jpm;

import java.io.InputStream;
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
        //Load jpm-config.xml
        //Call PresentationManager.init();
        final InputStream is = JPMContextListener.class.getResourceAsStream("/jpm-config.xml");
        PresentationManager.pm = new PresentationManager();
        PresentationManager.getPm().initialize(null);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
