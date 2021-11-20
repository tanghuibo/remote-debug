package com.github.tanghuibo.remotedebug.dto;

import javax.swing.*;

/**
 * VmInfoDto
 *
 * @author tanghuibo
 * @date 2021/11/19 14:49
 */
public class VmInfoDto {

    /**
     * 图标
     */
    private Icon icon;

    /**
     * 标签
     */
    private String tag;

    /**
     * 名称
     */
    private String name;

    /**
     * 主机
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 地址
     */
    private String url;

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
