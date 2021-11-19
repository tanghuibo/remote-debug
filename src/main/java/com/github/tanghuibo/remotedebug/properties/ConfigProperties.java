package com.github.tanghuibo.remotedebug.properties;

import java.util.List;

/**
 * ConfigProperties
 *
 * @author tanghuibo
 * @date 2021/11/19 14:45
 */
public class ConfigProperties {

    /**
     * debug明细
     */
    private List<ConfigDetailProperties> debug;

    public List<ConfigDetailProperties> getDebug() {
        return debug;
    }

    public void setDebug(List<ConfigDetailProperties> debug) {
        this.debug = debug;
    }
}
