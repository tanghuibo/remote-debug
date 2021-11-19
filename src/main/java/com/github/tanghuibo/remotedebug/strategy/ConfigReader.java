package com.github.tanghuibo.remotedebug.strategy;

import com.github.tanghuibo.remotedebug.dto.VmInfoDto;
import com.github.tanghuibo.remotedebug.properties.ConfigDetailProperties;
import com.github.tanghuibo.remotedebug.strategy.impl.IpConfigReadStrategyImpl;

import java.util.Arrays;
import java.util.List;

/**
 * ConfigReader
 *
 * @author tanghuibo
 * @date 2021/11/19 17:25
 */
public class ConfigReader {

    private static List<ConfigReadStrategy> configReadStrategyList = Arrays.asList(
            new IpConfigReadStrategyImpl()
    );

    public static VmInfoDto convert(ConfigDetailProperties properties) {
        for (ConfigReadStrategy configReadStrategy : configReadStrategyList) {
            if(configReadStrategy.match(properties)) {
                return configReadStrategy.convert(properties);
            }
        }
        return null;
    }


}
