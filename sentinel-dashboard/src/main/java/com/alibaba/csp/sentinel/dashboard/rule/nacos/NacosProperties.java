package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author philia
 * @since 1.7.2
 */
@Data
@Component
@ConfigurationProperties(prefix = "nacos.server")
public class NacosProperties {

    private String serverAddr;

    private String namespace;

    private String groupId;

    private String username;

    private String password;
}
