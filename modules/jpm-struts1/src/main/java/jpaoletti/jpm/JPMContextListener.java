package jpaoletti.jpm;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import jpaoletti.jpm.core.PresentationManager;
import org.apache.log4j.BasicConfigurator;

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
        BasicConfigurator.configure();
        if (!PresentationManager.isActive()) {
            PresentationManager.start("jpm-config.xml");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
