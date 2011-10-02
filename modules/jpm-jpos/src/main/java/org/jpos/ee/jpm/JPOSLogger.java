/*
 * jPOS Project [http://jpos.org]
 * Copyright (C) 2000-2011 Alejandro P. Revilla
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jpos.ee.jpm;

import jpaoletti.jpm.core.log.JPMLogger;
import org.jpos.util.Log;

/**
 * Logger for jPOS
 * 
 * @author jpaoletti
 */
public class JPOSLogger implements JPMLogger {

    private Log log;

    @Override
    public void setName(String name) {
        log = Log.getLog(name, "jPM");
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void debug(Object o) {
        if (isEnabled()) {
            log.debug(o);
        }
    }

    @Override
    public void info(Object o) {
        if (isEnabled()) {
            log.info(o);
        }
    }

    @Override
    public void warn(Object o, Throwable throwable) {
        if (isEnabled()) {
            log.warn(o, throwable);
        }
    }

    @Override
    public void warn(Object o) {
        if (isEnabled()) {
            log.warn(o);
        }
    }

    @Override
    public void error(Object o, Throwable throwable) {
        if (isEnabled()) {
            log.error(o, throwable);
        }
    }

    @Override
    public void error(Object o) {
        if (isEnabled()) {
            log.error(o);
        }
    }

    private boolean isEnabled() {
        return log != null;
    }
}
