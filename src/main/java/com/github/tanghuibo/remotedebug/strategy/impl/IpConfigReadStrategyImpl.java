package com.github.tanghuibo.remotedebug.strategy.impl;

import com.github.tanghuibo.remotedebug.dto.VmInfoDto;
import com.github.tanghuibo.remotedebug.properties.ConfigDetailProperties;
import com.github.tanghuibo.remotedebug.strategy.ConfigReadStrategy;
import com.github.tanghuibo.remotedebug.utils.IconManageUtils;

/**
 * IpConfigReadStrategyImpl
 *
 * @author tanghuibo
 * @date 2021/11/19 15:26
 */
public class IpConfigReadStrategyImpl extends ConfigReadStrategy {

    @Override
    public VmInfoDto convert(ConfigDetailProperties properties) {
        VmInfoDto vmInfoDto = new VmInfoDto();
        vmInfoDto.setIcon(IconManageUtils.IP_ICON);
        vmInfoDto.setTag(properties.getTag());
        vmInfoDto.setName(properties.getName());
        vmInfoDto.setHost(properties.getHost());
        vmInfoDto.setPort(properties.getPort());
        vmInfoDto.setUrl(properties.getUrl());
        return vmInfoDto;
    }

    @Override
    public boolean match(ConfigDetailProperties properties) {
        return "IP".equalsIgnoreCase(properties.getType());
    }
}
