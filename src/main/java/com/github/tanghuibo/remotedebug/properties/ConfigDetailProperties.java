package com.github.tanghuibo.remotedebug.properties;


/**
 * ConfigDetailProperties
 *
 * @author tanghuibo
 * @date 2021/11/19 14:45
 */
public class ConfigDetailProperties {

    /**
     * 类型
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    /**
     * 标签
     */
    private String tag;

    /**
     * 主机
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
