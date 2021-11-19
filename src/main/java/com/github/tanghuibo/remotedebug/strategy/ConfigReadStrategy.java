package com.github.tanghuibo.remotedebug.strategy;

import com.github.tanghuibo.remotedebug.dto.VmInfoDto;
import com.github.tanghuibo.remotedebug.properties.ConfigDetailProperties;

/**
 * ConfigReadStrategy
 *
 * @author tanghuibo
 * @date 2021/11/19 15:24
 */
public abstract class ConfigReadStrategy {

    /**
     * 转换为 VmInfo
     * @param properties
     * @return
     */
    public abstract VmInfoDto convert(ConfigDetailProperties properties);

    /**
     * 是否匹配
     * @param properties
     * @return
     */
    public abstract boolean match(ConfigDetailProperties properties);
}
