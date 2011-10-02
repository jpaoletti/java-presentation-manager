package jpaoletti.jpm.core.log;

/**
 * Log system interface
 * 
 * @author jpaoletti
 */
public interface JPMLogger {

    public void setName(String name);

    public boolean isDebugEnabled();

    public void debug(Object o);

    public void info(Object o);

    public void warn(Object o, Throwable throwable);

    public void warn(Object o);

    public void error(Object o, Throwable throwable);

    public void error(Object o);
}
