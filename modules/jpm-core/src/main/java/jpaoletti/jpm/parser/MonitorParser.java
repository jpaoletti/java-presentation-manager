package jpaoletti.jpm.parser;

import jpaoletti.jpm.core.monitor.Monitor;

/**
 *
 * @author jpaoletti
 */
public class MonitorParser extends ParserSupport{

    @Override
    protected void init(){
        super.init();
        getXstream().alias ("monitor", Monitor.class);
    }

    @Override
    protected Object newObject() {
        return new Monitor();
    }

}
