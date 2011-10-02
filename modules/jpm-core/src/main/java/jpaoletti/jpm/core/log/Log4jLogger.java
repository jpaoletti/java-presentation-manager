package jpaoletti.jpm.core.log;

import org.apache.log4j.Logger;

/**
 * Log4j logger
 * @author jpaoletti
 */
public class Log4jLogger implements JPMLogger {

    private Logger logger;

    @Override
    public void setName(String name) {
        logger = Logger.getLogger(name);
    }

    @Override
    public boolean isDebugEnabled() {
        return isEnabled() && logger.isDebugEnabled();
    }

    @Override
    public void debug(Object o) {
        if (isEnabled()) {
            logger.debug(o);
        }
    }

    @Override
    public void info(Object o) {
        if (isEnabled()) {
            logger.info(o);
        }
    }

    @Override
    public void warn(Object o, Throwable throwable) {
        if (isEnabled()) {
            logger.warn(o, throwable);
        }
    }

    @Override
    public void warn(Object o) {
        if (isEnabled()) {
            logger.warn(o);
        }
    }

    @Override
    public void error(Object o, Throwable throwable) {
        if (isEnabled()) {
            logger.error(o, throwable);
        }
    }

    @Override
    public void error(Object o) {
        if (isEnabled()) {
            logger.error(o);
        }
    }

    private boolean isEnabled() {
        return logger != null;
    }
}
