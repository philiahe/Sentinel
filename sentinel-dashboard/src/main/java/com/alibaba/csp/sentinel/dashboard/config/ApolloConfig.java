package com.alibaba.csp.sentinel.dashboard.config;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author philia
 * @since 1.7.2
 */
@Data
@Component
@ConfigurationProperties(prefix = "apollo")
public class ApolloConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApolloConfig.class);

    private String env;
    private String appId = "sentinel-dashboard";
    private String clusterName = "default";
    private String namespaceName = "sentinel-rule";
    private String modifyUser = "apollo";
    private String modifyComment = "modify by sentinel-dashboard";
    private String releaseComment = "release by sentinel-dashboard";
    private String releaseUser = "apollo";
    private String portalUrl;
    private String applicationToken;

    private String authorityDataIdSuffix = "authority";
    private String degradeDataIdSuffix = "degrade";
    private String flowDataIdSuffix = "flow";
    private String paramDataIdSuffix = "param-flow";
    private String systemDataIdSuffix = "system";

    @Bean
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }

    @Bean
    public ApolloOpenApiClient apolloOpenApiClient() {
        return ApolloOpenApiClient.newBuilder()
                .withPortalUrl(portalUrl)
                .withToken(applicationToken)
                .build();
    }


}
