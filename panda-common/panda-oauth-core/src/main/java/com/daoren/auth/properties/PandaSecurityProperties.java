package com.daoren.auth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * security相关参数
 * @author peng_da
 * @date  2022/8/26 14:37
 */
@ConfigurationProperties(prefix = "spring.security.panda")
public class PandaSecurityProperties {
    /** 网关白名单url */
    private List<String> ignoreUrl;
    /** 白名单路由前缀个数 */
    private Integer stripPrefix = 0;
    /** 白名单前缀 */
    private String prefixString = "";

    public List<String> getIgnoreUrl() {
        return ignoreUrl;
    }

    public void setIgnoreUrl(List<String> ignoreUrl) {
        this.ignoreUrl = ignoreUrl;
    }

    public Integer getStripPrefix() {
        return stripPrefix;
    }

    public void setStripPrefix(Integer stripPrefix) {
        this.stripPrefix = stripPrefix;
    }

    public String getPrefixString() {
        return prefixString;
    }

    public void setPrefixString(String prefixString) {
        this.prefixString = prefixString;
    }
}
