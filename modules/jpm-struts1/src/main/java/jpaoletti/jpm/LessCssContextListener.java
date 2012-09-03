package jpaoletti.jpm;

import java.io.File;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.struts.PMEntitySupport;
import org.lesscss.LessCompiler;

/**
 * Compiles less css.
 *
 * Put the following code in web.xml after JPMContextListener
 *
 * <pre>
 *  <listener>
 *       <listener-class>jpaoletti.jpm.LessCssContextListener</listener-class>
 *  </listener>
 * </pre>
 *
 * @author jpaoletti
 */
public class LessCssContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            if (PMEntitySupport.getInstance().getCssMode().equals("css")) {
                final LessCompiler lessCompiler = new LessCompiler();
                final String path = sce.getServletContext().getRealPath("/templates/" + PresentationManager.getPm().getTemplate());
                final File main = new File(path + "/all.less");
                if (main.exists()) {
                    lessCompiler.compile(
                            main,
                            new File(path + "/all.css")); //but its not minimified yet
                }
            }
        } catch (Exception ex) {
            PresentationManager.getPm().error(ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
